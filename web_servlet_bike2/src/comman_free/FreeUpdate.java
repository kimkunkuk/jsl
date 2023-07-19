package comman_free;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.CommonExcute;
import common.CommonUtil;
import dao.FreeDao;
import dto.FreeDto;

public class FreeUpdate implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		FreeDao dao = new FreeDao();
		String freeDir = CommonUtil.getFild_dir_freeboard();
		
		int maxSize = 1024*1024*5;
		MultipartRequest mpr = null;
		
		try {
			mpr = new MultipartRequest(request, freeDir, maxSize, "utf-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {
			e.printStackTrace();
		}
		String no = mpr.getParameter("t_no");
		String title = mpr.getParameter("t_title");
		String content = mpr.getParameter("t_content");
		String attach = mpr.getFilesystemName("t_attach");
		String ori_attach = mpr.getParameter("t_oriAttach");
		if(attach == null) {
			attach = ori_attach;
		}else {
			File file = new File(freeDir, ori_attach);
			file.delete();
			dao.DwHitReset(no);
		}
		String update_date = CommonUtil.getTodayTime();
		
		FreeDto dto = new FreeDto(no, title, content, attach, update_date);

		int result = dao.getFreeUpdate(dto);
		String msg = "수정성공!";
		
		if(result == 0) msg = "수정실패!";
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "FreeBoard");
	}

}

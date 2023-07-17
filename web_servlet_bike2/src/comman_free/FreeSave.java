package comman_free;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.CommonExcute;
import common.CommonUtil;
import dao.FreeDao;
import dto.FreeDto;

public class FreeSave implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		FreeDao dao = new FreeDao();
		String free_dir = CommonUtil.getFild_dir_freeboard();
		int maxSize = 1024 * 1024 * 5;
		MultipartRequest mpr = null;
		try {
			 mpr = new MultipartRequest(request, free_dir, maxSize, "utf-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String no = dao.getMaxNo();
		String title = mpr.getParameter("t_title");
		String content = mpr.getParameter("t_content");
		String attach = mpr.getFilesystemName("t_attach");
		String reg_date = CommonUtil.getTodayTime();
		HttpSession session = request.getSession();
		String reg_id = (String)session.getAttribute("sessionId");
		
		FreeDto dto = new FreeDto(no, title, content, attach, reg_date, reg_id);
		
		int result = dao.getFreeSave(dto);
		
		String msg = "등록성공!";
		if(result == 0) msg = "등록실패!";
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "FreeBoard");
	}

}

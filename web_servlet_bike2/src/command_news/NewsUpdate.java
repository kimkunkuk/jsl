package command_news;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.CommonExcute;
import common.CommonUtil;
import dao.NewsDao;
import dto.NewsDto;

public class NewsUpdate implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		NewsDao dao = new NewsDao();
		String newsDir = CommonUtil.getFile_dir_news();
		int maxSize = 1024 * 1024 * 5;
		String msg = "수정되었습니다.";
		
		try {
			MultipartRequest mpr =
					new MultipartRequest(request, newsDir, maxSize, "utf-8", new DefaultFileRenamePolicy());
			
			String no = mpr.getParameter("t_no");
			String title = mpr.getParameter("t_title");
			String content = mpr.getParameter("t_content");
			String attach = mpr.getFilesystemName("t_attach");
			String ori_attach = mpr.getParameter("t_ori_attach");
			if(attach == null) {
				attach = ori_attach;
			}else {
				File file = new File(newsDir, ori_attach);
				file.delete();
			}
			String update_date = CommonUtil.getTodayTime();
			
			NewsDto dto = new NewsDto(no, title, content, ori_attach, update_date);
			int result = dao.getNewsUpdate(dto);
			if(result != 1) msg = "수정실패!";
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "News");
		
	}

}

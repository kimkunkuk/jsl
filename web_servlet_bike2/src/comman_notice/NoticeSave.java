package comman_notice;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.CommonExcute;
import common.CommonUtil;
import dao.NoticeDao;
import dto.NoticeDto;

public class NoticeSave implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		NoticeDao dao = new NoticeDao();
		NoticeDto dto = new NoticeDto();
		HttpSession session = request.getSession();
		
		int maxSize = 1024*1024*10;
		
		try {
			MultipartRequest mpr = 
					new MultipartRequest(request, CommonUtil.getFile_dir_notice(), maxSize, "utf-8", new DefaultFileRenamePolicy());
			String no = dao.getMaxNo();
			String title = mpr.getParameter("t_title");
			String content = mpr.getParameter("t_content");
			String attach = mpr.getFilesystemName("t_attach");
			if(attach == null) attach = "";
			String reg_id = (String)session.getAttribute("sessionId");
			String reg_date = CommonUtil.getTodayTime();
			
			dto = new NoticeDto(no, title, content, attach, reg_id, reg_date);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
									// 리퀘스트, 첨부파일경로, 첨부파일 용량 사이즈, 한글 안깨지기, 첨부파일 중복해서 올리면 이름 뒤에 1,2... 붙이기
		
		int result = dao.noticeSave(dto);
		String msg = "등록!";
		if(result == 0) msg = "등록실패";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Notice");
		
	}

}

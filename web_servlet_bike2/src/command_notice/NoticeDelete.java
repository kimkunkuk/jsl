package command_notice;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import dao.NoticeDao;

public class NoticeDelete implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		NoticeDao dao = new NoticeDao();
		
		String no = request.getParameter("t_no");
		int result = dao.getDelete(no);
		
		String msg = "삭제성공!";
		if(result == 0) msg = "삭제실패!";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Notice");
		
	}

}

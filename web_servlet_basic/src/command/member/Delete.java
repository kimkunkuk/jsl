package command.member;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import dao.Memberdao;

public class Delete implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		Memberdao dao = new Memberdao();
		String id = request.getParameter("t_id");
		
		int result = dao.getDelete(id);
		
		String msg = "삭제성공!";
		String url = "Member";
		if(result != 1) {
			msg = "삭제실패!";
		}
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);

	}

}

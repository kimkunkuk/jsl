package command_member;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import dao.MemberDao;

public class MemberDelete implements CommonExecute {

	@Override
	public void execute(HttpServletRequest req) {
		MemberDao dao = new MemberDao();
		
		String id = req.getParameter("t_id");
		
		int result = dao.getDelete(id);
		
		String msg = "삭제성공!";
		if(result == 0) msg = "삭제실패!";
		
		req.setAttribute("t_msg", msg);
		req.setAttribute("t_url", "Member");
	}

}

package command_member;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import dao.MemberDao;

public class MemberPasswordSend implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		String id = request.getParameter("t_id");
		String mobile_1 = request.getParameter("t_mobile_1");
		String mobile_2 = request.getParameter("t_mobile_2");
		String mobile_3 = request.getParameter("t_mobile_3");
		String email = request.getParameter("t_email");
		
		String memberName = dao.getCheckMember(id, mobile_1, mobile_2, mobile_3, email);
		
		String msg = memberName+"님 에게 메일을 보냈습니다.";
		if(memberName.equals("")) {
			msg = "회원 정보가 없습니다.";
		}
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Member");
	}

}

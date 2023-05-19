package command_member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.CommonExcute;
import dao.MemberDao;
import dto.MemberDto;

public class MemberInfo implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionId");
		
		String msg="", url="";
		if(id == null) {
			msg = "로그인 정보 만료!!";
			url = "Member";
			request.setAttribute("urlGubun", "noSession");
			request.setAttribute("t_msg", msg);
			request.setAttribute("t_url", url);
			
		}else {
			request.setAttribute("urlGubun", "yesSession");
			
			MemberDto dto = dao.getMemberInfo(id);
			request.setAttribute("t_dto", dto);
		}
		
		
	}

}

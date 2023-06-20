package command_admin;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import dao.MemberDao;
import dto.MemberDto;

public class MemberView implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		
		String id = request.getParameter("t_id");
		
		MemberDto dto = dao.getMemberInfo(id);
		
		request.setAttribute("t_dto", dto);
	}

}

package command.member;

import javax.servlet.http.HttpServletRequest;

import dao.Memberdao;
import dto.MemberDto;

public class MemberView {
	public void execute(HttpServletRequest request) {
	Memberdao dao = new Memberdao();
	String id = request.getParameter("t_id");
	MemberDto dto = dao.getMemberView(id);
	
	request.setAttribute("t_dto", dto);
	}
}

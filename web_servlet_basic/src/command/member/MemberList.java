package command.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.Memberdao;
import dto.MemberDto;

public class MemberList {
	
	public void execute(HttpServletRequest request) {
		Memberdao dao = new Memberdao();
		
		String select  = request.getParameter("t_select");
		String search = request.getParameter("t_search");
		if(select == null){ // null로 넘어가서 조회 값 이상해지는거 방지
			select  = "id";
			search = "";
		}
		
		ArrayList<MemberDto> dtos = dao.getMemberList(select, search);
		request.setAttribute("t_dtos", dtos);
		request.setAttribute("t_select", select);
		request.setAttribute("t_search", search);
	}
	
}

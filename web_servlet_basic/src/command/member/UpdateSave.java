package command.member;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import dao.Memberdao;
import dto.MemberDto;

public class UpdateSave implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		Memberdao dao = new Memberdao();
		String id = request.getParameter("t_id");
		String name = request.getParameter("t_name");
		String age = request.getParameter("t_age");
		String reg_date = request.getParameter("t_date");
		
		MemberDto dto = new MemberDto(id, name, Integer.parseInt(age), reg_date);
		int result = dao.memberUpdate(dto);
		
		String msg = "수정성공!";
		String url = "Member";
		if(result != 1) {
			msg = "수정실패!";
		}
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);

	}

}

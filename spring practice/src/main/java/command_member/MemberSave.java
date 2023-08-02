package command_member;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import dao.MemberDao;
import dto.MemberDto;

public class MemberSave implements CommonExecute {

	@Override
	public void execute(HttpServletRequest req) {
		MemberDao dao = new MemberDao();
		String id = req.getParameter("t_id");
		String name = req.getParameter("t_name");
		String age = req.getParameter("t_age");
		String reg_date = req.getParameter("t_date");
		
		MemberDto dto = new MemberDto(id, name, Integer.parseInt(age), reg_date);
		
		int result = dao.memberSave(dto);
		String msg = "등록성공!";
		if(result == 0) msg = "등록실패!";
		
		req.setAttribute("t_msg", msg);
		req.setAttribute("t_url", "Member");
		//return "/common_alert";

	}

}

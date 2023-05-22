package command_member;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import common.CommonUtil;
import dao.MemberDao;
import dto.MemberDto;

public class MemberUpdate implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		
		String id = request.getParameter("t_id");
		String name = request.getParameter("t_name");
		String area = request.getParameter("t_area");
		String address = request.getParameter("t_address");
		String mobile_1 = request.getParameter("t_mobile_1");
		String mobile_2 = request.getParameter("t_mobile_2");
		String mobile_3 = request.getParameter("t_mobile_3");
		String gender = request.getParameter("t_gender");
		String hobby_t = request.getParameter("t_hobby_t");
		String hobby_r = request.getParameter("t_hobby_r");
		String hobby_s = request.getParameter("t_hobby_s");
		if(hobby_t == null) hobby_t="n";
		if(hobby_r == null) hobby_r="n";
		if(hobby_s == null) hobby_s="n";
		String update_date = CommonUtil.getTodayTime();
		
		MemberDto dto = new MemberDto(id, name, area, address, mobile_1, mobile_2, mobile_3, 
												gender, hobby_t, hobby_r, hobby_s, update_date);
		
		int result = dao.memberUpdate(dto);
		String msg = "", url ="";
		if(result == 1) {
			msg = "수정성공!";
			url = "Index";
		}else {
			msg = "수정실패!";
			url = "Member?t_gubun=memberUpadteForm";
		}
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);
	}

}

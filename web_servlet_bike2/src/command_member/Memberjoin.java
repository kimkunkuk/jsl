package command_member;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import common.CommonUtil;
import dao.MemberDao;
import dto.MemberDto;

public class Memberjoin implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		
		String id = request.getParameter("t_id");
		String name = request.getParameter("t_name");
		String pw = request.getParameter("t_pw2");
		int pwlen = pw.length();
		String password = "";
		try {
			password = dao.encryptSHA256(pw);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String area = request.getParameter("t_area");
		String address = request.getParameter("t_address");
		String mobile_1 = request.getParameter("t_mobile_1");
		String mobile_2 = request.getParameter("t_mobile_2");
		String mobile_3 = request.getParameter("t_mobile_3");
		String gender = request.getParameter("t_gender");
		String hobby_t = request.getParameter("t_hobby_t");
		String hobby_r = request.getParameter("t_hobby_r");
		String hobby_s = request.getParameter("t_hobby_s");
		String reg_date = CommonUtil.getTodayTime();
		
		if(hobby_t == null) hobby_t="n";
		if(hobby_r == null) hobby_r="n";
		if(hobby_s == null) hobby_s="n";
		
		//System.out.println(hobby_t);
		//System.out.println(hobby_r);
		//System.out.println(hobby_s);
		
		
		MemberDto dto = new MemberDto(id, name, password, area, address, mobile_1, mobile_2, mobile_3, gender, hobby_t, hobby_r, hobby_s, reg_date, pwlen);
		int result = dao.memberSave(dto);
		String msg = "등록성공!";
		String url = "Member";
		if(result != 1) {
			msg = "등록실패!";
		}
		request.setAttribute("t_url", url);
		request.setAttribute("t_msg", msg);
		
	}

}

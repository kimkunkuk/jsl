package command_member;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import dao.MemberDao;

public class MemberLogin implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		
		String id = request.getParameter("t_id");
		String pw = request.getParameter("t_pw");	
		try {
			pw = dao.encryptSHA256(pw);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String name = dao.loginCheck(id, pw);
		
	}

}

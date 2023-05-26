package command_member;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.CommonExcute;
import common.CommonUtil;
import dao.MemberDao;
import dto.MemberDto;

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
		String msg ="", url ="";
		
		MemberDto dto = dao.checkLogin(id, pw);
		String account = dao.checkAccount(id);
		
		if(dto == null || account.equals("n")) {
			msg="아이디나 비밀번호 오류";
			url="Member";
		}else {
			msg= dto.getName()+"hello";
			url= "Index";
			HttpSession session = request.getSession();
			session.setAttribute("sessionId", id);
			session.setAttribute("sessionName", dto.getName());
			session.setAttribute("sessionLevel", dto.getMemberlevel());
			session.setMaxInactiveInterval(60 * 60 * 3);
			
			int result = dao.setLoginTime(id,CommonUtil.getTodayTime());
		}
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);
		
		//String account = dao.checkAccount(id);
	
				
		
	}

}

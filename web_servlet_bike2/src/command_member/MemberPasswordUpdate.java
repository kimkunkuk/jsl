package command_member;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.CommonExcute;
import dao.MemberDao;

public class MemberPasswordUpdate implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		HttpSession session = request.getSession();
		String now_Pw = request.getParameter("t_now_pw");
		String new_Pw = request.getParameter("t_change_pw_1");
		int pwlen = new_Pw.length();
		String id = (String)session.getAttribute("sessionId");
		String name = (String)session.getAttribute("sessionName");
		
		String msg = "";
		if(id == null) {
			msg="로그인 정보가 만료 되었습니다.";
		}else {
			try {
				now_Pw = dao.encryptSHA256(now_Pw);
				new_Pw = dao.encryptSHA256(new_Pw);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int count = dao.getPasswordCheck(id, now_Pw);
			if(count != 1) {
				msg = "비밀번호가 틀립니다.";
			}else {
				int result = dao.setMemberPassword(id, new_Pw, pwlen);
				
				if(result == 1) {
					msg = name+"님 비밀번호가 수정되었습니다.";
					session.invalidate();
				}
				else {
					msg= name+"님 비밀번호 변경오류! 관리자 에게 문의하세요.";
				}
			}
			
		}
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Member");
		
	}

}

package command_member;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import common.MailContentSend;
import dao.MemberDao;

public class MemberPasswordSend implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		String id = request.getParameter("t_id");
		String mobile_1 = request.getParameter("t_mobile_1");
		String mobile_2 = request.getParameter("t_mobile_2");
		String mobile_3 = request.getParameter("t_mobile_3");
		String email = request.getParameter("t_email");
		
		String memberName = dao.getCheckMember(id, mobile_1, mobile_2, mobile_3, email);
		
		String msg = "";
		if(memberName.equals("")) {
			msg = "회원 정보가 없습니다.";
		}else {
			String newPassword = dao.getNewPassword(4);
			String sendPassword = newPassword;
			System.out.println(newPassword);
			try {
				newPassword = dao.encryptSHA256(newPassword);
				
			}catch(NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			int result = dao.setMemberPassword(id, newPassword);
			if(result != 1) {
				msg = "비밀번호 발송오류";
			}else {
				
				String mailSet_Server="smtp.naver.com"; // 보내는 메일 server
				String mailSet_ID="l0501jh";        // 보내는 메일 ID
				String mailSet_PW="";        // 보내는 메일 비밀번호
				
				String mailFromName ="JSL인재개발원";            // 보내는 사람 이름
				String mailFromAddress ="l0501jh@naver.com"; // 보내는 메일 주소
				
				String mailTo    = email;           // 받는  메일 주소
				String mailTitle =" JSL에서 비밀번호를 재발송 합니다.";   // 메일 제목
				String content = 	                             // 메일내용
				" <table width='400' height='200' border='0' > "+
				" <tr> "+
				" 	<td colspan='2' height='100' align='center'>고객님 비빌번호 안내</td> "+
				" </tr> "+
				" <tr>"+
				" 	<td width='120' align='center'>비 밀 번 호</td> "+
				" 	<td width='280' align='left'>"+
				"        <font size='5' color='red'>"+sendPassword+"</font> 입니다</td> "+
				" </tr> "+
				" </table> ";
				msg = memberName+"님 에게 새로운 비밀번호를 보냈습니다.";
				
				MailContentSend mail = new MailContentSend();
				mail.setMail(mailSet_Server, mailSet_ID, mailSet_PW);
				try {
					mail.sendMail(mailFromName, mailFromAddress, mailTo, mailTitle, content);
				} catch (Exception e) {
					msg = "비밀번호 메일 발송 실패";
					e.printStackTrace();
				}
				
				
				
				
			}
		}
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Member");
	}

}

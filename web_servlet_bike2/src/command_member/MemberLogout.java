package command_member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.CommonExcute;

public class MemberLogout implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("sessionName"); //(String) 하는 이유 없으면 완벽한 스트링 타입이 아니여서 해줌
		session.invalidate();
		
		String msg = name+"님 로그아웃되었습니다.";
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Index");
	}

}

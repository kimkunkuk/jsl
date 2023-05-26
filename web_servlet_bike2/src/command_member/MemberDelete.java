package command_member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.CommonExcute;
import common.CommonUtil;
import dao.MemberDao;

public class MemberDelete implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		HttpSession session = request.getSession(); //java 에선 해줘야 되는거 세션
		MemberDao dao = new MemberDao();
		String id = request.getParameter("t_id");
		String delDate = CommonUtil.getTodayTime();
		String msg = "", url = "Index";
		if(id == null) msg = "로그인 정보 만료";
		if(id != null) {
			int result = dao.memberDelete(id, delDate);
			
			if(result == 1) {
				msg = "탈퇴성공!";
				session.invalidate();
			}else {
				msg = "탈퇴실패!";
			}
		}
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);
		
		
	}

}

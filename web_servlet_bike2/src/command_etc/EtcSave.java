package command_etc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.CommonExcute;
import common.CommonUtil;
import dao.EtcDao;
import dto.EtcDto;

public class EtcSave implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		EtcDao dao = new EtcDao();
		
		String no = dao.getMaxNo();
		String group_no = no;
		int depth = 0;
		String title = request.getParameter("t_title");
		String content = request.getParameter("t_content");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionId");
		String reg_date = CommonUtil.getTodayTime();
		
		EtcDto dto = new EtcDto(no, group_no, depth, title, content, id, reg_date);
		
		int result = dao.getSave(dto);
		
		String msg = "등록성공!";
		if(result == 0) msg = "등록실패!";
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Etc");
	}

}

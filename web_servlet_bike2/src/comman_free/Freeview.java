package comman_free;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.CommonExcute;
import common.CommonUtil;
import dao.FreeDao;
import dto.FreeDto;

public class Freeview implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		FreeDao dao = new FreeDao();
		String no = request.getParameter("t_no");
		
		dao.getHitCount(no);
		
		FreeDto dto = dao.getFreeView(no);
		
		String extension = "";
		if(dto.getAttach() != null) {
			int len = dto.getAttach().indexOf(".");
			extension = dto.getAttach().substring(len+1);
		}
		
		String todayTime = CommonUtil.getTodayTime();
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionId");
		
		request.setAttribute("t_todayTime", todayTime);
		request.setAttribute("t_dto", dto);
		request.setAttribute("t_extension", extension);
		request.setAttribute("t_id", id);
	}

}

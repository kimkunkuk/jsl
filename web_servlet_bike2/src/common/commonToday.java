package common;

import javax.servlet.http.HttpServletRequest;

import dao.NewsDao;
import dto.NewsDto;

public class commonToday implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		String todayTime = CommonUtil.getTodayTime();
		request.setAttribute("t_todayTime", todayTime);
		
		NewsDao dao = new NewsDao();
		
		String no = request.getParameter("t_no");
		
		NewsDto dto = dao.getView(no);
		
		request.setAttribute("t_dto", dto);
	}

}

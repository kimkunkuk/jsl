package command_news;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import common.CommonUtil;
import dao.NewsDao;
import dao.NoticeDao;
import dto.NewsDto;
import dto.NoticeDto;

public class NewsView implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		NewsDao dao = new NewsDao();
		String no = request.getParameter("t_no");
		
		dao.getHitCount(no);
		NewsDto dto = dao.getView(no);
		
		NewsDto predto = dao.getPreTitle(no);
		NewsDto nextdto = dao.getNextTitle(no);
		
		String todayTime = CommonUtil.getTodayTime();
		
		request.setAttribute("t_todayTime", todayTime);
		
		request.setAttribute("t_dto", dto);
		
		request.setAttribute("t_predto", predto);
		request.setAttribute("t_nextdto", nextdto);

	}

}

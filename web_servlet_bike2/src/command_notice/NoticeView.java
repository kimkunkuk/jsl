package command_notice;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import common.CommonUtil;
import dao.NoticeDao;
import dto.NoticeDto;

public class NoticeView implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		NoticeDao dao = new NoticeDao();
		String no = request.getParameter("t_no");
		
		dao.setHitCount(no);
		NoticeDto dto = dao.getNoticeView(no);
		
		NoticeDto predto = dao.getLeftTitle(no);
		NoticeDto nextdto = dao.getRightTitle(no);
		
		String todayTime = CommonUtil.getTodayTime();
		
		request.setAttribute("t_todayTime", todayTime);
		
		request.setAttribute("t_dto", dto);
		
		request.setAttribute("t_predto", predto);
		request.setAttribute("t_nextdto", nextdto);

	}

}

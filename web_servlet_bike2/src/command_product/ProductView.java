package command_product;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import common.CommonUtil;
import dao.ProductDao;
import dto.NewsDto;
import dto.ProductDto;

public class ProductView implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		ProductDao dao = new ProductDao();
		String no = request.getParameter("t_no");
		
		dao.getHitCount(no);
		
		ProductDto dto = dao.getProductView(no);
		ProductDto predto = dao.getPreTitle(no);
		ProductDto nextdto = dao.getNextTitle(no);
		
		String todayTime = CommonUtil.getTodayTime();
		
		request.setAttribute("t_todayTime", todayTime);
		
		request.setAttribute("t_dto", dto);
		request.setAttribute("t_predto", predto);
		request.setAttribute("t_nextdto", nextdto);
	}

}

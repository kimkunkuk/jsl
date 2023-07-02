package command_sale;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import dao.SaleDao;
import dto.ProductSaleDto;

public class SaleView implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		SaleDao dao = new SaleDao();
		ProductSaleDto dto = new ProductSaleDto();
		
		String no = request.getParameter("t_no");
		String up = request.getParameter("t_up");
		if(up == null) {
			up = "";
		}
		String state = request.getParameter("t_state");
		
		dto = dao.getSaleView(no);
		
		int result = 0;
		if(up.equals("1")) {
			result = dao.getStateUpdate(no, state);
		}
		String msg = "수정성공!";
		if(result == 0) msg = "수정실패!";
		
		request.setAttribute("t_dto", dto);
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "SaleList");
	}

}

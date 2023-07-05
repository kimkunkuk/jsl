package command_product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.CommonExcute;
import common.CommonUtil;
import dao.ProductDao;
import dto.ProductSaleDto;


public class ProductSaleSave implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		ProductDao dao = new ProductDao();
		ProductSaleDto dto = null;
		HttpSession session = request.getSession();
		
		String p_no = request.getParameter("t_p_no");
		String state = "입금확인중";
		String s_no = dao.getMaxSaleNo();
		String id = (String)session.getAttribute("sessionId");
		String address = request.getParameter("t_address");
		String pay = request.getParameter("t_pay");
		String price = request.getParameter("t_price");
		String reg_date = CommonUtil.getTodayTime();
		String name = request.getParameter("t_name");
		String mobile_1 = request.getParameter("t_mobile_1");
		String mobile_2 = request.getParameter("t_mobile_2");
		String mobile_3 = request.getParameter("t_mobile_3");
		String card_1 = request.getParameter("t_card_1");
		String card_2 = request.getParameter("t_card_2");
		String card_3 = request.getParameter("t_card_3");
		String card_4 = request.getParameter("t_card_4");
		
		dto = new ProductSaleDto(s_no, p_no, id, state, address, pay, price, reg_date, 
				name, mobile_1, mobile_2, mobile_3, card_1, card_2, card_3, card_4);
		
		int result = dao.getSaleSave(dto);
		String msg = "주문성공!";
		if(result == 0)msg = "주문실패!";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Index");
		
	}

}

package command_order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.CommonExcute;
import dao.SaleDao;
import dto.ProductSaleDto;

public class OrderList implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		SaleDao dao = new SaleDao();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionId");
		
		ArrayList<ProductSaleDto> arr = dao.getOrderList(id);
		
		request.setAttribute("t_arr", arr);
	}

}

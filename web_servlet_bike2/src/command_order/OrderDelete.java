package command_order;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import dao.SaleDao;

public class OrderDelete implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		SaleDao dao = new SaleDao();
		
		String no = request.getParameter("t_no");
		
		int result = dao.getSaleCancel(no);
		
		String msg = "주문취소 성공!";
		if(result == 0) msg = "주문취소 실패!";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Order");

	}

}

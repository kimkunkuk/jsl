package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command_order.OrderDelete;
import command_order.OrderList;
import command_sale.SaleView;

/**
 * Servlet implementation class Order
 */
@WebServlet("/Order")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Order() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String gubun = request.getParameter("t_gubun");
		if(gubun == null) gubun = "orderList";
		String viewPage = "";
		request.setAttribute("t_ma", "order");
		
		//주문리스트
		if(gubun.equals("orderList")) {
			OrderList order = new OrderList();
			order.execute(request);
			viewPage = "order/orderList.jsp";
		
		//주문정보 상세보기
		}else if(gubun.equals("view")) {
			SaleView order = new SaleView();
			order.execute(request);
			viewPage = "order/orderView.jsp";
			
		//주문취소	
		}else if(gubun.equals("delete")) {
			OrderDelete order = new OrderDelete();
			order.execute(request);
			viewPage = "common_alert.jsp";
			
		}
				
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

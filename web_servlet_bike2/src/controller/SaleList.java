package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command_sale.SaleInfo;
import command_sale.SaleView;
import command_sale.StateUpdate;

/**
 * Servlet implementation class saleList
 */
@WebServlet("/SaleList")
public class SaleList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaleList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String gubun = request.getParameter("t_gubun");
		if(gubun == null) gubun = "saleList";
		String viewPage = "";
		request.setAttribute("t_ma", "saleList");
		
		//판매 리스트
		if(gubun.equals("saleList")) {
			command_sale.SaleList sale = new command_sale.SaleList();
			sale.execute(request);
			viewPage = "admin/saleList.jsp";
			
		//판매정보 상세뷰
		}else if(gubun.equals("view")) {
			SaleView sale = new SaleView();
			sale.execute(request);
			viewPage = "admin/saleView.jsp";
			//판매정보 진행상황 수정(관리자용)
			String up = request.getParameter("t_up");
			if(up == null) up = "";
			if(up.equals("1")) {
				sale.execute(request);
				viewPage = "common_alert.jsp";
			}
		
		//판매현황 리스트	
		}else if(gubun.equals("saleInfo")) {
			request.setAttribute("t_ma", "saleInfo");
			SaleInfo sale = new SaleInfo();
			sale.execute(request);
			viewPage = "admin/saleInfo.jsp";
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

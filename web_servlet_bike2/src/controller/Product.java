package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command_product.ProductList;
import command_product.ProductSave;
import command_product.ProductUpdate;
import command_product.ProductView;
import common.commonToday;

/**
 * Servlet implementation class Product
 */
@WebServlet("/Product")
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String gubun = request.getParameter("t_gubun");
		if(gubun == null) gubun = "product";
		String viewPage = "";
		request.setAttribute("t_ma", "product");
		
		//프로덕트 리스트	
		if(gubun.equals("product")) {
			ProductList product = new ProductList();
			product.execute(request);
			viewPage = "product/product.jsp";
		
		//프로덕트 글쓰기 폼
		}else if(gubun.equals("writeForm")) {
			commonToday product = new commonToday();
			product.execute(request);
			viewPage = "product/product_write.jsp";
		
		//글 세이브
		}else if(gubun.equals("save")) {
			ProductSave product = new ProductSave();
			product.execute(request);
			viewPage = "common_alert.jsp";
			
		//게시글 뷰	
		}else if(gubun.equals("view")) {
			ProductView product = new ProductView();
			product.execute(request);
			viewPage = "product/product_view.jsp";
			
		//업데이트 폼
		}else if(gubun.equals("update")) {
			ProductView product = new ProductView();
			product.execute(request);
			viewPage = "product/product_update.jsp";
			
		//업데이트 세이브
		}else if(gubun.equals("updateSave")) {
			ProductUpdate product = new ProductUpdate();
			product.execute(request);
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

package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command_admin.MemberList;
import command_admin.MemberView;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String gubun = request.getParameter("t_gubun");
		if(gubun == null) gubun = "admin";
		String viewPage = "";
		String ma = "admin";
		if(gubun.equals("product")) ma = "product";
		request.setAttribute("t_ma", ma);
		
		//멤버리스트
		if(gubun.equals("admin")) {
			MemberList admin = new MemberList();
			admin.execute(request);
			viewPage = "admin/memberList.jsp";
			
		//상세뷰	
		}else if(gubun.equals("view")) {
			MemberView admin = new MemberView();
			admin.execute(request);
			viewPage = "admin/memberView.jsp";
			
		//프로덕트 리스트	
		}else if(gubun.equals("product")) {
			
			viewPage = "admin/product.jsp";
		
		//프로덕트 글쓰기 폼
		}else if(gubun.equals("writeForm")) {
			viewPage = "admin/product_write.jsp";
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

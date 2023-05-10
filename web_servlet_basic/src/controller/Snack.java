package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command_snack.Com_snack;

/**
 * Servlet implementation class Snack
 */
@WebServlet("/Snack")
public class Snack extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Snack() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Com_snack snack = new Com_snack();
		String gubun = request.getParameter("t_gubun");
		String viewPage = "";
		if(gubun == null) gubun = "list";
	
		if(gubun.equals("list")) {
			snack.executeList(request);
			viewPage = "snack/Snack_list.jsp";
		}else if(gubun.equals("writeForm")) {
			viewPage = "snack/snack_write.jsp";
		}else if(gubun.equals("writeSave")) {
			snack.executeWriteSave(request);
			viewPage = "common_alert.jsp";
		}else if(gubun.equals("view")) {
			snack.executeView(request);
			viewPage = "snack/snack_view.jsp";
		}else if(gubun.equals("update")) {
			snack.executeUpdateForm(request);
			viewPage = "snack/snack_update.jsp";
		}else if(gubun.equals("updateSave")) {
			snack.executeUpdateSave(request);
			viewPage = "common_alert.jsp";
		}else if(gubun.equals("delete")) {
			snack.executeDelete(request);
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

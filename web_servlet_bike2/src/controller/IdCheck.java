package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;

/**
 * Servlet implementation class IdCheck
 */
@WebServlet("/IdCheck")
public class IdCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		MemberDao dao = new MemberDao();
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("t_id");
		String msg = "";
		
		int count = dao.checkId(id);
		if(count != 1) msg ="y";
		else if(count == 1) msg ="n";
		
		out.print(msg);
		
//		response.setContentType("text/html; charset=utf-8");
//		response.setCharacterEncoding("utf-8");
//		String id= request.getParameter("t_id");
//		MemberDao dao = new MemberDao();
//		int count= dao.checkId(id);
//		String msg= "";
//		if(count == 0) msg="사용 가능";
//		else if(count == 1) msg="이미 존재하는 ID";
//		else msg="서버 오류";
//		PrintWriter out = response.getWriter();
//		out.print(msg);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comman_notice.NoticeList;
import common.CommonExcute;

/**
 * Servlet implementation class Notice
 */
@WebServlet("/Notice")
public class Notice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Notice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String gubun = request.getParameter("t_gubun");
		String viewPage="";
		if(gubun == null) gubun="noticeList"; //t_gubun 이 받아오는게 없으면 null
		
		if(gubun.equals("List")) {
			NoticeList notice = new NoticeList(); 
			//CommonExcute notice = new NoticeList(); NoticeList 부모가 commonexcute 
			notice.execute(request);
			viewPage="notice/notice_list.jsp";
		}else if(gubun.equals("writeForm")) {
			viewPage="notice/notice_write.jsp";
		}else if(gubun.equals("save")) {
			viewPage="common_alert.jsp";
		}else if(gubun.equals("view")) {
			viewPage="notice/notice_view.jsp";
		}else if(gubun.equals("updateForm")) {
			viewPage="notice/notice_update.jsp";
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

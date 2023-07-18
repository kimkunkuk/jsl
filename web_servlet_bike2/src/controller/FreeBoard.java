package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comman_free.FreeList;
import comman_free.FreeSave;
import comman_free.Freeview;
import command_notice.NoticeList;
import command_notice.NoticeView;
import common.CommonExcute;
import common.commonToday;

/**
 * Servlet implementation class FreeBoard
 */
@WebServlet("/FreeBoard")
public class FreeBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String gubun = request.getParameter("t_gubun");
		String viewPage = "";
		request.setAttribute("t_ma", "free");
		if(gubun == null) gubun="list";
		
		if(gubun.equals("list")) {
			FreeList free = new FreeList();
			free.execute(request);
			viewPage = "free_board/fb_list.jsp";
			
		//글쓰기 폼
		}else if(gubun.equals("writeForm")) {
			CommonExcute free = new commonToday();
			free.execute(request);
			viewPage = "free_board/fb_write.jsp";
			
		//글쓰기 저장
		}else if(gubun.equals("save")) {
			FreeSave free = new FreeSave();
			free.execute(request);
			viewPage = "common_alert.jsp";
			
		//상세보기 
		}else if(gubun.equals("view")) {
			Freeview free = new Freeview();
			free.execute(request);
			viewPage="free_board/fb_view.jsp";
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

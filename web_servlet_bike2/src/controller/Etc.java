package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command_etc.EtcComSave;
import command_etc.EtcList;
import command_etc.EtcSave;
import common.CommonExcute;
import common.commonToday;

/**
 * Servlet implementation class Etc
 */
@WebServlet("/Etc")
public class Etc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Etc() {
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
		request.setAttribute("t_ma", "etc");
		if(gubun == null) gubun="list";
		
		if(gubun.equals("list")) {
			EtcList etc = new EtcList();
			etc.execute(request);
			viewPage = "etc/etc_list.jsp";
		//etc 게시글 폼
		}else if(gubun.equals("write")) {
			CommonExcute free = new commonToday();
			free.execute(request);
			viewPage = "etc/etc_writeForm.jsp";
		//etc 게시글 저장
		}else if(gubun.equals("save")) {
			EtcSave etc = new EtcSave();
			etc.execute(request);
			viewPage="common_alert.jsp";
		//댓글작성
		}else if(gubun.equals("commentSave")) {
			EtcComSave etc = new EtcComSave();
			etc.execute(request);
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

package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command_news.NewsList;
import command_news.NewsSave;
import command_news.NewsUpdate;
import command_news.NewsView;
import common.CommonExcute;
import common.commonToday;

/**
 * Servlet implementation class News
 */
@WebServlet("/News")
public class News extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public News() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String gubun = request.getParameter("t_gubun");
		if(gubun == null) gubun = "list";
		String viewPage = "";
		request.setAttribute("t_ma", "news"); //왼쪽 메뉴 사과 아이콘 
		if(gubun.equals("list")) {
			CommonExcute news = new NewsList();
			news.execute(request);
			viewPage = "news/news_list.jsp";
			
		}else if(gubun.equals("writeForm")) {
			CommonExcute common = new commonToday();
			common.execute(request);
			viewPage = "news/news_write.jsp";
			
		}else if(gubun.equals("save")) {
			CommonExcute news = new NewsSave();
			news.execute(request);
			viewPage = "common_alert.jsp";
			
		}else if(gubun.equals("view")) {
			CommonExcute news = new NewsView();
			news.execute(request);
			viewPage = "news/news_view.jsp";
		
		}else if(gubun.equals("update")) {
			CommonExcute news = new NewsView();
			news.execute(request);
			viewPage = "news/news_updateForm.jsp";
			
		}else if(gubun.equals("updateSave")) {
			CommonExcute news = new NewsUpdate();
			news.execute(request);
			viewPage = "common_alert.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

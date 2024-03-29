package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command_notice.NoticeDelete;
import command_notice.NoticeList;
import command_notice.NoticeSave;
import command_notice.NoticeUpdate;
import command_notice.NoticeView;
import common.CommonExcute;
import common.commonToday;

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
        // TODO Auto-generated  constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String gubun = request.getParameter("t_gubun");
		//System.out.println(gubun);
		String viewPage="";
		request.setAttribute("t_ma", "notice"); //왼쪽 메뉴 사과 아이콘 
		if(gubun == null) gubun="List"; //t_gubun 이 받아오는게 없으면 null
		
		//목록
		if(gubun.equals("List")) {
			NoticeList notice = new NoticeList(); 
			//CommonExcute notice = new NoticeList(); NoticeList 부모가 commonexcute 
			notice.execute(request);
			viewPage="notice/notice_list.jsp";
			
		//글쓰기 폼	
		}else if(gubun.equals("writeForm")) {
			CommonExcute notice = new commonToday();
			notice.execute(request);
			viewPage="notice/notice_write.jsp";
		
		//게시글 등록
		}else if(gubun.equals("save")) {
			NoticeSave notice = new NoticeSave();
			notice.execute(request);
			viewPage="common_alert.jsp";
		
		//상세보기 
		}else if(gubun.equals("view")) {
			NoticeView notice = new NoticeView();
			notice.execute(request);
			viewPage="notice/notice_view.jsp";
		
		//게시글 수정
		}else if(gubun.equals("updateForm")) {
			NoticeView notice = new NoticeView();
			notice.execute(request);
			viewPage="notice/notice_update.jsp";
			
		//게시글 삭제
		}else if(gubun.equals("delete")) {
			NoticeDelete notice = new NoticeDelete();
			notice.execute(request);
			viewPage="common_alert.jsp";
		
		//업데이트 저장
		}else if(gubun.equals("updateSave")) {
			NoticeUpdate notice = new NoticeUpdate();
			notice.execute(request);
			viewPage="common_alert.jsp";
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

package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command_member.MemberInfo;
import command_member.MemberLogin;
import command_member.MemberLogout;
import command_member.Memberjoin;

/**
 * Servlet implementation class Member
 */
@WebServlet("/Member")
public class Member extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Member() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String gubun = request.getParameter("t_gubun");
		if(gubun == null) gubun="memberLogin";
		String viewPage = "";
		
		//회원가입폼
		if(gubun.equals("memberJoin")) {
			viewPage = "member/member_join.jsp";
			
		//회원가입 세이브	
		}else if(gubun.equals("memberSave")) {
			Memberjoin member = new Memberjoin();
			member.execute(request);
			viewPage = "common_alert.jsp";
		//로그인	
		}else if(gubun.equals("memberLogin")) {
			viewPage = "member/member_login.jsp";
		//로그인 디비	
		}else if(gubun.equals("goLogin")) {
			MemberLogin member = new MemberLogin();
			member.execute(request);
			viewPage = "common_alert.jsp";
		//로그아웃	
		}else if(gubun.equals("memberLogout")) {
			MemberLogout member = new MemberLogout();
			member.execute(request);
			viewPage = "common_alert.jsp";
		//내정보
		}else if(gubun.equals("memberMyinfo")) {
			MemberInfo member = new MemberInfo();
			member.execute(request);
			String urlGubun = (String)request.getAttribute("urlGubun");
			if(urlGubun.equals("noSession")) {
				viewPage = "common_alert.jsp";
			}else {
				viewPage = "member/member_info.jsp";
			}
		
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

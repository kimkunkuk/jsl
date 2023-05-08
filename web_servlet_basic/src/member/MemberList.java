package member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Memberdao;
import dto.MemberDto;

/**
 * Servlet implementation class MemberList
 */
@WebServlet("/MemberList")
public class MemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		Memberdao dao = new Memberdao();
		
		String gubun  = request.getParameter("t_gubun");
		String search = request.getParameter("t_search");
		if(gubun == null){ // null로 넘어가서 조회 값 이상해지는거 방지
			gubun  = "id";
			search = "";
		}
		
		ArrayList<MemberDto> dtos = dao.getMemberList(gubun, search);
		request.setAttribute("t_dtos", dtos);
		request.setAttribute("t_gubun", gubun);
		request.setAttribute("t_search", search);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("member/member_list.jsp");
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

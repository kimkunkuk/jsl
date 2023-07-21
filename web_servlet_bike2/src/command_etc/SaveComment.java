package command_etc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.CommonUtil;
import dao.EtcDao;
import dto.EtcDto;

/**
 * Servlet implementation class SaveComment
 */
@WebServlet("/SaveComment")
public class SaveComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		EtcDao dao = new EtcDao();
		
		String no = dao.getMaxNo();
		String group_no = request.getParameter("t_no");
		int depth = 1;
		String title = request.getParameter("t_comment");
		//System.out.println("d"+group_no);
		//System.out.println("d"+title);
		String content = "";
		String id = (String)session.getAttribute("sessionId");
		String reg_date = CommonUtil.getTodayTime();
		
		EtcDto dto = new EtcDto(no, group_no, depth, title, content, id, reg_date);
		
		int result = dao.getSave(dto);
		
		String msg = "등록성공!";
		if(result == 0) msg = "등록실패!";
		out.print(msg);		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

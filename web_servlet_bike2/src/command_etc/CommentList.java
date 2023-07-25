package command_etc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.EtcDao;
import dto.EtcDto;

/**
 * Servlet implementation class CommentList
 */
@WebServlet("/CommentList")
public class CommentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentList() {
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
		
		String g_no = request.getParameter("t_no");
		System.out.println(g_no+"======================================");
		
		JSONObject fobj = new JSONObject();
		JSONArray jarr = new JSONArray();
		JSONObject jsub = new JSONObject();
		HashMap<String, Object> hM = new HashMap<String, Object>();
		
		ArrayList<EtcDto> arr = dao.getComment(g_no);
		
		for(int k=0; k<arr.size(); k++) {
			hM = new HashMap<String, Object>();
			String no = arr.get(k).getNo();
			hM.put("no", no);
			String title = arr.get(k).getTitle();
			hM.put("title", title);
			String group_no = arr.get(k).getGroup_no();
			hM.put("group_no", group_no);
			String name = arr.get(k).getReg_name();
			hM.put("name", name);
			String date = arr.get(k).getReg_date();
			hM.put("date", date);
			
			jsub = new JSONObject(hM);
			jarr.add(jsub);
		}
		fobj.put("t_arr", jarr);
		
		out.print(fobj);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package snack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SnackDao;
import dto.SnackDto;

/**
 * Servlet implementation class DBSnackUpdate
 */
@WebServlet("/DBSnackUpdate")
public class DBSnackUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBSnackUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SnackDao dao = new SnackDao();
		request.setCharacterEncoding("utf-8");
		
		String code = request.getParameter("t_code");
		String name = request.getParameter("t_name");
		String price = request.getParameter("t_price");
		price = price.replaceAll(" ", "");
		String com = request.getParameter("t_com");
		int iprice = Integer.parseInt(price);
		
		SnackDto dto = new SnackDto(code, name, iprice, com);
		
		int result = dao.getSnackUpdate(dto);
		String msg = "수정성공!";
		String url = "SnackList";
		if(result != 1) {
			msg = "수정실패!";
			url = "SnackWrite";
		}
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);
		
		RequestDispatcher rd = request.getRequestDispatcher("common_alert.jsp");
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

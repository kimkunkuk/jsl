package test;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Tdto;

/**
 * Servlet implementation class Testtwo
 */
@WebServlet("/Testtwo")
public class Testtwo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Testtwo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = "홍길동";
		int age = 25;
		Tdto dto = new Tdto("김","평",39);
		ArrayList<Tdto> dtos = new ArrayList<Tdto>();
		dtos.add(new Tdto("바이든","워싱턴",80));
		dtos.add(new Tdto("푸틴","러시아",70));
		
		request.setAttribute("t_name", name); // 넘어갈변수명, 담을변수
		request.setAttribute("t_age", age);
		request.setAttribute("t_dto", dto);
		request.setAttribute("t_dtos", dtos);
		
		RequestDispatcher rd = request.getRequestDispatcher("test_two.jsp"); //jsp파일주소 연결
		rd.forward(request, response); //jsp파일에 전송
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

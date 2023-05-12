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
 * Servlet implementation class El_jstl
 */
@WebServlet("/El_jstl")
public class El_jstl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public El_jstl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name="홍길동";
		int age = 25;
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("김");
		arr.add("이");
		arr.add("박");
		arr.add("최");
		arr.add("정");
		
		
		ArrayList<Tdto> dtos = new ArrayList<>();
		dtos.add(new Tdto("홍길동","대전",25));
		dtos.add(new Tdto("김길동","부산",20));
		dtos.add(new Tdto("최길동","도쿄",90));
		
		
		request.setAttribute("t_name", name);
		request.setAttribute("t_age", age);
		request.setAttribute("t_arr", arr);
		request.setAttribute("t_dtos", dtos);
		
		RequestDispatcher rd = request.getRequestDispatcher("el_jstl.jsp");
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

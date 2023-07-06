package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;

import dao.ProductDao;
import dto.ProductDto;

/**
 * Servlet implementation class SaleCheck
 */
@WebServlet("/SaleCheck")
public class SaleCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*response.setContentType("text/html; charset=utf-8");
		ProductDao dao = new ProductDao();
		PrintWriter out = response.getWriter();
		String id = request.getParameter("t_id");
		
		ProductDto dto = dao.getSaleMember(id);
		
		ArrayList<ProductDto> t_dto = new ArrayList<>();
		
		
		out.print(dto.getName());*/
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String id = request.getParameter("t_id");
		response.getWriter().write(getJSON(id));
	}

	public String getJSON(String id) {
		StringBuffer result = new StringBuffer("");
		result.append("{\result\":[");
		
		ProductDao dao = new ProductDao();
		ArrayList<ProductDto> arr = dao.getSaleMember(id);
		for(int i=0; i<arr.size(); i++) {
			result.append("[{\"value\": \""+arr.get(i).getName() +"\"},");
			result.append("{\"value\": \""+arr.get(i).getAddress() +"\"},");
			result.append("{\"value\": \""+arr.get(i).getMobile_1() +"\"},");
			result.append("{\"value\": \""+arr.get(i).getMobile_2() +"\"},");
			result.append("{\"value\": \""+arr.get(i).getMobile_3() +"\"}],");
		}
		result.append("]}");
		return result.toString();
	}
}

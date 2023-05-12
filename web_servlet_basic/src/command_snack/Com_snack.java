package command_snack;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.SnackDao;
import dto.SnackDto;

public class Com_snack {
	
	public void executeList(HttpServletRequest request) {
		SnackDao dao = new SnackDao();
		
		String select = request.getParameter("t_select");
		String search = request.getParameter("t_search");
		String com = request.getParameter("com");
		if(select == null){ // null로 넘어가서 조회 값 이상해지는거 방지
			select = "p_name";
			search = "";
		}
		if(com == null) com="all";
		if(com.equals("all")) com = "";
		ArrayList<SnackDto> dtos = dao.getSnackList(select, search, com);
		request.setAttribute("t_dtos", dtos);
		request.setAttribute("t_select", select);
		request.setAttribute("t_search", search);
		request.setAttribute("t_com", com);
	}
	
	public void executeWriteSave(HttpServletRequest request) {
		SnackDao dao = new SnackDao();
		
		String code = request.getParameter("t_code");
		String name = request.getParameter("t_name");
		String price = request.getParameter("t_price");
		String com = request.getParameter("t_com");
		int iprice = Integer.parseInt(price);
		
		SnackDto dto = new SnackDto(code, name, iprice, com);
		int count = dao.snakcSaveCount(code);
		int result = dao.snackSave(dto);
		String msg = "등록성공!";
		String url = "Snack";
		if(result != 1) {
			msg = "등록실패!";
			url = "Snack";
			if(count == 1) msg = "중복된 제품번호";
		}
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);
	
	}
	
	public void executeView(HttpServletRequest request) {
		SnackDao dao = new SnackDao();
		
		String code = request.getParameter("t_code");
		SnackDto dto = dao.getSnackView(code);
		
		request.setAttribute("t_dto", dto);
	}
	
	public void executeUpdateForm(HttpServletRequest request) {
		SnackDao dao = new SnackDao();
		String code = request.getParameter("t_code");
		SnackDto dto = dao.getSnackView(code);
		
		request.setAttribute("t_dto", dto);
	}
	
	public void executeUpdateSave(HttpServletRequest request) {
		SnackDao dao = new SnackDao();
		
		String code = request.getParameter("t_code");
		String name = request.getParameter("t_name");
		String price = request.getParameter("t_price");
		price = price.replaceAll(" ", "");
		String com = request.getParameter("t_com");
		int iprice = Integer.parseInt(price);
		
		SnackDto dto = new SnackDto(code, name, iprice, com);
		
		int result = dao.getSnackUpdate(dto);
		String msg = "수정성공!";
		String url = "Snack";
		if(result != 1) {
			msg = "수정실패!";
			url = "Snack";
		}
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);
	}
	
	public void executeDelete(HttpServletRequest request) {
		SnackDao dao = new SnackDao();
		String code = request.getParameter("t_code");
		int result = dao.getDelete(code);
		String msg = "삭제성공!";
		String url = "Snack";
		if(result != 1) {
			msg = "삭제실패!";
			url = "Snack";
		}
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);
		
	}
}

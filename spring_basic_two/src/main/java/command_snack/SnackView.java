package command_snack;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import dao.SnackDao;
import dto.SnackDto;

public class SnackView implements CommonExecute {

	@Override
	public void execute(HttpServletRequest req) {
		SnackDao dao = new SnackDao();
		
		String code = req.getParameter("t_code");
		
		SnackDto dto = dao.getSnackView(code);
		
		ArrayList<SnackDto> arr = dao.getCompanyList();
		
		req.setAttribute("t_dto", dto);
		req.setAttribute("t_arr", arr);
	}

}

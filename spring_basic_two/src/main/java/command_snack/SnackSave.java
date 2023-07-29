package command_snack;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import dao.SnackDao;
import dto.SnackDto;

public class SnackSave implements CommonExecute {

	@Override
	public void execute(HttpServletRequest req) {
		SnackDao dao = new SnackDao();
		
		String code = req.getParameter("t_code");
		String name = req.getParameter("t_name");
		String price = req.getParameter("t_price");
		int I_price = Integer.parseInt(price);
		String com = req.getParameter("t_com");
		
		SnackDto dto = new SnackDto(code, name, I_price, com);
		
		int result = dao.snackSave(dto);
		String msg = "등록성공!";
		if(result == 0) msg = "등록실패!";
		
		req.setAttribute("t_msg", msg);
		req.setAttribute("t_url", "Snack");

	}

}

package command_snack;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import dao.SnackDao;

public class SnackDelete implements CommonExecute {

	@Override
	public void execute(HttpServletRequest req) {
		SnackDao dao = new SnackDao();
		
		String code = req.getParameter("t_code");
		
		int result = dao.getDelete(code);
		String msg = "삭제성공!";
		if(result == 0) msg = "삭제실패!";
		
		req.setAttribute("t_msg", msg);
		req.setAttribute("t_url", "Snack");
	}

}

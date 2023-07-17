package comman_free;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import dao.FreeDao;
import dto.FreeDto;

public class FreeList implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		FreeDao dao = new FreeDao();
		
		ArrayList<FreeDto> arr = dao.getFreeList();
		
		request.setAttribute("t_arr", arr);
	}

}

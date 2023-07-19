package comman_free;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import common.CommonUtil;
import dao.FreeDao;

public class FreeDelete implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		FreeDao dao = new FreeDao();
		String no = request.getParameter("t_no");
		String attach = request.getParameter("t_attach");
		String freeDir = CommonUtil.getFild_dir_freeboard();
		
		int result = dao.getFreeDelete(no);
		String msg = "삭제성공!";
		
		if(result == 0) { 
			msg = "삭제실패!";
		}else {
			File file = new File(freeDir, attach);
			file.delete();
		}
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "FreeBoard");
		
	}

}

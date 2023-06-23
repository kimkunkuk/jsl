package command_product;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import common.CommonUtil;
import dao.ProductDao;

public class ProductDelete implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		ProductDao dao = new ProductDao();
		String productDir = CommonUtil.getFild_dir_product();
		String no = request.getParameter("t_no");
		String attach = request.getParameter("t_attach");
		
		int result = dao.getDelete(no);
		String msg = "삭제성공!";
		
		if(result == 0) {
			msg = "삭제실패!";
		}else {
			File file = new File(productDir, attach);
			file.delete();
		}
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Product");
	}

}

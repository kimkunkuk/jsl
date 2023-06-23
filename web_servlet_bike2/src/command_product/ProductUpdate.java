package command_product;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.CommonExcute;
import common.CommonUtil;
import common.commonToday;
import dao.ProductDao;
import dto.ProductDto;

public class ProductUpdate implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		ProductDao dao = new ProductDao();
		ProductDto dto = new ProductDto();
		
		String productDir = CommonUtil.getFild_dir_product();
		int maxSize = 1024*1024*5;
		MultipartRequest mpr = null;
		
		try {
			mpr = new MultipartRequest(request, productDir, maxSize, "utf-8", new DefaultFileRenamePolicy());
			String no = mpr.getParameter("t_no");
			String title = mpr.getParameter("t_title");
			String content = mpr.getParameter("t_content");
			String attach = mpr.getFilesystemName("t_attach");
			String ori_attach = mpr.getParameter("t_oriAttach");
			if(attach == null) {
				attach = ori_attach;
			}else {
				File file = new File(productDir, ori_attach);
				file.delete();
			}
			String price = mpr.getParameter("t_price");
			String size = mpr.getParameter("t_p_size");
			String level = mpr.getParameter("t_level");
			String reg_date = CommonUtil.getTodayTime();
			
			dto = new ProductDto(no, title, content, attach, reg_date, price, size, level);
			System.out.println("ori: "+ori_attach);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int result = dao.getUpdate(dto);
		String msg = "수정성공!";
		
		if(result == 0) msg = "수정실패!";
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Product");
	}	

}

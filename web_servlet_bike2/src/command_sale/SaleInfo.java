package command_sale;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import dao.SaleDao;
import dto.ProductDto;

public class SaleInfo implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		SaleDao dao = new SaleDao();
		
		ArrayList<ProductDto> arr = dao.saleInfoList();
		
		request.setAttribute("t_arr", arr);
	}

}

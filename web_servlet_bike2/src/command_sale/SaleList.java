package command_sale;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import common.CommonUtil;
import dao.SaleDao;
import dto.ProductSaleDto;

public class SaleList implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		SaleDao dao = new SaleDao();
		
		String select = request.getParameter("t_select");
		String search = request.getParameter("t_search");
		String state  = request.getParameter("t_state");
		if(select == null) {
			select = "s_no";
			search = "";
		}
		if(state == null) {
			state = "";
		}
		String display_count = request.getParameter("t_display_count");
		if(display_count == null) {
			display_count = "10";
		}
		int line = Integer.parseInt(display_count);
		/* paging 설정 start*/
		int totalCount = dao.getTotalCount(select,search,state);
		int list_setup_count = line;  //한페이지당 출력 행수 
		int pageNumber_count = 3;  //한페이지당 출력 페이지 갯수
		
		String nowPage = request.getParameter("t_nowPage");
		int current_page = 0; // 현재페이지 번호
		int total_page = 0;    // 전체 페이지 수
		
		if(nowPage == null || nowPage.equals("")) current_page = 1; 
		else current_page = Integer.parseInt(nowPage);
		
		total_page = totalCount / list_setup_count;  // 몫 : 2
		int rest = 	totalCount % list_setup_count;   // 나머지:1
		if(rest !=0) total_page = total_page + 1;     // 3
		
		int start = (current_page -1) * list_setup_count + 1;
		int end   = current_page * list_setup_count;
		/* paging 설정 end*/
		
		int order = totalCount - ((current_page - 1) * list_setup_count);
		
		ArrayList<ProductSaleDto> arr = dao.getSaleList(select, search, state);
		
		String paging = CommonUtil.pageListPost(current_page, total_page, pageNumber_count);
		
		request.setAttribute("t_arr", arr);
		request.setAttribute("t_search", search);
		request.setAttribute("t_select", select);
		request.setAttribute("t_totalcount", totalCount);
		request.setAttribute("t_display_count", display_count);
		request.setAttribute("t_order", order);
		request.setAttribute("t_paging", paging);
	}

}

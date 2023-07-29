package command_member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import dao.MemberDao;
import dto.MemberDto;

public class MemberList implements CommonExecute {

	@Override
	public void execute(HttpServletRequest req) {
		MemberDao dao = new MemberDao();
		
		String gubun = req.getParameter("t_select");
		String search = req.getParameter("t_search");
		if(gubun == null) {
			gubun = "id";
			search = "";
		}
		ArrayList<MemberDto> arr = dao.getMemberList(gubun, search);
		
		req.setAttribute("t_arr", arr);
		req.setAttribute("t_select", gubun);
		req.setAttribute("t_search", search);
	}

}

package kr.com.sts;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import command_member.MemberDelete;
import command_member.MemberList;
import command_member.MemberSave;
import command_member.MemberUpdateSave;
import command_member.MemberView;
import common.CommonExecute;
import dao.MemberDao;
import dto.MemberDto;

@Controller
public class Member {
	
	@RequestMapping("Member")
	public String member(HttpServletRequest req) {
		String gubun = req.getParameter("t_gubun");
		if(gubun == null) gubun = "list";
		String viewPage = "";
		
		if(gubun.equals("list")) {
			//리스트
			CommonExecute mem = new MemberList();
			mem.execute(req);
			viewPage = "/memberMVC/member_list";
		
		}else if(gubun.equals("write")) {
			//등록폼	
			viewPage = "/memberMVC/member_write";
			
		}else if(gubun.equals("save")) {
			//저장
			CommonExecute mem = new MemberSave();
			mem.execute(req);
			viewPage = "/common_alert";
		}else if(gubun.equals("view")) {
			//멤버 상세보기 뷰
			CommonExecute mem = new MemberView();
			mem.execute(req);
			viewPage = "/memberMVC/member_view";
		}else if(gubun.equals("delete")) {
			//삭제
			CommonExecute mem = new MemberDelete();
			mem.execute(req);
			viewPage = "/common_alert";
		}else if(gubun.equals("updateForm")) {
			//수정폼
			CommonExecute mem = new MemberView();
			mem.execute(req);
			viewPage = "/memberMVC/member_update";
		}else if(gubun.equals("updateSave")) {
			//수정저장
			CommonExecute mem = new MemberUpdateSave();
			mem.execute(req);
			viewPage = "/common_alert";
		}
		
		return viewPage;
	}
	
	
	
	//등록
	/*@RequestMapping("memberSave")
	public String memberSave(HttpServletRequest req) {
		MemberDao dao = new MemberDao();
		String id = req.getParameter("t_id");
		String name = req.getParameter("t_name");
		String age = req.getParameter("t_age");
		String reg_date = req.getParameter("t_date");
		
		MemberDto dto = new MemberDto(id, name, Integer.parseInt(age), reg_date);
		
		int result = dao.memberSave(dto);
		String msg = "등록성공!";
		if(result == 0) msg = "등록실패!";
		
		req.setAttribute("t_msg", msg);
		req.setAttribute("t_url", "memberList");
		return "/common_alert";
	}
	
	//글쓰기폼
	@RequestMapping("memberWrite")
	public String memberWrite() {
		return "/member/member_write";
		
	}
	
	//리스트
	@RequestMapping("memberList")
	public String memberList(HttpServletRequest req) {
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
		return "/member/member_list";
	}
	
	//멤버 상세보기
	@RequestMapping("memberView")
	public String memberView(HttpServletRequest req) {
		MemberDao dao = new MemberDao();
		
		String id = req.getParameter("t_id");
		
		MemberDto dto = dao.getMemberView(id);
		
		req.setAttribute("t_dto", dto);
		
		return "/member/member_view";
		
	}
	
	//멤버수정 form
	@RequestMapping("memberUpdate")
	public String memberUpdate(HttpServletRequest req) {
		MemberDao dao = new MemberDao();
		
		String id = req.getParameter("t_id");
		
		MemberDto dto = dao.getMemberView(id);
		
		req.setAttribute("t_dto", dto);
		
		return "/member/member_update";
	}
	
	//멤버수정 저장
	@RequestMapping("memberUpdateSave")
	public String memberUpdateSave(HttpServletRequest req) {
		MemberDao dao = new MemberDao();
		
		String id = req.getParameter("t_id");
		String name = req.getParameter("t_name");
		String age = req.getParameter("t_age");
		String reg_date = req.getParameter("t_date");
		
		MemberDto dto = new MemberDto(id, name, Integer.parseInt(age), reg_date);
		
		int result = dao.memberUpdate(dto);
		String msg = "수정성공!";
		if(result == 0) msg = "수정실패!";
		
		//String msg = result == 1? "수정성공!" : "수정실패!";
		
		req.setAttribute("t_msg", msg);
		req.setAttribute("t_url", "memberView?t_id="+id);
		return "/common_alert";
		
	}
	
	//멤버삭제
	@RequestMapping("memberDelete")
	public String memberDelete(HttpServletRequest req) {
		MemberDao dao = new MemberDao();
		
		String id = req.getParameter("t_id");
		
		int result = dao.getDelete(id);
		
		String msg = "삭제성공!";
		if(result == 0) msg = "삭제실패!";
		
		req.setAttribute("t_msg", msg);
		req.setAttribute("t_url", "memberList");
		//return "/common_alert";
		return "redirect:memberList"; //알럿창 으로 안띄우고 바로 리스트로 jsp파일 찾는게 아니라 맵핑 찾아라
	}*/
}

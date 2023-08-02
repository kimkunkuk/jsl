package com.kr.sps;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import command_member.MemberDelete;
import command_member.MemberList;
import command_member.MemberSave;
import command_member.MemberUpdateSave;
import command_member.MemberView;
import common.CommonExecute;
import common.CommonTemplate;
import dto.MemberDto;

@Controller
public class MemberController {
	
	@Autowired
	MemberDto memDto;
	
	@Autowired
	JdbcTemplate template;
	
	@Autowired
	public void setTemplate() {
		CommonTemplate.setTemplate(template);
	}
	
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
		
		/*String query = "select id, name, age, to_char(reg_date, 'yyyy-MM-dd') as reg_date from h_이주형_member\r\n" + 
				"where id = '102'";
		RowMapper<MemberDto> memDto = 
				new BeanPropertyRowMapper<MemberDto>(MemberDto.class);
		
		MemberDto dto = template.queryForObject(query, memDto);
		
		System.out.println("id:"+dto.getId());
		System.out.println("name:"+dto.getName());
		System.out.println("age:"+dto.getAge());
		System.out.println("reg_date"+dto.getReg_date());
		
		String query="select id, name, age, to_char(reg_date, 'yyyy-MM-dd')as reg_date "
				+ "from h_이주형_member\r\n" + 
				" order by reg_date desc";
		
		RowMapper<MemberDto> memDtoList = 
				new BeanPropertyRowMapper<MemberDto>(MemberDto.class);
		
		ArrayList<MemberDto> arr = (ArrayList<MemberDto>) template.query(query, memDtoList);
		
		System.out.println("회원수:"+arr.size());
		
		query = "delete from h_이주형_member where id = '101'";
		
		int result = template.update(query);
		
		System.out.println("삭제: "+result);
		
		
		
		return "home";*/
	}
}

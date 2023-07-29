package kr.com.sts;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import command_snack.SnackDelete;
import command_snack.SnackList;
import command_snack.SnackSave;
import command_snack.SnackUpdateSave;
import command_snack.SnackView;
import common.CommonExecute;

@Controller
public class Snack {

	@RequestMapping("Snack")
	public String Snack(HttpServletRequest req) {
		String gubun = req.getParameter("t_gubun");
		if(gubun == null) gubun = "list";
		String viewPage="";
		
		if(gubun.equals("list")) {
			//리스트
			CommonExecute snack = new SnackList();
			snack.execute(req);
			viewPage="/snack/snack_list";
			
		}else if(gubun.equals("writeForm")) {
			//작성폼
			viewPage="/snack/snack_write";
			
		}else if(gubun.equals("writeSave")) {
			//글 저장
			CommonExecute snack = new SnackSave();
			snack.execute(req);
			viewPage="common_alert";
			
		}else if(gubun.equals("view")) {
			//상세보기 뷰
			CommonExecute snack = new SnackView();
			snack.execute(req);
			viewPage="/snack/snack_view";
			
		}else if(gubun.equals("updateForm")) {
			//수정폼
			CommonExecute snack = new SnackView();
			snack.execute(req);
			viewPage="/snack/snack_update";
			
		}else if(gubun.equals("updateSave")) {
			//수정 저장
			CommonExecute snack = new SnackUpdateSave();
			snack.execute(req);
			viewPage="common_alert";
			
		}else if(gubun.equals("delete")) {
			//삭제
			CommonExecute snack = new SnackDelete();
			snack.execute(req);
			viewPage="common_alert";
		}
		
		return viewPage;
	}
	
}

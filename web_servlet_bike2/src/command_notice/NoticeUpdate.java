package command_notice;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;



import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.CommonExcute;
import common.CommonUtil;
import dao.NoticeDao;
import dto.NoticeDto;

public class NoticeUpdate implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		NoticeDao dao = new NoticeDao();
		NoticeDto dto = new NoticeDto();
		String noticeDir = CommonUtil.getFile_dir_notice();
		int maxSize = 1024*1024*10;
		
		MultipartRequest mpr;
		
		try {
			mpr = new MultipartRequest(request, noticeDir, maxSize, "utf-8", new DefaultFileRenamePolicy() );
			String no = mpr.getParameter("t_no");
			String title = mpr.getParameter("t_title");
			String content = mpr.getParameter("t_content");
			String attach = mpr.getFilesystemName("t_attach");
			if(attach == null) attach = "";
			
			String delAttach = mpr.getParameter("t_del_attach");
			String oriAttach = mpr.getParameter("t_ori_attach");
			if(oriAttach == null) oriAttach = "";
			String saveAttach = "";
			
			if(delAttach != null) {
				File file = new File(noticeDir, delAttach);
				boolean tf = file.delete();
				if(!tf) {
					System.out.print("공지사항 첨부파일삭제:" + tf);
				}
			}else {
				saveAttach = oriAttach;
			}
			
			//새로운 파일 등록
			if(!attach.equals("")) {
				if(!oriAttach.equals("")) {
					File file = new File(noticeDir, oriAttach);
					boolean tf = file.delete();
				}
				saveAttach = attach;
			}
			
			dto = new NoticeDto(no, title, content, saveAttach);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		int result = dao.getUpdate(dto);
		String msg = "수정성공!";
		if(result == 0) msg = "수정실패!";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Notice");
		
	}

}

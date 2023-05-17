<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*,dto.*,common.*"%>
<%@ include file = "../common_session.jsp" %>
<%
	request.setCharacterEncoding("utf-8");
	NoticeDao dao = new NoticeDao();
	if(!sessionLevel.equals("top")){
%>
<script>
	alert("로그인 정보만료!, 노관리자");
	location.href="notice.jsp";
</script>
<% 
		
	}else{ 
		
		
	String noticeDir = CommonUtil.getFile_dir_notice(); //첨부파일 등록될 폴더경로
	int maxSize = 1024 * 1024 * 10; // 10메가바이트    1024 1키로바이트 (첨부파일 최대사이즈 1메가 *10 = 10메가)
	
	MultipartRequest mpr = new MultipartRequest(request, noticeDir, maxSize, "utf-8", new DefaultFileRenamePolicy());
 	//new DefaultFileRenamePolicy() 중복되는 파일 덮어쓰기 방지
	
		String no = dao.getMaxNo();
		String title = mpr.getParameter("t_title");
		title = title.replaceAll("\'", "\''");
		String content = mpr.getParameter("t_content");
		content = content.replaceAll("\'", "\''");
		String attach = mpr.getFilesystemName("t_attach"); //input 타입이 file 인거 이 메소드로 받아오기
		if(attach == null) attach = "";
		String reg_id = sessionId;
		//String reg_date = CommonUtil.getToday();
		String reg_date = CommonUtil.getTodayTime();
		
		NoticeDto dto = new NoticeDto(no, title, content, attach, reg_id, reg_date);
		
		int result = dao.noticeSave(dto);
		String msg = "등록 되었습니다.";
		if(result != 1) msg = "등록실패";
	
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	alert("<%=msg%>");
	location.href="notice.jsp";
</script>
</head>
<body>

</body>
</html>
<% } %>
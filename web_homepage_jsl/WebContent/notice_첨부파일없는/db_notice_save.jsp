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
	
		String no = dao.getMaxNo();
		String title = request.getParameter("t_title");
		title = title.replaceAll("'", "''");
		String content = request.getParameter("t_content");
		content = content.replaceAll("'", "''");
		String attach = request.getParameter("t_attach");
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
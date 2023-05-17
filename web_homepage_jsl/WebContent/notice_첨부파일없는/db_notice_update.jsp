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
<% 	} else {
	String no = request.getParameter("t_no");
	String title = request.getParameter("t_title");
	title = title.replaceAll("'", "''");
	String content = request.getParameter("t_content");
	content = content.replaceAll("'", "''");
	String attach = request.getParameter("t_attach");
	
	
	NoticeDto dto = new NoticeDto(no, title, content, attach);
	int result = dao.getUpdate(dto);
	String msg = "수정되었습니다.";
	if(result != 1) msg = "수정실패!";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	alert("<%=msg %>");
	location.href="notice.jsp"
</script>
</head>
<body>
	
</body>
</html>
<% } %>
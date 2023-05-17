<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*,dto.*,common.*" %>
<%@ include file="../common_session.jsp" %>
<%
	FaqDao dao = new FaqDao();
	request.setCharacterEncoding("utf-8");
	
	String no = request.getParameter("t_no");
	String title = request.getParameter("t_title");
	title = title.replaceAll("\'", "\''");
	String content = request.getParameter("t_content");
	content = content.replaceAll("\'", "\''");
	
	int result = dao.getFaqUpdate(no, title, content);
	String msg = "수정실패!!!";
	if(result == 1) msg = "수정성공!!!";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	alert("<%=msg%>");
	location.href="faq_list.jsp";
</script>
</head>
<body>

</body>
</html>
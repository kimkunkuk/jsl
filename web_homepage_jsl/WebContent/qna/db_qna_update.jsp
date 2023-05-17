<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*,dto.*" %>
<% %>
<%
	QnaDao dao = new QnaDao();
	request.setCharacterEncoding("utf-8");
	
	String title = request.getParameter("t_title");
	title = title.replaceAll("'", "''");
	
	String content = request.getParameter("t_content");
	content = content.replaceAll("'", "''");
	
	String no = request.getParameter("t_no");
	
	int result = dao.getQnaUpdate(title, content, no);
	String msg = "질문 수정실패!!!";
	if(result == 1) msg = "질문 수정성공!!!";
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	alert("<%=msg%>");
	location.href="qna_list.jsp";
</script>
</head>
<body>

</body>
</html>
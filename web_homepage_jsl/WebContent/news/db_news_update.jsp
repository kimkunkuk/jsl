<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.*,dao.*,common.*" %>
<%
	request.setCharacterEncoding("utf-8");
	NewsDao dao = new NewsDao();
	String no	 = request.getParameter("t_no");
	String title = request.getParameter("t_title");
	title = title.replaceAll("'", "''");
	String content = request.getParameter("t_content");
	content = content.replaceAll("'", "''");
	String update_date = CommonUtil.getTodayTime();
	
	NewsDto dto = new NewsDto(no, title, content, update_date);
	int result = dao.getNewsUpdate(dto);
	String msg = "수정실패!";
	if(result == 1) msg="수정성공!";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	alert("<%=msg%>");
	location.href="news.jsp";
</script>
</head>
<body>

</body>
</html>
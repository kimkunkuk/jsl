<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dao.*,dto.*" %>
<%
	QnaDao dao = new QnaDao();
	String no = request.getParameter("t_no");
	int result = dao.getQnaDelete(no);
	
	String msg = "삭제실패!!!";
	if(result == 1) msg = "삭제성공!!!";
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
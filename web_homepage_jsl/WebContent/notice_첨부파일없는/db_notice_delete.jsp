<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>
<%
	NoticeDao dao = new NoticeDao();
	String no = request.getParameter("t_no");
	
	int result = dao.getDelete(no);
	String msg = "삭제되었습니다.";
	if(result != 1) msg = "삭제실패!";
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
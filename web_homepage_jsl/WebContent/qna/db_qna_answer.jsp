<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*,dto.*,common.*" %>
<%
	QnaDao dao = new QnaDao();
	request.setCharacterEncoding("utf-8");
	
	String no = request.getParameter("t_no");
	String answer = request.getParameter("t_answer");
	String qna_date = CommonUtil.getTodayTime();
	
	int result = dao.getSaveAnswer(no , answer, qna_date);
	
	String msg = "답변등록실패!!!";
	if(result == 1)msg="답변등록성공!!!";
	
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
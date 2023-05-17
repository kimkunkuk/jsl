<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*,dto.*,common.*" %>
<%@ include file="../common_session.jsp" %>
<%
	request.setCharacterEncoding("utf-8");
	QnaDao dao = new QnaDao();
	
	String no = dao.getMaxNo();
	String title = request.getParameter("t_title");
	title = title.replaceAll("'", "''");
	
	String content = request.getParameter("t_content");
	content = content.replaceAll("'", "''");
	
	String reg_id = sessionId;
	String reg_date = CommonUtil.getTodayTime();
	
	QnaDto dto = new QnaDto(no, title, content, reg_id, reg_date);
	int result = dao.getQnaSave(dto);
	
	String msg = "등록실패!!!";
	if(result == 1) msg = "등록성공!!!";
	
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
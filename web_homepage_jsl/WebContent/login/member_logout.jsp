<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String sessionName = (String)session.getAttribute("sessionName");
	
	String msg = "로그아웃 되었습니다.";
	if(sessionName != null){
		msg = sessionName + "님 로그아웃 되었습니다.";
	}
	
	session.invalidate();//세션값 없애기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	alert("<%=msg%>");
	location.href="../index.jsp";
</script>
<body>

</body>
</html>
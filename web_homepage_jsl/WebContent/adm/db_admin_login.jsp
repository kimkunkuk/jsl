<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*,dto.*" %>
<%
	AdminDao dao = new AdminDao();
	request.setCharacterEncoding("utf-8");
	
	String id = request.getParameter("t_id");
	String pw = request.getParameter("t_pw");
	
	pw = dao.encryptSHA256(pw);
	String name = dao.checkId(id,pw);
	String msg = "";
	String url = "";
	if(name.equals("")){
		msg = "id/비밀번호를 확인해주세요!";
		url = "admin.jsp";
	} else if(id.equals("manager")) {
		msg = "관리자 회원목록";
		url ="admin_member_list.jsp";
	} else {
		msg = "관리자가 아닙니다.";
		url = "../index.jsp";
		session.invalidate();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	alert("<%=msg%>");
	location.href="<%=url%>";
</script>
<body>

</body>
</html>
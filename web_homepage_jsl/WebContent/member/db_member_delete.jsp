<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*,dto.*,common.*" %>
<%
	MemberDao dao = new MemberDao();
	String id = request.getParameter("t_id");
	String pw = request.getParameter("t_pw1");
	String date = CommonUtil.getTodayTime();
	pw = dao.encryptSHA256(pw);
	
	int count = dao.getCheckPassword(id, pw);
	out.print(count);
	int result = 0;
	if(count == 1){
		result = dao.getMemberDelete(id, date);
		session.invalidate();
	}
	
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	if(<%=count%>!=1){
		alert("비밀번호 불일치!");
		window.history.back();
	}
	if(<%=result%>==1){
		alert("탈퇴성공!");
		location.href="../index.jsp";
	}else {
		alert("탈퇴실패!");
		window.history.back();
	}
</script>
</head>
<body>

</body>
</html>
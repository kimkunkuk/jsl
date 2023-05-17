<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*,common.*" %>
<%

	MemberDao dao = new MemberDao();
	String id = request.getParameter("t_id");
	String pw = request.getParameter("t_password");
	
	String acount = dao.checkAcount(id);
	
	pw = dao.encryptSHA256(pw);
	String name = dao.checkLogin(id, pw);
	String msg="";
	String url="";
	if(name.equals("") || acount.equals("n")){
		msg = "ID나 비밀번호를 확인하세요";
		url = "member_login.jsp";
	} else {
		msg = name+"님 환영합니다.";
		url = "../index.jsp";
		session.setAttribute("sessionId", id);
		session.setAttribute("sessionName", name);
		
		if(id.equals("manager")){
			session.setAttribute("sessionLevel", "top");
		}else{
			session.setAttribute("sessionLevel", "member");
		}
		
		session.setMaxInactiveInterval(60*60); //session 시간
		String login_date = CommonUtil.getTodayTime();
		dao.getLogin_date(login_date, id);
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	alert("<%=msg%>");
	location.href="<%=url%>";
</script>
</head>
<body>

</body>
</html>
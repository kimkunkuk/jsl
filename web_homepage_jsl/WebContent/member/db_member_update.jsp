<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*,dto.*"%>
<%
	request.setCharacterEncoding("utf-8");
	MemberDao dao = new MemberDao();
	
	String name = request.getParameter("t_name");
	String id = request.getParameter("t_id");
	String job = request.getParameter("t_job");
	String tell_1 = request.getParameter("t_tell1");
	String tell_2 = request.getParameter("t_tell2");
	String tell_3 = request.getParameter("t_tell3");
	String mobile_1 = request.getParameter("t_mobile1");
	String mobile_2 = request.getParameter("t_mobile2");
	String mobile_3 = request.getParameter("t_mobile3");
	String mobile = mobile_1 + mobile_2 + mobile_3;
	String email = request.getParameter("t_email");
	String pw = request.getParameter("t_pw1");
	pw = dao.encryptSHA256(pw);
	MemberDto dto = new MemberDto(id, name, pw, job, tell_1, tell_2, tell_3, mobile, email,"");
	int count = dao.getCheckPassword(id, pw);
	
	int result = 0;
	if(count == 1){
		result = dao.getMemberUpdate(dto);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	if(<%=count%> != 1) {
		alert("비밀번호 불일치");
		window.history.back();
	}
	if(<%=result%> == 1){
		alert("수정성공");
		location.href="member_myinfo.jsp";
	}else {
		alert("수정실패");
		window.history.back();
	}
	
</script>
</head>
<body>

</body>
</html>
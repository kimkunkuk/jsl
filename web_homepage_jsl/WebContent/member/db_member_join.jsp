<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*,dto.*,common.CommonUtil" %>
<%
	request.setCharacterEncoding("utf-8");
	MemberDao dao = new MemberDao();
	String name = request.getParameter("t_name");
	String id = request.getParameter("t_id");
	String pw = request.getParameter("t_pw2");
	int pwlen = pw.length();
	pw = dao.encryptSHA256(pw);
	String job = request.getParameter("t_job");
	String tell_1 = request.getParameter("t_tell1");
	String tell_2 = request.getParameter("t_tell2");
	String tell_3 = request.getParameter("t_tell3");
	String mobile_1 = request.getParameter("t_mobile1");
	String mobile_2 = request.getParameter("t_mobile2");
	String mobile_3 = request.getParameter("t_mobile3");
	String mobile = mobile_1 + mobile_2 + mobile_3;
	String email = request.getParameter("t_email");
	String reg_date = CommonUtil.getTodayTime();
	
	MemberDto dto = new MemberDto(id, name, pw, job, tell_1, tell_2, tell_3, mobile, email, reg_date, pwlen);
	int result = dao.memberJoin(dto);
	String msg = name +"님 환영합니다.";
	if(result != 1) msg ="회원가입 실패! 관리자에게 문의바립니다.";
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	alert("<%=msg%>");
	location.href="../index.jsp";
</script>
</head>
<body>
	
</body>
</html>
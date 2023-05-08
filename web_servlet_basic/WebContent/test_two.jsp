<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.*,java.util.*" %>
<%
	String name = (String)request.getAttribute("t_name");
	int age = (int)request.getAttribute("t_age");
	Tdto dto = (Tdto)request.getAttribute("t_dto");
	ArrayList<Tdto> dtos = (ArrayList<Tdto>)request.getAttribute("t_dtos");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=name %> <br>
<%=age %> <br>
<%=dto.getName() %> <br>
<%=dto.getArea() %> <br>
<%=dto.getAge() %> <br>

<%for(Tdto dt : dtos){ %>
	<%=dt.getName() %><%=dt.getArea() %><%=dt.getAge() %>
<%} %>
</body>
</html>
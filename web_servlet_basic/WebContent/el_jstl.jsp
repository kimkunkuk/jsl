<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <!-- jstl import 개념 -->
<%
	//String name = (String)request.getAttribute("t_name");
	//jstl 태그 라이브러리 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
====================== el표현  =========================<br>
성명: ${t_name}<br>
나이: ${t_age }<br>
배열: ${t_arr }<br>
arr size: ${t_arr.size() }<br>
arr 1: ${t_arr[0] }<br>
arr 2: ${t_arr[1] }<br>
arr 3: ${t_arr[2] }<br>
arr 4: ${t_arr[3] }<br>
arr 5: ${t_arr[4] }<br>
arr 6: ${t_arr[5] }<br><br>
====================== jstl표현  =========================<br>

<c:if test="${t_name =='홍길동'}">
	1. 홍길동 이다<br>
</c:if>
<c:if test="${t_name !='홍길동' }">
	2. 홍길동 아니다<br>
</c:if>
<c:if test="${t_name eq '홍길동' }">
	3. 홍길동 eq 이다<br>
</c:if>
<c:if test="${t_name ne '홍길동' }">
	4.홍길동 아니다<br>
</c:if>

<c:if test="${t_age == 25 }">
	5.25 <br>
</c:if>
<c:if test="${t_age != 25 }">
	6.25 x <br>
</c:if>
<c:if test="${t_age > 0 }">
	7. 0보다 큼 <br>
</c:if>
<c:if test="${t_age >= 25 }">
	8. 25보다 크거나 같다 <br>
</c:if>

<c:if test="${t_name == '홍길동' && t_age >= 25 }">
	9. 이름이 홍길동이고 나이가 25 이상이다 <br>
</c:if>
<c:if test="${t_name eq '홍길동' and t_age >= 25 }">
	10. 이름이 홍길동이고 나이가 25 이상이다 <br>
</c:if>
<c:if test="${t_name == '홍길동' || t_age >= 30 }">
	11. 이름이 홍길동이거나 나이가 30 이상이다 <br>
</c:if>
<c:if test="${t_name eq '홍길동' or t_age >= 30 }">
	12. 이름이 홍길동이거나 나이가 30 이상이다 <br>
</c:if>





</body>
</html>
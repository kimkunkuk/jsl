<%@page import="dto.SnackDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<SnackDto> dtos = (ArrayList<SnackDto>)request.getAttribute("t_dtos");
	String select = (String)request.getAttribute("t_select");
	String search = (String)request.getAttribute("t_search");
%>
<!DOCTYPE html>
<html> 
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--
	******************************************** 
		title : 풀스텍 홍길동
	******************************************** 
 -->	
	<title>TRACK11 홍길동</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">	
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >	
</head>
<body>
<script>
	function goSearch(){
		sna.method="post";
		sna.action="SnackList";
		sna.submit();
	}
</script>
	<div class="container">

		<div class="leftmargin">
			<img src="images/jsl_logo.png"><h1>TRACK11 홍길동 SNACK</h1>
		</div>		
		<div class="search_wrap">
			<div class="record_group">
				<p>총게시글 : <span><%=dtos.size() %></span>건</p>
			</div>
			<form name="sna">
			<div class="search_group">
				<select name="t_select" class="select">
					<option value="p_name" <%if(select.equals("p_name")) out.print("selected"); %>>제품명</option>
					<option value="m_name" <%if(select.equals("m_name")) out.print("selected"); %>>제조사명</option>
				</select>
				<input type="text" name="t_search" value="<%=search %>" class="search_word">
				<button onclick="goSearch()" class="btn_search"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
			</div>
			</form>
		</div>
	</div>
	<div class="board_list">
		<table class="board_table">
			<colgroup>
				<col width="25%">
				<col width="25%">
				<col width="25%">
				<col width="25%">
			</colgroup>
			<thead>
				<tr>
					<th>제품번호</th>
					<th>제품명</th>
					<th>제조사명</th>
					<th>가격</th>
				</tr>
			</thead>
			<tbody>
			<%for(SnackDto dto : dtos) {%>
				<tr>
					<td><a href="SnackView?t_code=<%=dto.getP_code() %>"><%=dto.getP_code() %></a></td>
					<td><%=dto.getP_name() %></td>
					<td><%=dto.getM_name() %></td>
					<td><%=dto.getSprice() %></td>
				</tr>	
			<%} %>
			</tbody>
		</table>
		<div class="paging">
			<a href="SnackWrite" class="write">제품등록</a>
		</div>
	</div>
 </body>
</html>








<%@page import="dto.MemberDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<MemberDto> dtos = (ArrayList<MemberDto>)request.getAttribute("t_dtos");
	String gubun  = (String)request.getAttribute("t_select");
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
	<link href="/web_servlet_basic/css/common.css" rel="stylesheet">
	<link href="/web_servlet_basic/css/layout.css" rel="stylesheet" >	
</head>
<script>
	function goSearch(){
		mem.method="post"; 
		mem.action="Member";
		//mem.action="MemberList";
		mem.submit();
	}
	function goView(id){
		view.t_id.value = id;
		view.method="post";
		view.action="MemberView";
		view.submit();
	}
</script>
<body>
<form name="view">
	<input type="hidden" name="t_id">
</form>
	<div class="container">

		<div class="leftmargin">
			<img src="/web_servlet_basic/images/jsl_logo.png"><h1>TRACK11 홍길동 회원관리</h1>
		</div>		
		<div class="search_wrap">
			<div class="record_group">
				<p>총게시글 : <span><%=dtos.size() %></span>건</p>
			</div>
			<form name="mem">
				<div class="search_group">
					<select name="t_gubun" class="select">
						<option value="id" >ID</option>
						<option value="name" >성명</option>
					</select>
					<input name="t_search" value="<%=search %>" type="text" class="search_word">
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
					<th>ID</th>
					<th>성명</th>
					<th>나이</th>
					<th>가입일</th>
				</tr>
			</thead>
			<tbody>
			<%for(MemberDto dto : dtos) {%>
				<tr>
					<td><a href="MemberView?t_id=<%=dto.getId() %>"><%=dto.getId() %></a></td>
					<td><a href=javascript:goView('<%=dto.getId() %>')><%=dto.getName() %></a></td>
					<td><%=dto.getAge() %></td>
					<td><%=dto.getReg_date() %></td>
				</tr>	
			<%} %>
			</tbody>
		</table>
		<div class="paging">
			<!-- <a href="Memberwrite" class="write">회원등록</a>  -->
			
			<a href="Member?t_gubun=writeForm" class="write">회원등록</a>
		</div>
	</div>
 </body>
</html>








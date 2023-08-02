<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
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
<script>
//검색
	function goSearch(){
		src.method="post";
		src.action="Member";
		src.submit();
	}
//멤버 상세보기
	function goView(id){
		view.t_id.value = id;
		view.t_gubun.value = "view";
		view.method="post";
		view.action="Member";
		view.submit();
	}
	function goWrite(){
		view.t_gubun.value = "write";
		view.method="post";
		view.action="Member";
		view.submit();
	}
</script>
<form name="view">
	<input type="hidden" name="t_id">
	<input type="hidden" name="t_gubun">
</form>
<body>
	<div class="container">

		<div class="leftmargin">
			<img src="images/jsl_logo.png"><h1>TRACK11 홍길동 회원관리</h1>
		</div>		
		<div class="search_wrap">
			<div class="record_group">
				<p>총인원 : <span>${t_arr.size()}</span>명</p>
			</div>
			<form name="src">
			<div class="search_group">
				<select name="t_select" class="select">
					<option value="id" <c:if test="${t_select eq 'id'}"> selected </c:if> >ID</option>
					<option value="name" <c:if test="${t_select eq 'name'}"> selected </c:if> >성명</option>
				</select>
				<input name="t_search" type="text" value="${t_search}" class="search_word">
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
			<c:forEach items="${t_arr}" var="arr">
				<tr>
					<td><a href="javascript:goView('${arr.getId()}')">${arr.getId()}</a></td>
					<td>${arr.getName()}</td>
					<td>${arr.getAge()}</td>
					<td>${arr.getReg_date()}</td>
				</tr>	
			</c:forEach>
			</tbody>
		</table>
		<div class="paging">
			<a href="javascript:goWrite()" class="write">회원등록</a>
		</div>
	</div>
 </body>
</html>








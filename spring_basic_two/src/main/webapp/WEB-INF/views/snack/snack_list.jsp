<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html> 
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--
	******************************************** 
		title : 풀스텍 홍길동test
	******************************************** 
 -->	
	<title>TRACK11 홍길동</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">	
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >	
</head>
<script>
	function goSearch(){
		search.method="post";
		search.action="Snack";
		search.submit();
	}
	function goWrite(){
		sna.t_gubun.value = "writeForm";
		sna.method="post";
		sna.action="Snack";
		sna.submit();
	}
	function goView(code){
		sna.t_gubun.value = "view";
		sna.t_code.value = code;
		sna.method="post";
		sna.action="Snack";
		sna.submit();
	}
</script>
<form name="sna">
	<input type="hidden" name="t_gubun" value="writeForm">
	<input type="hidden" name="t_code">
</form>
<body>
	<div class="container">
		<div class="leftmargin">
			<img src="images/jsl_logo.png"><h1>TRACK11 홍길동 SNACK</h1>
		</div>		
		<div class="search_wrap">
			<div class="record_group">
				<p>총게시글 : <span>${t_arr.size()}</span>건</p>
			</div>
			<form name="search">
			<div class="search_group">
				<select name="t_select" class="select">
					<option value="s.p_name" <c:if test="${t_select eq 's.p_name'}"> selected </c:if> >제품명</option>
					<option value="c.m_name" <c:if test="${t_select eq 'c.m_name'}"> selected </c:if> >제조사명</option>
				</select>
				<input type="text" name="t_search" value="${t_search}" class="search_word">
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
			<c:forEach items="${t_arr}" var="arr">
				<tr>
					<td><a href="javascript:goView('${arr.getP_code()}')">${arr.getP_code()}</a></td>
					<td>${arr.getP_name()}</td>
					<td>${arr.getM_name()}</td>
					<td>${arr.getSprice()}</td>
				</tr>	
			</c:forEach>
			</tbody>
		</table>
		<div class="paging">
			<a href="javascript:goWrite()" class="write">제품등록</a>
		</div>
	</div>
 </body>
</html>








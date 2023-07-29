<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>TRACK11 홍길동</title>
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >	
	<script type="text/javascript">
		function goSave(){
			board.method="post";
			board.action="Snack";
			board.submit();
		}
	</script>
</head>
<body>
	<div class="container">
		<div class="leftmargin">
			<img src="images/jsl_logo.png"><h1>TRACK11 홍길동 SNACK</h1>
		</div>		
		<div class="write_wrap">
		
			<div class="board_list">
				<form name="board">
				<input type="hidden" name="t_gubun" value="updateSave">
				<input type="hidden" name="t_code" value="${t_dto.getP_code()}">
				<table class="board_table">
					<colgroup>
						<col width="12%">
						<col width="*">
					</colgroup>
					
					<tbody>
						<tr>
							<th>제품번호</th>
							<td class="th_left">
								${t_dto.getP_code()}
							</td>
						</tr>
						<tr>
							<th>제품명</th>
							<td class="th_left">
								<input name="t_name" value="${t_dto.getP_name()}" class="input_300px" type="text">
							</td>
						</tr>
						<c:set var="price" value="${t_dto.getSprice()}"></c:set>
						<tr>
							<th>가격</th>
							<td class="th_left">
								<input name="t_price" value="${t_dto.getSprice()}" class="input_100px" type="text">
							</td>
						</tr>
						<tr>
							<th>제조사</th>
							<td class="th_left">
								<select name="t_com" class="select">
									<c:forEach items="${t_arr}" var="arr">
										<option value="${arr.getM_code()}" <c:if test="${arr.getM_code() eq t_dto.getM_code()}"> selected </c:if> >
											${arr.getM_name()}
										</option>
									</c:forEach>
								</select>										
							</td>
						</tr>
					</tbody>
				</table>
				</form>
			</div>
			<div class="btn_wrap">
				<input type="button" onClick="location.href='Snack'" value="목록" class="btn_list">
				<input type="button" onClick="goSave()" value="수정저장" class="btn_ok">&nbsp;&nbsp;
			</div>
		</div>
	</div>
</body>
</html>




















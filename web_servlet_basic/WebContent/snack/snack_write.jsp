<%@page import="dto.SnackDto,dao.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	SnackDao dao = new SnackDao();
	ArrayList<SnackDto> arr = dao.getCompanyList();
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>TRACK11 홍길동</title>
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >		
	<script >
		function goSave(){
			if(board.t_code.value == ""){
				alert("제품번호 입력하세요");
				board.t_code.focus();
				return;
			}
			if(board.t_name.value == ""){
				alert("제품명 입력하세요");
				board.t_name.focus();
				return;
			}
			if(board.t_price.value == ""){
				alert("가격 입력하세요");
				board.t_price.focus();
				return;
			}
			board.t_gubun.value="writeSave"
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
			<form name="board">
			<input type="hidden" name="t_gubun">
			<div class="board_list">
				<table class="board_table">
					<colgroup>
						<col width="12%">
						<col width="*">
					</colgroup>
					<tbody>
						<tr>
							<th>제품번호</th>
							<td class="th_left">
								<input name="t_code"  class="input_100px" type="text">
							</td>
						</tr>
						<tr>
							<th>제품명</th>
							<td class="th_left">
								<input name="t_name"  class="input_300px" type="text">
							</td>
						</tr>
						<tr>
							<th>가격</th>
							<td class="th_left">
								<input name="t_price"  class="input_100px" type="text">
							</td>
						</tr>
						<tr>
							<th>제조사</th>
							<td class="th_left">
								<select name="t_com" class="select">
								<%for(int k=0; k<arr.size(); k++) {%>
									<option value="<%=arr.get(k).getM_code()%>"><%=arr.get(k).getM_name() %></option>
								<%} %>	
								</select>								
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			</form>
			<div class="btn_wrap">
				<input type="button" value="목록" onclick="location.href='Snack'" class="btn_ok">&nbsp;&nbsp;
				<input type="button" value="등록" onclick="goSave()" class="btn_list">
			</div>
		</div>
	</div>
</body>
</html>




















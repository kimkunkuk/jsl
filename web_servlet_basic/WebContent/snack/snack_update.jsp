<%@page import="dao.SnackDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.SnackDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	SnackDao dao = new SnackDao();
	SnackDto dto = (SnackDto)request.getAttribute("t_dto");
	ArrayList<SnackDto> arr = dao.getCompanyList();
%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>TRACK11 홍길동</title>
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >	
	<script type="text/javascript">
		function goUpdate(){
			sna.t_gubun.value="updateSave";
			sna.method="post";
			sna.action="Snack";
			sna.submit();
		}
	</script>
</head>
<body>
	<form name="sna">
	<input type="hidden" name=t_code value="<%=dto.getP_code() %>">
	<input type="hidden" name="t_gubun">
	<div class="container">
		<div class="leftmargin">
			<img src="images/jsl_logo.png"><h1>TRACK11 홍길동 SNACK</h1>
		</div>		
		<div class="write_wrap">
		
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
								<%=dto.getP_code() %>
							</td>
						</tr>
						<tr>
							<th>제품명</th>
							<td class="th_left">
								<input name="t_name" value="<%=dto.getP_name() %>" class="input_300px" type="text">
							</td>
						</tr>
						<tr>
							<th>가격</th>
							<td class="th_left">
								<input name="t_price" value="<%=dto.getSprice().replaceAll(",", "") %>" class="input_100px" style="text-align: right" type="text">
							</td>
						</tr>
						<tr>
							<th>제조사</th>
							<td class="th_left">
								<select name="t_com"  class="select">
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
				<input type="button" onClick="location.href='Snack'" value="목록" class="btn_list">
				<input type="button" onClick="goUpdate()" value="수정저장" class="btn_ok">&nbsp;&nbsp;
			</div>
		</div>
	</div>
</body>
</html>




















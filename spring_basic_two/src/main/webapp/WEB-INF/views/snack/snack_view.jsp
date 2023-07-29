<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>TRACK11 홍길동</title>
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >	
	<script type="text/javascript">
		function goUpdate(){
			sna.t_gubun.value="updateForm";
			sna.method="post";
			sna.action="Snack";
			sna.submit();
		}
		function goDelete(){
			sna.t_gubun.value="delete";
			sna.method="post";
			sna.action="Snack";
			sna.submit();
		}
	</script>
</head>
<form name="sna">
	<input type="hidden" name="t_gubun">
	<input type="hidden" name="t_code" value="${t_dto.getP_code()}">
</form>
<body>
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
								${t_dto.getP_code()}
							</td>
						</tr>
						<tr>
							<th>제품명</th>
							<td class="th_left">
								${t_dto.getP_name()}
							</td>
						</tr>
						<tr>
							<th>가격</th>
							<td class="th_left">
								${t_dto.getSprice()}
							</td>
						</tr>
						<tr>
							<th>제조사</th>
							<td class="th_left">
								${t_dto.getM_name()}
								${t_dto.getM_code()}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btn_wrap">
				<input type="button" onClick="location.href='Snack'" value="목록" class="btn_list">
				<input type="button" onClick="javascript:goUpdate()" value="수정" class="btn_list">
				<input type="button" onClick="goDelete()" value="삭제" class="btn_list">
			</div>
		</div>
	</div>
</body>
</html>




















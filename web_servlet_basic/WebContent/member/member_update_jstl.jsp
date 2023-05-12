<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>TRACK11 홍길동</title>
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >	
	<script type="text/javascript">
		function goUpdateSave(){
			update.t_gubun.value="updateSave";
			update.method="post";
			update.action="Member";
			update.submit();
		}
	</script>
</head>
<body>
	<div class="container">
		<div class="leftmargin">
			<img src="images/jsl_logo.png"><h1>TRACK11 홍길동 회원관리</h1>
		</div>		
		<div class="write_wrap">
		
			<div class="board_list">
				<table class="board_table">
					<colgroup>
						<col width="12%">
						<col width="*">
					</colgroup>
					<form name="update">
					<input type="hidden" name="t_gubun">
					<input type="hidden" name="t_id" value="${t_dto.getId() }">
					<tbody>
						<tr>
							<th>ID</th>
							<td class="th_left">
								${t_dto.getId() }
							</td>
						</tr>
						<tr>
							<th>성명</th>
							<td class="th_left">
								<input name="t_name" value="${t_dto.getName() }" class="input_300px" type="text">
							</td>
						</tr>
						<tr>
							<th>나이</th>
							<td class="th_left">
								<input name="t_age" value="${t_dto.getAge() }" class="input_100px" type="text">
							</td>
						</tr>
						<tr>
							<th>가입일</th>
							<td class="th_left">
								<input name="t_date" value="${t_dto.getReg_date() }" class="input_200px" type="date">							
							</td>
						</tr>
					</tbody>
					</form>
				</table>
			</div>
			<div class="btn_wrap">
				<input type="button" onClick="location.href='Member'" value="목록" class="btn_list">
				<input type="button" onClick="goUpdateSave()" value="수정저장" class="btn_ok">&nbsp;&nbsp;
			</div>
		</div>
	</div>
</body>
</html>




















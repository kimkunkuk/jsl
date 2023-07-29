<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>TRACK11 홍길동</title>
	<link href="/sts/css/common.css" rel="stylesheet">
	<link href="/sts/css/layout.css" rel="stylesheet" >	
	<script type="text/javascript">
		function goUpdate(){
			mem.method="post";
			mem.action="memberUpdate";
			mem.submit();
		}
		function goDelete(){
			if(confirm("삭제하실건가요?")){
				mem.method="post";
				mem.action="memberDelete";
				mem.submit();
			}
		}
	</script>
</head>
<form name="mem">
	<input type="hidden" name="t_id" value="${t_dto.getId()}">
</form>
<body>
	<div class="container">
	
		<div class="leftmargin">
			<img src="/sts/images/jsl_logo.png"><h1>TRACK11 홍길동 회원관리</h1>
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
							<th>ID</th>
							<td class="th_left">
								${t_dto.getId()}
							</td>
						</tr>
						<tr>
							<th>성명</th>
							<td class="th_left">
								${t_dto.getName()}
							</td>
						</tr>
						<tr>
							<th>나이</th>
							<td class="th_left">
								${t_dto.getAge()}
							</td>
						</tr>
						<tr>
							<th>가입일</th>
							<td class="th_left">
								${t_dto.getReg_date()}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btn_wrap">
				<input type="button" onClick="location.href='memberList'" value="목록" class="btn_list">
				<input type="button" onClick="goUpdate()" value="수정" class="btn_list">
				<input type="button" onClick="goDelete()" value="삭제" class="btn_list">
			</div>
		</div>
	</div>
</body>
</html>




















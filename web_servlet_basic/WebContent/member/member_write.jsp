<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>TRACK11 홍길동</title>
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >		
	<script type="text/javascript">
		
	
		function goSave(){
			var id = mem.t_id.value;
			if(id ==""){
				alert("id 입력하이소");
				mem.t_id.focus();
				return;
			}
			
			if(mem.t_name.value == ""){
				alert("이름 입력해라");
				mem.t_name.focus();
				return;
			}
			if(mem.t_date.value == ""){
				alert("날짜입력 ㄱㄱ");
				mem.t_date.focus();
				return;
			}
			mem.t_gubun.value = "memberSave"
			mem.method="post"; //post ,get 의차이 post는 넘기는 값을 안보이게 넘긴다 
			mem.action="Member" //폼의 넘의가는 값을 받을 jsp 페이지
			mem.submit(); // 실행 
		}
	</script>
</head>
<body>
	<div class="container">

		<div class="leftmargin">
			<img src="images/jsl_logo.png"><h1>TRACK11 이주형 회원관리asdfsadf</h1>
		</div>		
		<div class="write_wrap">
			<form name="mem"> <!-- 폼으로 감싸야 인풋값 넘길수가 있음 -->
			<input type="hidden" name="t_gubun">
			<div class="board_list">
				<table class="board_table">
					<colgroup>
						<col width="12%">
						<col width="*">
					</colgroup>
					<tbody>
						<tr>
							<th>ID</th>
							<td class="th_left"> <!-- 각각 인풋 들에 이름도 넣어줌 -->
								<input name="t_id"  class="input_100px" type="text">
							</td>
						</tr>
						<tr>
							<th>성명</th>
							<td class="th_left">
								<input name="t_name"  class="input_300px" type="text">
							</td>
						</tr>
						<tr>
							<th>나이</th>
							<td class="th_left">
								<input name="t_age"  class="input_100px" type="text">
							</td>
						</tr>
						<tr>
							<th>가입일</th>
							<td class="th_left">
								<input name="t_date"  class="input_200px" type="date">							
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			</form>
			<div class="btn_wrap">
				<input type="button" value="등록" onclick="goSave()" class="btn_ok">&nbsp;&nbsp;
				<input type="button" value="목록" onclick="location.href='Member'" class="btn_list">
			</div>
		</div>
	</div>
</body>
</html>



















    
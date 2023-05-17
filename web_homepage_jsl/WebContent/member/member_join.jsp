<%@ page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../common_header.jsp" %>
	<script>
	
	function memberJoin(){
		
		if(checkValue(mem.t_id,"id입력!")) return;
		if(checkValue(mem.t_name,"성명입력!")) return;
		
		var idLength = mem.t_id.value.length;
		if(idLength < 3 || idLength > 10) {
			alert("ID 3자리이상 10자리 이내!");
			mem.t_id.focus();
			return;
		}
	
		if(checkValue(mem.t_pw1,"비밀번호입력!")) return;
		if(checkValue(mem.t_pw2,"비밀번호 확인입력!")) return;
		if(mem.t_pw1.value != mem.t_pw2.value){
			alert("비밀번호 확인!");
			mem.t_pw2.focus();
			return;
		}
		
		if(checkValue(mem.t_job,"소속선택!")) return;
		var tell_2_length = mem.t_tell2.value.length;
		var tell_3_length = mem.t_tell3.value.length;
		if(mem.t_tell1.value != "" && tell_2_length == 0){
			alert("유선전화 3자리");
			mem.t_tell2.focus();
			return;
		}
		if(mem.t_tell1.value != "" && tell_3_length == 0){
			alert("유선전화 4자리");
			mem.t_tell3.focus();
			return;
		}
		if(tell_2_length != 0 && mem.t_tell1.value == ""){
			alert("지역번호 선택");
			mem.t_tell1.focus();
			return;
		}
		if(tell_3_length != 0 && mem.t_tell1.value == ""){
			alert("지역번호 선택");
			mem.t_tell1.focus();
			return;
		}
		if(tell_2_length != 3 && tell_2_length != 0){
			alert("유선전화 3자리");
			mem.t_tell2.focus();
			return;
		}
		
		if(tell_3_length != 4 && tell_3_length != 0){
			alert("유선전화 4자리");
			mem.t_tell3.focus();
			return;
		}
		
		
		if(tell_2_length == 3 && tell_3_length == 0){
			alert("세번째 연락처 입력");
			mem.t_tell3.focus();
			return;
		}
		
		
		if(tell_2_length == 0 && tell_3_length == 4){
			alert("두번째 연락처 입력");
			mem.t_tell2.focus();
			return;
		}
		
		if(checkValue(mem.t_mobile2,"휴대전화 입력!")) return;
		if(checkValue(mem.t_mobile3,"휴대전화 입력!")) return;
		if(mem.t_mobile2.value.length != 4){
			alert("휴대폰 번호 4자리입력");
			mem.t_mobile2.focus();
			return;
		}
		if(mem.t_mobile3.value.length != 4){
			alert("휴대폰 번호 4자리입력");
			mem.t_mobile3.focus();
			return;
		}
		
		if(checkValue(mem.t_email,"이메일 입력!")) return;
		
		if(mem.t_idcheck.value == ""){
			alert("ID 중복검사하시오!");
			return;
		}
		
		if(mem.t_idcheck.value == "사용불가") {
			alert("중복된 ID 입니다.");
			mem.t_id.focus();
			return;
		}
		
		if(mem.t_id.value != mem.t_idcheckValue.value){
			alert("변경ID 중복검사 하시오");
			mem.t_id.focus();
			return;
		}
		
		mem.method="post";
		mem.action="db_member_join.jsp";
		mem.submit();
		
	}  
	function checkID(){
		if(checkValue(mem.t_id,"id입력후 중복검사")) return;
		
		$.ajax({
			type : "POST",
			url : "member_checkid.jsp",
			data: "t_id="+mem.t_id.value,
			dataType : "text",
			error : function(){
				alert('통신실패!!!!!');
			},
			success : function(data){
				var result = $.trim(data);
				mem.t_idcheck.value = result;
				
				if(result == "사용가능"){
					mem.t_idcheckValue.value = mem.t_id.value;
				}else{
					mem.t_idcheckValue.value = "";
				}
			}
		});				
	}
	</script>
	<!-- sub contents -->
	<div class="sub_title">
		<h2>회원가입</h2>
		<div class="container">
		  <div class="location">
			<ul>
				<li class="btn_home">
					<a href="index.html"><i class="fa fa-home btn_plus"></i></a>
				</li>
				<li class="dropdown">
					<a href="">커뮤니티<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="gratings.html">공지사항</a>
						<a href="allclass.html">학과및모집안내</a>
						<a href="portfolio.html">포트폴리오</a>
						<a href="online.html">온라인접수</a>
						<a href="notice.html">커뮤니티</a>
					</div>
				</li>
				<li class="dropdown">
					<a href="">공지사항<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="notice.html">공지사항</a>
						<a href="qa.html">질문과답변</a>
						<a href="faq.html">FAQ</a>
					</div>
				</li>
			</ul>
		  </div>
		</div><!-- container end -->
	</div>

	<div class="container">
		<div class="con_title">
            <h1>내정보(개인회원)</h1>
         <p>HOME / 마이페이지 / 내정보(개인회원)</p>
        </div>
		<div class="join_write col_989">
                <div class="list_con">
                    <ul class="icon_type1">
                        <li>회원정보는 개인정보 취급방침에 따라 안전하게 보호되며 회원님의 명백한 동의 없이 공개 또는 제3자에게 제공되지 않습니다.</li>
                    </ul>
                </div>
                <form name="mem">
            <table class="table_write02" summary="회원가입을 위한 이름, 아이디, 비밀번호, 비밀번호확인, 소속, 유선전화번호, 휴대전화번호, 이메일, 주소, 본인확인질문, 본인확인답, 주활용사이트, 알림여부 정보 입력">
                <caption>회원가입을 위한 정보입력표</caption>
                <colgroup>
                    <col width="160px">
                    <col width="auto">
                </colgroup>
                <tbody id="joinDataBody">
                	<tr>
                        <th><label for="id">아이디<span class="must"></span></label></th>
                        <td>
                            <input type="text" name="t_id" id="mbrId" class="w300" autofocus>
							<a href="javascript:checkID()" class="btn_write"><b>ID중복검사</b></a>
							<input type="text" name="t_idcheck" readonly style="border:none">
							<input type="hidden" name="t_idcheckValue" readonly style="border:none">
						</td>
                    </tr>
                    <tr>
                        <th><label for="name">이름</label></th>
                        <td>
                            <input type="text" name="t_name" id="mbrName" class="w300">
                        </td>
                    </tr>
                    <tr>
                        <th><label for="pw">비밀번호<!-- <span class="must"><b>필수입력</b></span> --></label></th>
                        <td>
                            <input type="password" name="t_pw1" id="scrtNo" class="w300">
                            <p class="guideTxt"><span class="tc_point"></p>
                        </td>
                    </tr>
                    <tr>
                        <th><label for="pw_confirm">비밀번호확인<!-- <span class="must"><b>필수입력</b></span> --></label></th>
                        <td>
                            <input type="password" name="t_pw2" id="scrtNoConfirm" class="w300">
                            <p class="guideTxt"></p>
                        </td>
                    </tr>
                    <tr>
                        <th>소속<span class="must"><b></b></span></th>
                        <td>
                            <label for="mbrClCd" class="blind">소속1차 카테고리 선택</label>
                            <select name="t_job" id="mbrClCd">
                                <option value="">선택</option>
                                
                                    <option value="기업">기업</option>
                                
                                    <option value="교수자">교수자</option>
                                
                                    <option value="미취업자">미취업자</option>
                                
                                    <option value="기타">기타</option>
                                
                            </select>
                            <p class="guideTxt">학생 신분은 '미취업자-학생' 소속으로 선택해주십시오.</p>
                        </td>
                    </tr>
                    <tr>
                        <th>유선전화</th>
                        <td>
                            <label for="phone_number1" class="blind">유선전화 앞번호 선택</label>
                            <select name="t_tell1" id="telNo1" class="w95">
                                <option value="" selected>선택</option>
                                    <option value="02">02</option>
                                    <option value="042">042</option>
                                    <option value="051">051</option>
                                    <option value="061">061</option>
                                    <option value="070">070</option>
                            </select>
                            <input type="text" name="t_tell2" id="telNo2" class="w95" value="" maxlength="3"><label for="phone_number2" class="blind">중간번호</label>
                            <input type="text" name="t_tell3" id="telNo3" class="w95" value="" maxlength="4"><label for="phone_number3" class="blind">마직막번호</label>
                        </td>
                    </tr>
                    <tr>
                        <th>휴대전화<span class="must"></th>
                        <td>
                            <label for="mphonNo1" class="blind">휴대전화 앞번호 선택</label>
                            <select name="t_mobile1" id="mphonNo1" class="w95">
                                    <option value="010">010</option>
                                    <option value="011">011</option>
                            </select>
                            <input type="text" name="t_mobile2" id="mphonNo2" class="w95" value="" maxlength="4"><label for="mphonNo2" class="blind">중간번호</label>
                            <input type="text" name="t_mobile3" id="mphonNo3" class="w95" value="" maxlength="4"><label for="mphonNo3" class="blind">마직막번호</label>
                        </td>
                    </tr> 
                    <tr>
                        <th><label for="email">이메일</label></th>
                        <td>
                            <input type="email" name="t_email" id="email" class="w300" >
                        </td>
                    </tr>
            </table>
            </form>
        </div>
	</div>
	<!-- end contents -->
	
	<div class="btnArea Acenter pt60 pb100">
        <a href="javascript:history.go(-1);" class="btn_round btn_large btn_BlueGray w180"><b>취소</b></a>
        <a href="javascript:memberJoin()" class="btn_round btn_large btn_pointColor w180"><b>확인</b></a>
    </div>
	
	
	<script>
		$(function() {
			$(".location  .dropdown > a").on("click",function(e) {
				e.preventDefault();
				if($(this).next().is(":visible")) {
					$(".location  .dropdown > a").next().hide();
				} else {
					$(".location  .dropdown > a").next().hide();
					$(this).next().show();
				}
			});
		});
	</script>
	

<%@ include file = "../common_footer.jsp" %>

 </body>
</html>










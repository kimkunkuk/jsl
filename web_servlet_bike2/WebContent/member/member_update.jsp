<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common_header.jsp" %>
<script>
	function goUpdate(){
		
		
		if(checkValue(join.t_name,"이름 입력하세요.")) return;

		if(checkValue(join.t_address,"주소를 입력해주세요.")) return;
		
		if(checkValue(join.t_mobile_1,"휴대전화 입력해주세요.")) return;
		var m1_length = join.t_mobile_1.value.length;
		if(m1_length != 3){
			alert("휴대번호 앞자리 3자리입력 ex)010, 011");
			return;
		}
		
		if(checkValue(join.t_mobile_2,"휴대전화 입력해주세요.")) return;
		var m2_length = join.t_mobile_2.value.length;
		if(m2_length != 4){
			alert("휴대번호 4자리 입력");
			join.t_mobile_2.focus();
			return;
		}
		
		if(checkValue(join.t_mobile_3,"휴대전화 입력해주세요.")) return;
		var m3_length = join.t_mobile_3.value.length;
		if(m3_length != 4){
			alert("휴대번호 4자리 입력");
			join.t_mobile_3.focus();
			return;
		}
		
		if(checkValue(join.t_pw_confirm,"비밀번호 확인 .")) return;
		
		checkPassword(); 
		
		
		if(join.t_password_check.value == "no!"){
			alert("비밀번호 확인하세요");
			join.t_pw_confirm.focus();
			return;
		}
		
		
		join.t_gubun.value="memberUpadte"; 
		join.method="post";
		join.action="Member";
		
		//join.action="Member?t_gubun=memberSave";
		join.submit();
		
	}
	function checkPassword(){
	//alert("d");
	//var result = "";
		$.ajax({
			type : "POST",
			url : "MemberCheckPassword",
			data: "t_id="+join.t_id.value+"&t_pw="+join.t_pw_confirm.value,
			async : false, //비동기를 동기로 변환
			dataType : "text", //순간적으로 띄운 브라우저 에 글씨 받아오겠다.
			error : function(){
				alert('통신실패!!!!!');
			},
			success : function(data){
				var result = $.trim(data);
				join.t_password_check.value = data;
				
			}
		});	
	
	//alert(result);
	}	
</script>	
		<div id="b_left">
			<P>MEMBER</P>
			<ul>
				<li><a href="javascript:goWork('memberLogin')">LOGIN</a></li>
				<li><a href="">ID / PASSWORD</a></li>
				<li><a href="#"><span class="fnt"><i class="fas fa-apple-alt"></i></span> UPDATE</a></li>
			</ul>
		</div>
		<form name="join">
		<input type="hidden" name="t_gubun">
		<input type="hidden" value="${t_dto.getId() }" name="t_id">
		<input type="text" name="t_password_check">
		<div id="b_right">
			<p class="n_title">
				MEMBER UPDATE
			</p>
			
			<table class="boardForm">
			  <colgroup>
				<col width="200" />
				<col width="500" />
			  </colgroup>
			  
			  <tbody>
				<tr>
				  <th><label for="id">I D</label></th>
				  <td>
					${t_dto.getId() }
				  </td>
				</tr>
				<tr>
				  <th><label for="nana">성 명</label></th>
				  <td><input type="text" value="${t_dto.getName() }" name="t_name" size="8" id="nana"></td>
				</tr>
				<tr>
				  <th>지역</th>
				  <td>
					<select name="t_area">
						<option value="서울" <c:if test="${t_dto.getArea() eq '서울' }">selected</c:if> >서울</option>
						<option value="대전" <c:if test="${t_dto.getArea() eq '대전' }">selected</c:if> >대전</option>
						<option value="부산" <c:if test="${t_dto.getArea() eq '부산' }">selected</c:if> >부산</option>
						<option value="대구" <c:if test="${t_dto.getArea() eq '대구' }">selected</c:if> >대구</option>        
					</select>	  
				  </td>
				</tr>	
				
				<tr>
				  <th>주소</th>
				  <td><input type="text" value="${t_dto.getAddress() }" name="t_address" size="40"></td>
				</tr>
				<tr>
				  <th>연락처</th>
				  <td>
					<input type="text" value="${t_dto.getMobile_1() }" name="t_mobile_1" size="2"> -
					<input type="text" value="${t_dto.getMobile_2() }" name="t_mobile_2" size="3"> -
					<input type="text" value="${t_dto.getMobile_3() }" name="t_mobile_3" size="3">
				  </td>
				</tr>
				<tr>
				  <th>남여구분</th>
				  <td>
					  <input type="radio" value="f" <c:if test="${t_dto.getGender() eq 'f' }">checked</c:if> name="t_gender" class="middleCheck" /> 여&nbsp;&nbsp;        
					  <input type="radio" value="m" <c:if test="${t_dto.getGender() eq 'm' }">checked</c:if> name="t_gender" class="middleCheck" /> 남        
				  </td>
				</tr>
				<tr>
				  <th>취미</th>
				  <td>
					  <input type="checkbox" <c:if test="${t_dto.getHobby_t() eq 'y' }">checked</c:if> value="y" name="t_hobby_t" class="middleCheck" /> 여행&nbsp;&nbsp; 
					  <input type="checkbox" <c:if test="${t_dto.getHobby_r() eq 'y' }">checked</c:if> value="y" name="t_hobby_r" class="middleCheck" /> 독서&nbsp;&nbsp; 
					  <input type="checkbox" <c:if test="${t_dto.getHobby_s() eq 'y' }">checked</c:if> value="y" name="t_hobby_s" class="middleCheck" /> 운동
				  </td>
				</tr>
				<tr>
				  <th>회원가입일</th>
				  <td>
					 ${t_dto.getReg_date() }
				  </td>
				</tr>
				<tr>
				  <th>정보수정일</th>
				  <td>
					 ${t_dto.getUpdate_date() }
				  </td>
				</tr>
				<tr>
				  <th>최근로그인</th>
				  <td>
					 ${t_dto.getLogin_date() } 
				  </td>
				</tr>
				<tr>
				  <th>비빌번호 확인</th>
				  <td><input type="text" name="t_pw_confirm" size="13"></td>
				</tr>
			  </tbody>
			 
			</table>
			
			<div class="buttonGroup_center">
				<a href="javascript:goUpdate()" class="butt">수정</a>
			</div>	
		</div>	
		</form>
		
<%@ include file="../common_footer.jsp" %>







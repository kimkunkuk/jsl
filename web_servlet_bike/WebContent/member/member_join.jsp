<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common_header.jsp" %>
<script>
	function goJoin(){
		
		if(checkValue(join.t_id,"Id 입력하세요.")) return;
		
		if(checkValue(join.t_name,"이름 입력하세요.")) return;
		
		if(checkValue(join.t_pw1,"비밀번호 입력하세요.")) return;
		if(checkValue(join.t_pw2,"비밀번호 확인 입력하세요.")) return;
		
		if(join.t_pw1.value != join.t_pw2.value){
			alert("비밀번호 확인 불일치!");
			join.t_pw2.focus();
			return;
		}
		
		if(checkValue(join.t_address,"주소를 입력해주세요.")) return;
		if(checkValue(join.t_mobile_1,"휴대전화 입력해주세요.")) return;
		if(checkValue(join.t_mobile_2,"휴대전화 입력해주세요.")) return;
		if(checkValue(join.t_mobile_3,"휴대전화 입력해주세요.")) return;
		if(checkValue(join.t_gender,"성별을 체크해주세요.")) return;
		
		join.t_gubun.value="memberSave";
		join.method="post";
		join.action="Member";
		join.submint();
	}
</script>	
		<div id="b_left">
			<P>MEMBER</P>
			<ul>
				<li><a href="javascript:goWork('memberLogin')">LOGIN</a></li>
				<li><a href="">ID / PASSWORD</a></li>
				<li><a href="javascript:goWork('memberJoin')"><span class="fnt"><i class="fas fa-apple-alt"></i></span> JOIN</a></li>
			</ul>
		</div>
		<form name="join">
		<input type="hidden" name="t_gubun">
		<div id="b_right">
			<p class="n_title">
				MEMBER JOIN
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
					<input name="t_id" type="text" size="10" id="id" title="id입력하세요">
					<input type="button" onclick="checkId()" value="ID중복검사" class="checkB">
				  </td>
				</tr>
				<tr>
				  <th><label for="nana">성 명</label></th>
				  <td><input type="text" name="t_name" size="8" id="nana"></td>
				</tr>
				<tr>
				  <th>비빌번호</th>
				  <td><input type="text" name="t_pw1" size="13"></td>
				</tr>
				<tr>
				  <th>비밀번호확인</th>
				  <td><input type="text" name="t_pw2" size="13"></td>
				</tr>
				<tr>
				  <th>지역</th>
				  <td>
					<select name="t_area">
						<option value="서울">서울</option>
						<option value="대전">대전</option>
						<option value="부산">부산</option>
						<option value="대구">대구</option>        
					</select>	  
				  </td>
				</tr>	
				
				<tr>
				  <th>주소</th>
				  <td><input type="text" name="t_address" size="40"></td>
				</tr>
				<tr>
				  <th>연락처</th>
				  <td>
					<input type="text" name="t_mobile_1" size="2"> -
					<input type="text" name="t_mobile_2" size="3"> -
					<input type="text" name="t_mobile_3" size="3">
				  </td>
				</tr>
				<tr>
				  <th>남여구분</th>
				  <td>
					  <input type="radio" value="f" name="t_gender" class="middleCheck" /> 여&nbsp;&nbsp;        
					  <input type="radio" value="m" name="t_gender" class="middleCheck" /> 남        
				  </td>
				</tr>
				<tr>
				  <th>취미</th>
				  <td>
					  <input type="checkbox" value="y" name="t_hobby_t" class="middleCheck" /> 여행&nbsp;&nbsp; 
					  <input type="checkbox" value="y" name="t_hobby_r" class="middleCheck" /> 독서&nbsp;&nbsp; 
					  <input type="checkbox" value="y" name="t_hobby_s" class="middleCheck" /> 운동
				  </td>
				</tr>
			  </tbody>
			 
			</table>
			
			<div class="buttonGroup_center">
				<a href="javascript:goJoin()" class="butt">JOIN</a>
			</div>	
		</div>	
		</form>
<%@ include file="../common_footer.jsp" %>







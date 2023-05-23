<%@page import="dao.MemberDao"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common_header.jsp" %>
<%

 %>
<script>
	function goDelete(val){
		var tf = confirm("정막 삭제?")
		if(tf == true){
			del.t_gubun.value=val;
			del.method="post";
			del.action="Member";
			del.submit();
		}
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
		<form name="del">
			<input type="hidden" name="t_id" value=" ${t_dto.getId()}">
		</form>
		
		<div id="b_right">
			<p class="n_title">
				MEMBER INFORMATION
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
					 ${t_dto.getId()}
				  </td>
				</tr>
				<tr>
				  <th><label for="nana">성 명</label></th>
				  <td> ${t_dto.getName()}</td>
				</tr>
				<tr>
				  <th>비빌번호 자릿수</th>
				  <td> 
				  	<c:forEach begin="1" end="${t_dto.getPwlen() }">*</c:forEach>
				  </td>
				</tr>
				<tr>
				  <th>지역</th>
				  <td>
					 ${t_dto.getArea()}
				  </td>
				</tr>	
				<tr>
				  <th>주소</th>
				  <td>
				  	 ${t_dto.getAddress()}
				  </td>
				</tr>
				<tr>
				  <th>연락처</th>
				  <td>
					 ${t_dto.getMobile_1()} -
					 ${t_dto.getMobile_2()} -
					 ${t_dto.getMobile_3()}
				  </td>
				</tr>
				<tr>
				  <th>남여구분</th>
				  <td>
				  <c:if test="${t_dto.getGender() eq 'm' }">
					   남성
				  </c:if>
				  <c:if test="${t_dto.getGender() eq 'f' }">
					   여성
				  </c:if>	   
				  </td>
				</tr>
				<tr>
				  <th>취미</th>
				  <td>
					  <input type="checkbox" value="y" name="t_hobby_t" class="middleCheck" disabled
					  <c:if test="${t_dto.getHobby_t() eq 'y' }">checked</c:if>/> 여행
					  <input type="checkbox" value="y" name="t_hobby_t" class="middleCheck" disabled
					  <c:if test="${t_dto.getHobby_r() eq 'y' }">checked</c:if>/> 독서
					  <input type="checkbox" value="y" name="t_hobby_t" class="middleCheck" disabled
					  <c:if test="${t_dto.getHobby_s() eq 'y' }">checked</c:if>/> 운동
					  
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
			  </tbody>
			 
			</table>
			
			<div class="buttonGroup_center">
				<a href="javascript:goWork('memberUpadteForm')" class="butt">수정</a>
				<a href="javascript:goDelete('memberDelete')" class="butt">탈퇴</a>
			</div>	
		</div>	
		
<%@ include file="../common_footer.jsp" %>







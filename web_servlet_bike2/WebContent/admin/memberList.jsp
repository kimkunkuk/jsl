<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>    
<%@ include file="../common_menu_admin.jsp" %>
<script>
	function goSearch(){
		adm.method="post";
		adm.action="Admin";
		adm.submit();
	}
	function goPage(pageNumber){
		adm.t_nowPage.value = pageNumber;
		adm.method="post";
		adm.action="Admin";
		adm.submit();
	}
</script>	
		<div id="b_right">
			<p class="n_title">
				MEMBER LIST
			</p>
			<div class="record_group record_group_left">
				<p><i class="fa-solid fa-bell"></i> 총회원수<span> ${t_totalCount} </span>명</p>
			</div>
			<form name="adm">			
			<p class="select_box select_box_right">
			<input type="hidden" name="t_nowPage">
				<select name="t_display_count" class="sel_box">
					<option value="5"  <c:if test="${t_display_count eq '5' }">selected</c:if> >5명</option>
					<option value="10" <c:if test="${t_display_count eq '10' }">selected</c:if> >10명</option>
					<option value="20" <c:if test="${t_display_count eq '20' }">selected</c:if> >20명</option>
					<option value="50" <c:if test="${t_display_count eq '50' }">selected</c:if> >50명</option>
				</select>
				<select name="t_select" class="sel_box">
					<option value="id" <c:if test="${t_select eq 'id'}">selected</c:if>   >ID</option>
					<option value="name" <c:if test="${t_select eq 'name'}">selected</c:if> >Name</option>
					<option value="mobile_3" <c:if test="${t_select eq 'mobile_3'}">selected</c:if> >Mobile</option>
				</select>
				<input type="text" name="t_search" value="${t_search}" class="sel_text">
			
				<button type="button" onClick="goSearch()" class="sel_button"><i class="fa fa-search"></i> SEARCH</button>
			</p>			
			</form>
			<table class="boardList">
				<colgroup>
					<col width="5%">
					<col width="10%">
					<col width="10%">
					<col width="10%">
					<col width="20%">
					<col width="15%">
					<col width="25%">
					<col width="5%">
				</colgroup>
				<thead>
					<tr>
						<th>No</th>
						<th>ID</th>
						<th>성명</th>
						<th>지역</th>
						<th>연락처</th>
						<th>가입일자</th>
						<th>로그인정보</th>
						<th>탈퇴여부</th>
					</tr>
				</thead>
				<tbody>
				<c:set var="number" value="${t_order}"></c:set> 
				<c:forEach items="${t_arr}" var="arr">
					<tr>
						<td>
							${number}
							<c:set var="number" value="${number-1}"></c:set> 
						</td>
						<td class="t_left" style="text-align:center;"><a href="#">${arr.getId()}</a></td>
						<td>${arr.getName()}</td>
						<td>${arr.getArea()}</td>
						<td>
							${arr.getMobile_1()}-
							${arr.getMobile_2()}-
							${arr.getMobile_3()}
						</td>
						<td>${arr.getReg_date()}</td>
						<td>${arr.getLogin_date()}</td>
						<td>
							<c:if test="${arr.getAccount() eq 'n'}">탈퇴</c:if>
						</td>
					</tr>
				</c:forEach>		
				</tbody>
			</table>
			
			<div class="paging">
				${t_paging}
			</div>
		</div>	
	</div>
	<%@include file="../common_footer.jsp" %>
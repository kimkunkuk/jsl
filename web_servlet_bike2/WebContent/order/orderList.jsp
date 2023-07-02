<%@page import="dao.MemberDao"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common_header.jsp" %>
<script>
	
</script>
<%@ include file="../common_menu_member.jsp" %>
<style>
	.view_img{
		width:100px;
		height:100px;
	}
</style>				
<div id="b_right">
			<p class="n_title">
				ORDER LIST
			</p>
			<div class="record_group record_group_left">
				<p><i class="fa-solid fa-bell"></i> 총게시글<span>  </span>건</p>
			</div>
			<form name="pro">	
			<input type="hidden" name="t_nowPage">
			<p class="select_box select_box_right">
				<select name="t_select" class="sel_box">
					<option value="title" <c:if test="${t_select eq 'title'}"> selected </c:if> >제품명</option>
				</select>
				<input type="text" name="t_search" value="${t_search}" class="sel_text">

				<button type="button" onclick="goSearch()" class="sel_button"><i class="fa fa-search"></i> SEARCH</button>
			</p>
			</form>				
			
			<table class="boardList">
				<colgroup>
					<col width="15%">
					<col width="15%">
					<col width="30%">
					<col width="20%">
					<col width="20%">
				</colgroup>
				<thead>
					<tr>
						<th>제품명</th>
						<th>가격</th>
						<th>사진</th>
						<th>주문상황</th>
						<th>주문일</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${t_arr}" var="arr">
					<tr>
						<td class="t_left"><a href="javascript:goView('${arr.getS_no()}')">${arr.getTitle()}</a></td>
						<td><fmt:formatNumber value="${arr.getPrice()}" pattern="#,###"/></td>
						<td>
							<img src="attach/product/${arr.getAttach()}"/ class="view_img">
						</td>
						<td>${arr.getState()}</td>
						<td>${arr.getReg_date()}</td>
					</tr>
				</c:forEach>	
				</tbody>
			</table>
			
			<div class="paging">
				${t_paging}
			</div>
		</div>	
	</div>
		
<%@ include file="../common_footer.jsp" %>







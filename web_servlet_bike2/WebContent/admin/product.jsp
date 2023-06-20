<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common_header.jsp" %>
<%@ include file="../common_menu_admin.jsp" %>
<div id="b_right">
			<p class="n_title">
				NEWS
			</p>
			<div class="record_group record_group_left">
				<p><i class="fa-solid fa-bell"></i> 총게시글<span> ${t_totalcount} </span>건</p>
			</div>
			<form name="news">	
			<p class="select_box select_box_right">
			<input type="hidden" name="t_nowPage">
				<select name="t_select" class="sel_box">
					<option value="a.title" <c:if test="${t_select eq 'a.title'}"> selected </c:if> >Title</option>
					<option value="a.content" <c:if test="${t_select eq 'a.content'}"> selected </c:if> >Content</option>
				</select>
				<input type="text" name="t_search" value="${t_search}" class="sel_text">

				<button type="button" onclick="goSearch()" class="sel_button"><i class="fa fa-search"></i> SEARCH</button>
			</p>
			</form>				
			
			<table class="boardList">
				<colgroup>
					<col width="5%">
					<col width="60%">
					<col width="15%">
					<col width="14%">
					<col width="6%">
				</colgroup>
				<thead>
					<tr>
						<th>No</th>
						<th>Title</th>
						<th>Reg Name</th>
						<th>Reg Date</th>
						<th>Hit</th>
					</tr>
				</thead>
				<tbody>
				<c:set var="number" value="${t_order}"></c:set>
				<c:forEach items="${t_arr}" var="arr">
					<tr>
						<td>${number}
							<c:set var="number" value="${number-1}"></c:set>
						</td>
						<td class="t_left"><a href="javascript:goView('${arr.getNo()}')">${arr.getTitle()}</a></td>
						<td>${arr.getReg_name()}</td>
						<td>${arr.getReg_date()}</td>
						<td>${arr.getHit()}</td>
					</tr>
				</c:forEach>	
				</tbody>
			</table>
			
			<div class="paging">
				${t_paging}
				<a href="javascript:goAdmin('writeForm')" class="write">글쓰기</a>
			</div>
		</div>	
	</div>

<%@ include file="../common_footer.jsp" %>
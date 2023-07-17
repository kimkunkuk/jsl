<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<%@ include file="../common_menu.jsp" %>
<script>
	function goSearch(){
		etc.t_gubun.value="etc";
		etc.method="post";
		etc.action="Notice";
		etc.submit();
	}
</script>
<div id="b_right">
			<p class="n_title">
				ETC
			</p>
			<div class="record_group record_group_left">
				<p><i class="fa-solid fa-bell"></i> 총게시글<span>${t_totalCount}</span>건</p>
			</div>		
			<form name="etc">	
			<p class="select_box select_box_right">
				<input type="hidden"  name="t_gubun">
				<select name="t_select" class="sel_box">
					<option value="n.title" selected >Title</option>
					<option value="n.content"  >Content</option>
				</select>
				<input type="text" name="t_search" value="" class="sel_text">
			
				<button type="button"  onclick="goSearch()" class="sel_button"><i class="fa fa-search"></i> SEARCH</button>
			</form>
			</p>			
			
			<table class="boardList">
				<colgroup>
					<col width="5%">
					<col width="60%">
					<col width="5%">
					<col width="10%">
					<col width="14%">
					<col width="6%">
				</colgroup>
				<thead>
					<tr>
						<th>No</th>
						<th>Title</th>
						<th>Attach</th>
						<th>Reg Name</th>
						<th>Reg Date</th>
						<th>Hit</th>
					</tr>
				</thead>
				<tbody>
				
				<c:forEach items="${t_arr }" var="arr">
					<tr>
						<td>
							${number}
							<c:set var="number" value="${number-1}"></c:set> 
						</td>
						<td class="t_left"><a href="#">${arr.getTitle() }</a></td>
						<td>
							<c:if test="${not empty arr.getAttach()  }">
							<img src="images/clip.png">
							</c:if>
						</td>
						<td>${arr.getReg_name()}</td>
						<td>${arr.getReg_date()}</td>
						<td>${arr.getHit()}</td>
					</tr>
				</c:forEach>	
					
				</tbody>
			</table>
			
			<div class="paging">
				${t_paging}
				<a href="notice_write.html" class="write">글쓰기</a>
			</div>
		</div>	
	</div>

<%@ include file="../common_footer.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<%@ include file="../common_menu.jsp" %>		
<script>
	function goSearch(val){
		notice.t_gubun.value = val;
		notice.method="post";
		notice.action="Notice";
		notice.submit();
	}
</script>
		<div id="b_right">
			<p class="n_title">
				NOTICE
			</p>
			<div class="record_group record_group_left">
				<p><i class="fa-solid fa-bell"></i> 총게시글<span> 4 </span>건</p>
			</div>			
			<p class="select_box select_box_right">
			<form name="notice">
			<input type="hidden" name="t_gubun">
				<select name="t_line" class="select" style="width:100px;" onchange="changeLine()">
					<option value="5" >5줄 보기</option>
					<option value="10" >10줄 보기</option>
				</select>
				<select name="t_select" class="sel_box">
					<option value="" selected >Title</option>
					<option value=""  >Content</option>
				</select>
				<input type="text" name="t_search" value="" class="sel_text">

				<button type="button" onclick="goSearch('List')" class="sel_button"><i class="fa fa-search"></i> SEARCH</button>
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
						<td></td>
						<td class="t_left"><a href="notice_view.html">${arr.getTitle() }</a></td>
						<td><img src="../images/clip.png"></td>
						<td>${arr.getReg_name() }</td>
						<td>${arr.getReg_date() }</td>
						<td>${arr.getHit() }</td>
					</tr>
				</c:forEach>		
				</tbody>
			</table>
			
			<div class="paging">
				<a href=""><i class="fa fa-angle-double-left"></i></a>
				<a href=""><i class="fa fa-angle-left"></i></a>
				<a href="" class="active">1</a>
				<a href="">2</a>
				<a href="">3</a>
				<a href="">4</a>
				<a href="">5</a>
				<a href=""><i class="fa fa-angle-right"></i></a>
				<a href=""><i class="fa fa-angle-double-right"></i></a>
				<a href="notice_write.html" class="write">글쓰기</a>
			</div>
		</div>	
	</div>
<%@ include file="../common_footer.jsp" %>







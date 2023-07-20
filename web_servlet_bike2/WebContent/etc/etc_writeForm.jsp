<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>    

<c:if test="${sessionLevel ne 'admin' and sessionLevel ne 'member'}">
	<script>
		alert("로그인이 필요합니다.");
		location.href="Member";
	</script>
</c:if>


<%@ include file="../common_menu.jsp" %>	
<script>
	function goSave(){
		if(checkValue(noti.t_title, "제목입력!")) return;
		if(checkValue(noti.t_content, "내용입력!")) return;
		
		noti.method="post";
		noti.action="Etc?t_gubun=save";
		noti.submit();
	}
</script>	

		<div id="b_right">
			<p class="n_title">
				ETC
			</p>
			<form name="noti">
			<table class="boardForm">
			
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="10%">
					<col width="40%">
				</colgroup>
				<tbody>
					<tr>
						<th>Title</th>
						<td colspan="3"><input type="text" name="t_title" class="input600"></td>
					</tr>	
					<tr>
						<th>Content</th>
						<td colspan="3"><textarea name="t_content" class="textArea_H250"></textarea></td>
					</tr>
					<tr>
						<th>Writer</th>
						<td>${sessionName}</td>
						<th>RegDate</th>
						<td>${t_todayTime}</td>
					</tr>	
				</tbody>
			</table>
			</form>
			<div class="buttonGroup">
				<a href="javascript:goSave()" class="butt">Save</a>
				<a href="Etc" class="butt">List</a>
			</div>	
		</div>	
	</div>
<%@ include file="../common_footer.jsp" %>







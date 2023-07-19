<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<%@ include file="../common_menu.jsp" %>
<script>
	function goView(no){
		view.t_gubun.value = "view";
		view.t_no.value = no;
		view.method="post";
		view.action="Product";
		view.submit();
	}
	function goUpdate(no){
		view.t_gubun.value ="update";
		view.t_no.value = no;
		view.method="post";
		view.action="FreeBoard";
		view.submit();
	}
	function goSale(no){
		view.t_gubun.value ="productBuy";
		view.t_no.value = no;
		view.method="post";
		view.action="Product";
		view.submit();
	}
	function goDelete(no){
		if(confirm("삭제 하시겠습니까?")){
			view.t_gubun.value ="delete";
			view.t_no.value = no;
			view.method="post";
			view.action="FreeBoard";
			view.submit();
		}
	}
	function goDown(no){
		
		view.t_no.value = no; 
		view.method="post";
		view.action="Filedown";
		view.submit();
		
		$.ajax({
			type : "POST",
			url : "DownHit",
			async: false, 
			data: "t_no="+no,
			dataType : "text", //순간적으로 띄운 브라우저 에 글씨 받아오겠다.
			error : function(){
				alert('통신실패!!!!!');
			},
			success : function(data){
				var result = $.trim(data);
				con.down.value = result;
				
			}
		});	
	}
	
</script>		
<style>
	.view_img{
		width:350px;
		height:350px;
		border: 1px solid black;
		margin-left: 100px;
	}
</style>
<form name="view">
	<input type="hidden" name="t_gubun">
	<input type="hidden" name="t_no">
	<input type="hidden" name="t_attach" value="${t_dto.getAttach()}">
	<input type="hidden" name="t_fileName" value="${t_dto.getAttach()}">
	<input type="hidden" name="t_fileDir" value="freeboard">
</form>
		<div id="b_right">
			<p class="n_title">
				FREE BOARD
			</p>
			<form name="con">
			<table class="boardForm">
				<colgroup>
					<col width="15%">
					<col width="45%">
					<col width="10%">
					<col width="30%">
				</colgroup>
				<tbody>
					<tr>
						<th>Title</th>
						<td colspan="2">${t_dto.getTitle()}</td>
						<td> <i class="far fa-eye"></i> ${t_dto.getHit()} / 
						다운로드: <input type="text" name="down" value="${t_dto.getDw_hit()}" style="width:25px">회</td>
					</tr>	
						<style>
						.viewContent{
							white-space:pre-wrap;
							
						}
					</style>
					<tr>
						<th>Content</th>
						<td colspan="3">
						<c:if test="${t_extension eq 'jpg' || t_extension eq 'png' || t_extension eq 'gif' || t_extension eq 'jpeg'}">
						<img src="attach/freeboard/${t_dto.getAttach()}"/ class="view_img">
						<div style="margin-bottom:15px;"></div>
						</c:if>
						<div class="viewContent">${t_dto.getContent()}</div>
						</td>
					</tr>		
					<tr>
						<th>Attach</th>
						<td colspan="3"><a href="javascript:goDown('${t_dto.getNo()}')">${t_dto.getAttach()}</a></td>
					</tr>	
					<tr>
						<th rowspan="2">Writer</th>
						<td rowspan="2">${t_dto.getName()}</td>
						<th>RegDate</th>
						<td>${t_dto.getReg_date()}</td>
					</tr>	
					<tr>
						<th>update</th>
						<td>${t_dto.getUpdate_date()}</td>
					</tr>
				</tbody>
			</table>
			</form>
			<div class="preNext">
				<c:if test="${!empty t_predto.getTitle()}">
				<a href="javascript:goView('${t_predto.getNo()}')">
					<p class="pre"><span><i class="fa-solid fa-circle-arrow-left"></i> 이전글</span> 
						<span class="preNextTitle">
									
									<!-- 
									<c:if test="${fn:length(t_predto.getTitle()) >= 10 }">
										${fn:substring(t_predto.getTitle(), 0, 10)}...
									</c:if>
									 -->
									<c:choose>
										<c:when test="${fn: length(t_predto.getTitle()) < 10 }">
											${t_predto.getTitle()}
										</c:when>
										<c:when test="${fn:length(t_predto.getTitle()) >= 10 }">
											${fn:substring(t_predto.getTitle(), 0, 10)}...
										</c:when>
									</c:choose>
						</span>
					</p>
				</a>
				</c:if>
				<c:if test="${!empty t_nextdto.getTitle()}">
				<a href="javascript:goView('${t_nextdto.getNo()}')">
					<p class="next"><span>다음글 <i class="fa-solid fa-circle-right"></i></span>
						<span class="preNextTitle">
									<c:choose>
										<c:when test="${fn:length(t_nextdto.getTitle()) >= 10 }">
											${fn:substring(t_nextdto.getTitle(), 0, 10)}...
										</c:when>
										<c:otherwise>
											${t_nextdto.getTitle()}
										</c:otherwise>
									</c:choose>
						</span>
					</p>
				</a>
				</c:if>
				
			</div>			
			<div class="buttonGroup">
			<c:if test="${sessionId == t_dto.getReg_id()}">
				<a href="javascript:goUpdate('${t_dto.getNo()}')" class="butt">Update</a>
				<a href="javascript:goDelete('${t_dto.getNo()}')" class="butt">Delete</a>
			</c:if>
				<a href="FreeBoard" class="butt">List</a>
			</div>	
		</div>	
	</div>
<%@ include file="../common_footer.jsp" %>







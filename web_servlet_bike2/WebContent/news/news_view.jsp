<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<%@ include file="../common_menu.jsp" %>	
<script>
	function goView(no){
		view.t_gubun.value = "view";
		view.t_no.value = no;
		view.method="post";
		view.action="News";
		view.submit();
	}
	function goUpdate(no){
		view.t_gubun.value ="update";
		view.t_no.value = no;
		view.method="post";
		view.action="News";
		view.submit();
	}
</script>		
<style>
	.view_img{
		width:350px;
		height:350px;
	}
</style>
<form name="view">
	<input type="hidden" name="t_gubun">
	<input type="hidden" name="t_no">
</form>
		<div id="b_right">
			<p class="n_title">
				NEWS
			</p>
			
			<table class="boardForm">
				<colgroup>
					<col width="15%">
					<col width="55%">
					<col width="10%">
					<col width="20%">
				</colgroup>
				<tbody>
					<tr>
						<th>Title</th>
						<td colspan="2">${t_dto.getTitle()}</td>
						<td> <i class="far fa-eye"></i> ${t_dto.getHit()}</td>
					</tr>	
					<tr>
						<th>Content</th>
						<td colspan="3">
						<img src="attach/news/${t_dto.getAttach()}"/ class="view_img">;
							<textarea class="textArea_H250_noBorder" readonly>${t_dto.getContent()}</textarea>
						</td>
					</tr>
					<style>
						.viewContent{
							white-space:pre-wrap;
						}
					</style>
					<tr>
						<th>Content</th>
						<td colspan="3">
							<div class="viewContent">${t_dto.getContent()}</div>
						</td>
					</tr>		
					<tr>
						<th>Attach</th>
						<td colspan="3"><a href="common/filedown.jsp?t_fileDir=notice&t_fileName=${t_dto.getAttach()}">${t_dto.getAttach()}</a></td>
					</tr>	
					<tr>
						<th>Writer</th>
						<td>${t_dto.getReg_name()}</td>
						<th>RegDate</th>
						<td>${t_dto.getReg_date()}</td>
					</tr>	

				</tbody>
			</table>
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
										<c:when test="${fn:length(t_predto.getTitle()) < 10 }">
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
				<a href="" class="butt">Delete</a>
				<a href="javascript:goUpdate('${t_dto.getNo()}')" class="butt">Update</a>
				<a href="Notice" class="butt">List</a>
			</div>	
		</div>	
	</div>
<%@ include file="../common_footer.jsp" %>







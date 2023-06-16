<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<%@ include file="../common_menu.jsp" %>	
		
		<div id="b_right">
			<p class="n_title">
				UPDATE
			</p>
			
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
						<td colspan="3"><input type="text" class="input600" value="${t_dto.getTitle()}"></td>
					</tr>	
					<tr>
						<th>Content</th>
						<td colspan="3"><textarea class="textArea_H250">${t_dto.getContent()}</textarea></td>
					</tr>	
					<tr>
						<th>Attach</th>
							<td colspan="3">
								<c:if test="${not empty t_dto.getAttach()}">
								${t_dto.getAttach()}&nbsp;&nbsp;
								삭제&nbsp;<input type="checkbox" name="t_delFile" value="${t_dto.getAttach()}">
								</c:if>
								<input type="file" class="input600">
							</td>
					</tr>	
					<tr>
						<th>Writer</th>
						<td>${sessionName}</td>
						<th>RegDate</th>
						<td>${t_todayTime}</td>
					</tr>	

				</tbody>
			</table>
			<div class="buttonGroup">
				<a href="notice_list.html" class="butt">Save</a>
				<a href="notice_list.html" class="butt">List</a>
			</div>	
		</div>	
	</div>
<%@ include file="../common_footer.jsp" %>






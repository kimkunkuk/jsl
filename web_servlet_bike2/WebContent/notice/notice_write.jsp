<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>    

<c:if test="${sessionLevel ne 'admin' }">
	<script>
		alert("관리자 화면 입니다!");
		location.href="Notice";
	</script>
</c:if>

<%@ include file="../common_menu.jsp" %>	
<script>
	function goSave(){
		if(checkValue(noti.t_title, "제목입력!")) return;
		if(checkValue(noti.t_content, "내용입력!")) return;
		
		/***  첨부파일 검사 ***/
		// 1.확장자 검사
/*		var fileName = noti.t_attach.value;
		if(fileName != ""){ //  C:\fakepath\img_1.png
			var pathFileName = fileName.lastIndexOf(".")+1;    //확장자 제외한 경로+파일명
			var extension = (fileName.substr(pathFileName)).toLowerCase();	//확장자명
			//파일명.확장자
//			if(extension != "jpg" && extension != "gif" && extension != "png"){
			if(extension != "pdf"){
				alert(extension +" 형식 파일은 업로드 안됩니다. 한글, PDF 파일만 가능!");
				return;
			}		
		}
*/

		// 2.첨부 용량 체크	
		var file = noti.t_attach;
		var fileMaxSize  = 10; // 첨부 최대 용량 설정
		if(file.value !=""){
			// 사이즈체크
			var maxSize  = 1024 * 1024 * fileMaxSize;  //1024 * 1024 * 10바이트 = 10메가
			var fileSize = 0;
			// 브라우저 확인
			var browser=navigator.appName;
			// 익스플로러일 경우
			if (browser=="Microsoft Internet Explorer"){
				var oas = new ActiveXObject("Scripting.FileSystemObject");
				fileSize = oas.getFile(file.value).size;
			}else {
			// 익스플로러가 아닐경우
				fileSize = file.files[0].size;
			}

			if(fileSize > maxSize){
				alert(" 첨부파일 사이즈는 "+fileMaxSize+"MB 이내로 등록 가능합니다. ");
				return;
			}	
		}
		//noti.t_gubun.value = "save"; 첨부파일 폼 형식 이여서 겟파라미터 안되서 못넘김 그래서  get방식으로 직접 넘기든가 해야댐 아니면 인풋에 벨류값 주던가 
		noti.method="post";
		noti.action="Notice?t_gubun=save";
		noti.submit();
	}
</script>		
		<div id="b_right">
			<p class="n_title">
				NOTICE
			</p>
			<form name="noti" enctype="multipart/form-data">
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
						<th>Attach</th>
						<td colspan="3"><input type="file" name="t_attach" class="input600"></td>
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
				<a href="Notice" class="butt">List</a>
			</div>	
		</div>	
	</div>
<%@ include file="../common_footer.jsp" %>







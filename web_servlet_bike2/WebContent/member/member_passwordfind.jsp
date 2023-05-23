<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common_header.jsp" %>
<script>
	function memberPassword(){
		if(checkValue(goLogin.t_id,"id입력!")) return;
		if(checkValue(goLogin.t_mobile_1,"전화번호입력!")) return;
		if(checkValue(goLogin.t_mobile_2,"전화번호입력!")) return;
		if(checkValue(goLogin.t_mobile_3,"전화번호입력!")) return;
		goLogin.method="post";
		goLogin.action="Member?t_gubun=memberPasswordSend";
		goLogin.submit();
	}
	
</script>
		<div id="b_left">
			<P>MEMBER</P>
			<ul>
				<li><a href="javascript:goWork('memberLogin')">LOGIN</a></li>
				<li><a href="javascript:goWork('memberPasswordFind')"><span class="fnt"><i class="fas fa-apple-alt"></i></span>ID / PASSWORD</a></li>
				<li><a href="javascript:goWork('memberJoin')"> JOIN</a></li>
			</ul>
		</div>
		
		<div id="b_right">
			<p class="n_title">
				비밀번호 찾기
			</p>
		
			<div class="login">
				<div class="member_boxL">
					<h2>비밀번호 찾기</h2>
					<div class="login_form">
						<form name="goLogin">
							<input type="hidden" name="t_gubun" value="login">
							<div class="fl_clear"><label for="mbrId">아이디 </label><input name="t_id" class="t_id" type="text" ></div>
							<div class="fl_clear"><label for="scrtNo">전화번호</label>
							<input name="t_mobile_1" class="t_mobile" id="scrtNo" type="text">
							<input name="t_mobile_2" class="t_mobile" id="scrtNo" type="text">
							<input name="t_mobile_3" class="t_mobile" id="scrtNo" type="text">
							</div>
							<div class="fl_clear"><label for="mbrId">이메일 </label><input name="t_email" class="t_id" type="text" ></div>
							
							<a class="btn_login btn_Blue" href="javascript:memberPassword()">찾기</a>
							
						</form>
					</div>
				   
				</div>		
			</div>

		</div>	
	</div>
<%@ include file="../common_footer.jsp" %>







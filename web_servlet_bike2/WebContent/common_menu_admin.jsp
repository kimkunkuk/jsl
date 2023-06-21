<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<script>
	function goMenu(val){
		var menuName= "";
		if(val == "admin"){
			menuName = "Admin";
		}else if(val == "product"){
			menuName = "Product";
		}
		menu.t_gubun.value = val;
		menu.method="post";
		menu.action= menuName;
		menu.submit();
	}
</script>
<form name="menu">
	<input type="hidden" name="t_gubun">
</form>
<div id="b_left">
			<P>NOTICE & NEWS</P>
			<ul>
				<li>
				<a href="Admin">
					<c:if test="${t_ma eq 'admin' }">
					<span class="fnt"><i class="fas fa-apple-alt"></i></span> 
					</c:if>
					MEMBER LIST
				</a>
				</li>
				<li>
				<a href="javascript:goMenu('product')">
					<c:if test="${t_ma eq 'product' }">
					<span class="fnt"><i class="fas fa-apple-alt"></i></span> 
					</c:if>
					PRODUCT
				</a>
				</li>
			</ul>
</div>
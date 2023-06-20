<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
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
				<a href="Product">
					<c:if test="${t_ma eq 'product' }">
					<span class="fnt"><i class="fas fa-apple-alt"></i></span> 
					</c:if>
					PRODUCT
				</a>
				</li>
			</ul>
</div>
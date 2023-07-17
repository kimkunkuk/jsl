<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<div id="b_left">
			<P>NOTICE & NEWS</P>
			<ul>
				<li>
				<a href="Notice">
					<c:if test="${t_ma eq 'notice' }">
					<span class="fnt"><i class="fas fa-apple-alt"></i></span> 
					</c:if>
					NOTICE
				</a>
				</li>
				<li>
				<a href="News">
					<c:if test="${t_ma eq 'news' }">
					<span class="fnt"><i class="fas fa-apple-alt"></i></span> 
					</c:if>
					NEWS
				</a>
				</li>
				<li>
				<a href="Qna">
					<c:if test="${t_ma eq 'qna' }">
					<span class="fnt"><i class="fas fa-apple-alt"></i></span> 
					</c:if>
					Q & A
				</a>
				</li>
				<li>
				<a href="FreeBoard">
					<c:if test="${t_ma eq 'free' }">
					<span class="fnt"><i class="fas fa-apple-alt"></i></span> 
					</c:if>
					FREE BOARD
				</a>
				</li>
				<li>
				<a href="javascript:goNotice('etc')">
					<c:if test="${t_ma eq 'etc' }">
					<span class="fnt"><i class="fas fa-apple-alt"></i></span> 
					</c:if>
					ETC
				</a>
				</li>
			</ul>
</div>
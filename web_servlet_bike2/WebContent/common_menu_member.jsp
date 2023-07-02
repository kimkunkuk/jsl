<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<div id="b_left">
			<P>내정보</P>
			<ul>
				<li>
				<a href="Member?t_gubun=memberMyinfo">
					<c:if test="${t_ma eq 'myinfo' }">
					<span class="fnt"><i class="fas fa-apple-alt"></i></span> 
					</c:if>
					MYINFO
				</a>
				</li>
				<li>
				<a href="Order">
					<c:if test="${t_ma eq 'order' }">
					<span class="fnt"><i class="fas fa-apple-alt"></i></span> 
					</c:if>
					ORDER LIST
				</a>
				</li>
			</ul>
</div>
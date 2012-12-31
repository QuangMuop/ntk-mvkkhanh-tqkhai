<%-- 
    Document   : quick_login
    Created on : Oct 29, 2012, 11:08:43 AM
    Author     : TrongKhoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="adsImagesFolder" value="${requestScope.AdsImagesFolder}" />
<c:set var="dsQuangCao" value="${requestScope.DSQuangCao}" />


<div id="ads-left" align="center"
	style="width: 140px; height: 100%; position: fixed; top: 50px; left: 0px;">
	<c:forEach var="qc" items="${dsQuangCao}">
		<c:if test="${qc.viTri == 'left'}">
			<a href="#" onClick="">
				<img width="100px" alt="ads"
				src="${pageContext.request.contextPath}${adsImagesFolder}<c:out value="${qc.hinhAnh}"/>" />
			</a>
		</c:if>
	</c:forEach>
</div>


<div id="ads-right" align="center"
	style="width: 140px; height: 100%; position: fixed; top: 50px; right: 0px;">
	<c:forEach var="qc" items="${dsQuangCao}">
		<c:if test="${qc.viTri == 'right'}">
			<a href="#" onClick="">
				<img width="100px" alt="ads"
				src="${pageContext.request.contextPath}${adsImagesFolder}<c:out value="${qc.hinhAnh}"/>" />
			</a>
		</c:if>
	</c:forEach>
</div>
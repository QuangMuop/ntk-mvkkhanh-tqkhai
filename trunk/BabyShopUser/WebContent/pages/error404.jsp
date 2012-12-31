<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="homeController" value="${requestScope.HomeController}"/>
<div align="center">
<img alt="eror404" src="${pageContext.request.contextPath}/images/errors/Error404.jpg"/><br/>
<a href="${homeController}/index">Quay lại trang chủ</a>

</div>
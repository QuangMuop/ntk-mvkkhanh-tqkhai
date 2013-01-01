<%-- 
    Document   : quick_login
    Created on : Oct 29, 2012, 11:08:43 AM
    Author     : TrongKhoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${account.getHoTen() != null}">
	<li><a href="#">${account.getHoTen()}</a></li>                
    |
    <li><a href="${accountsController}login">Đăng xuất</a></li>
</c:if>
<c:if test="${account.getHoTen() == null}">
	<li><a href="${accountsController}register">Đăng ký</a></li>                
    |
   	<li><a href="${accountsController}login">Đăng nhập</a></li>
</c:if>

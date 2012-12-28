<%-- 
    Document   : Template
    Created on : Oct 29, 2012, 9:49:43 PM
    Author     : Khanh
--%>

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib  prefix="tiles"  uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/shared-res/css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/shared-res/css/style.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/jquery-1.8.2.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/navigation.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/modernizr.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/selectivizr.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/jquery.scrollTo-1.4.1-min.js"></script>       
        <tiles:insertAttribute name="lib" />
        <title>
            <tiles:insertAttribute name="title" />
        </title>
    </head>
       
    <!--Content -->
    <tiles:insertAttribute name="content"/>
</html>

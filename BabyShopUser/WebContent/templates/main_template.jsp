<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/shared-css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/shared-css/style.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
        <title>
            <tiles:insertAttribute name="title"/>
        </title>
        <tiles:insertAttribute name="additional_css_and_js"/>
    </head>
    <body class="shop-ribbon">
        <!-- START WRAPPER -->
        <div class="wrapper group"> 
            <!-- START HEADER -->
            <div id="header" class="group"> 
                <tiles:insertAttribute name="header"/>
            </div>
            <!-- END HEADER --> 
            <!-- START CONTENT -->
            <div id="main-content" class="inner">
                <tiles:insertAttribute name="content"/>
            </div>
            <!-- END CONTENT --> 
            <div class="clear"></div>
            <!-- START FOOTER --> 
            <div id="footer">
                <tiles:insertAttribute name="footer"/>
            </div>
            <!-- END FOOTER --> 
            <!-- START COPYRIGHT -->
            <div class="group" id="copyright">
                <tiles:insertAttribute name="copyright"/>
            </div>
            <!-- END COPYRIGHT --> 
        </div>
        <%@include file="/components/advertisements.jsp" %>
        <!-- END WRAPPER -->
    </body>
</html>
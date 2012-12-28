<%-- 
    Document   : css_and_js_product_details
    Created on : Oct 29, 2012, 10:45:14 AM
    Author     : TrongKhoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/objects.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/product-details/product-details.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ok-ui-kit.css"/>
<!--JQUERY UI-->

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.css"/>
<script>
    $(function() {
        $( "#tabs" ).tabs();
    });
</script>

<!--END JQUERY UI-->
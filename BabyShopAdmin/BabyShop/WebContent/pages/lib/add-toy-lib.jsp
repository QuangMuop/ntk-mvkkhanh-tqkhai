<%-- 
    Document   : add-toy-lib
    Created on : Oct 30, 2012, 12:10:56 PM
    Author     : Khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/shared-res/css/plugins/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/bootstrap-dropdown.js"></script>
<script>
	$(document).ready(function() {
		$('.datepicker').datepicker();
		$("#topOfPage").click(function(){
			$.scrollTo( 0, 500);
			return false;
			});
	});
</script>
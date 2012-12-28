<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/bootstrap-dropdown.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/views-res/cart/action.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/jquery-ui-1.9.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/jquery.clearsearch.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/shared-res/css/blitzer/jquery-ui-1.9.2.min.css"/>

<script>
	$(document).ready(function() {

		 $("#topOfPage").click(function(){
				$.scrollTo( 0, 500);
				return false;
				});
		 $( ".datepicker" ).datepicker(
				{
	            changeMonth: true,
	            changeYear: true,
	            dateFormat: "yy-mm-dd",
	        });
		 $('.clearable').clearSearch();
	});

</script>
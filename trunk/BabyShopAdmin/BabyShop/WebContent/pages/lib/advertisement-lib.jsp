<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/jquery.prettyPhoto.js"></script>
<link href="${pageContext.request.contextPath}/shared-res/css/plugins/prettyPhoto.css" rel="stylesheet" type="text/css"/>

<script>
	$(document).ready(function() {
		$('a[rel^="prettyPhoto"]').prettyPhoto({
			 theme: "light_rounded"
	        });
        
		 $("#topOfPage").click(function(){
				$.scrollTo( 0, 500);
				return false;
				});
        }); 
</script>
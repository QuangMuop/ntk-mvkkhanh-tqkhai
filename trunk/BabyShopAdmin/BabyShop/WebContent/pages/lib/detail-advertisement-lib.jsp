<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/bootstrap-dropdown.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/jquery.prettyPhoto.js"></script>
<link href="${pageContext.request.contextPath}/shared-res/css/plugins/prettyPhoto.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/jquery-ui-1.9.2.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/shared-res/css/blitzer/jquery-ui-1.9.2.min.css"/>
<script>
	$(document).ready(function() {
		$('a[rel^="prettyPhoto"]').prettyPhoto({
			 theme: "light_rounded"
	        });
        
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
		 InitDisabledState();
         $('.request-edit').click(
			function(){
                   $('#inputPosition').attr('disabled', !$(this).is(':checked'));
                   $('#inputLink').attr('disabled', !$(this).is(':checked'));   
                   $('#inputExpiredDate').attr('disabled', !$(this).is(':checked'));
                   $('#chkHienThi').attr('disabled', !$(this).is(':checked'));                                                    
			}
		);
}); 
	function InitDisabledState()
    {
        $('#inputPosition').attr('disabled', true);
        $('#inputLink').attr('disabled', true);   
        $('#inputExpiredDate').attr('disabled', true);
        $('#chkHienThi').attr('disabled', true);                                                     
    } 
</script>
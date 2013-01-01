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
                   $('#inputMaQuangCao').attr('disabled', !$(this).is(':checked'));
                   $('#inputTenQuangCao').attr('disabled', !$(this).is(':checked'));   
                   $('#inputHinhAnh').attr('disabled', !$(this).is(':checked'));
                   $('#inputViTri').attr('disabled', !$(this).is(':checked'));
                   $('#inputNgayBatDau').attr('disabled', !$(this).is(':checked'));
                   $('#inputNgayKetThuc').attr('disabled', !$(this).is(':checked'));
                   $('#chkCoHieuLuc').attr('disabled', !$(this).is(':checked'));
                   $('#textareaMoTa').attr('disabled', !$(this).is(':checked'));
                   $('#btnLuu').attr('disabled', !$(this).is(':checked'));                                                    
			}
		);
}); 
	function InitDisabledState()
    {
		$('#inputMaQuangCao').attr('disabled', true);
        $('#inputTenQuangCao').attr('disabled', true);   
        $('#inputHinhAnh').attr('disabled', true);
        $('#inputViTri').attr('disabled', true);
        $('#inputNgayBatDau').attr('disabled', true);
        $('#inputNgayKetThuc').attr('disabled', true);
        $('#chkCoHieuLuc').attr('disabled', true);
        $('#textareaMoTa').attr('disabled', true);
        $('#btnLuu').attr('disabled', true);                                                   
    } 
</script>
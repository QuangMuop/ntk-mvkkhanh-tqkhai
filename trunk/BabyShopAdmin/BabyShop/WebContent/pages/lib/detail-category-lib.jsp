<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/bootstrap-dropdown.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/jquery-ui-1.9.2.js"></script>
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

		InitDisabledState();
                $('.request-edit').click(
			function(){
                                 $('#inputMaLoaiDoChoi').attr('disabled', !$(this).is(':checked'));
                                 $('#inputTenLoaiDoChoi').attr('disabled', !$(this).is(':checked'));   
                                 $('#inputNgayCapNhat').attr('disabled', !$(this).is(':checked'));
                                 $('#chkDaXoa').attr('disabled', !$(this).is(':checked'));
                                 $('#btnLuu').attr('disabled', !$(this).is(':checked'));                                                      
			}
		);
        });

    function InitDisabledState()
    {
    	$('#inputMaLoaiDoChoi').attr('disabled', true);
        $('#inputTenLoaiDoChoi').attr('disabled', true);   
        $('#inputNgayCapNhat').attr('disabled', true);
        $('#chkDaXoa').attr('disabled', true);
        $('#btnLuu').attr('disabled', true); 
    }  
</script>
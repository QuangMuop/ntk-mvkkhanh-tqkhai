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
                                 $('#inputMaHoaDon').attr('disabled', !$(this).is(':checked'));
                                 $('#selectTrangThaiDonHang').attr('disabled', !$(this).is(':checked'));   
                                 $('#inputNgayCapNhat').attr('disabled', !$(this).is(':checked'));
                                 $('#chkDaThanhToan').attr('disabled', !$(this).is(':checked'));
                                 $('#chkDaXoa').attr('disabled', !$(this).is(':checked'));   
                                 $('#btnLuu').attr('disabled', !$(this).is(':checked'));                                               
			}
		);
        });

    function InitDisabledState()
    {
    	 $('#inputMaHoaDon').attr('disabled', true);
    	 $('#selectTrangThaiDonHang').attr('disabled', true); 
    	 $('#inpuatNgayCapNhat').attr('disabled', true); 
         $('#chkDaThanhToan').attr('disabled', true);
         $('#chkDaXoa').attr('disabled', true);
         $('#btnLuu').attr('disabled', true);                                                     
    }  
</script>
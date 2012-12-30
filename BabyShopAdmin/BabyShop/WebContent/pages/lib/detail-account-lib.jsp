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
                                 $('#inputMaTaiKhoan').attr('disabled', !$(this).is(':checked'));
                                 $('#inputMatKhau').attr('disabled', !$(this).is(':checked'));   
                                 $('#selectLoaiTaiKhoan').attr('disabled', !$(this).is(':checked'));
                                 $('#inputHinhDaiDien').attr('disabled', !$(this).is(':checked'));
                                 $('#inputHoTen').attr('disabled', !$(this).is(':checked'));
                                 $('#inputNgaySinh').attr('disabled', !$(this).is(':checked'));
                                 $('#inputThanhPho').attr('disabled', !$(this).is(':checked'));
                                 $('#inputDienThoai').attr('disabled', !$(this).is(':checked'));
                                 $('#inputGioiTinhNam').attr('disabled', !$(this).is(':checked'));
                                 $('#inputGioiTinhNu').attr('disabled', !$(this).is(':checked'));
                                 $('#inputEmail').attr('disabled', !$(this).is(':checked'));
                                 $('#inputNgayCapNhat').attr('disabled', !$(this).is(':checked'));
                                 $('#chkDaXoa').attr('disabled', !$(this).is(':checked'));
                                 $('#chkDaBan').attr('disabled', !$(this).is(':checked'));
                                 $('#btnLuu').attr('disabled',  !$(this).is(':checked'));                                                      
			}
		);
        });

    function InitDisabledState()
    {
    	 $('#inputMaTaiKhoan').attr('disabled', true);
    	 $('#inputMatKhau').attr('disabled', true);  
         $('#selectLoaiTaiKhoan').attr('disabled', true);
         $('#inputHinhDaiDien').attr('disabled', true);
         $('#inputHoTen').attr('disabled', true);
         $('#inputNgaySinh').attr('disabled', true);
         $('#inputThanhPho').attr('disabled', true);
         $('#inputDienThoai').attr('disabled', true);
         $('#inputGioiTinhNam').attr('disabled', true);
         $('#inputGioiTinhNu').attr('disabled', true);
         $('#inputEmail').attr('disabled', true);
         $('#inputNgayCapNhat').attr('disabled', true);
         $('#chkDaXoa').attr('disabled', true);
         $('#chkDaBan').attr('disabled', true);
         $('#btnLuu').attr('disabled', true);                                           
    }  
</script>
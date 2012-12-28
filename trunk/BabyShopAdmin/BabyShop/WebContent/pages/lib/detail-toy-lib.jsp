<%-- 
    Document   : detail-toy-lib
    Created on : Oct 30, 2012, 3:14:33 PM
    Author     : Khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/bootstrap-dropdown.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/jquery.prettyPhoto.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/jquery-ui-1.9.2.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/shared-res/css/blitzer/jquery-ui-1.9.2.min.css"/>
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
		 $( ".datepicker" ).datepicker(
					{
		            changeMonth: true,
		            changeYear: true,
		            dateFormat: "yy-mm-dd",
		        });

		InitDisabledState();
                $('.request-edit').click(
			function(){
                                 $('#inputMaDoChoi').attr('disabled', !$(this).is(':checked'));
                                 $('#inputTenDoChoi').attr('disabled', !$(this).is(':checked'));   
                                 $('#selectLoaiDoChoi').attr('disabled', !$(this).is(':checked'));   
                                 $('#selectNhaSanXuat').attr('disabled', !$(this).is(':checked'));   
                                 $('#selectXuatXu').attr('disabled', !$(this).is(':checked'));   
                                 $('#inputGiaBanDau').attr('disabled', !$(this).is(':checked'));   
                                 $('#inputGiaHienTai').attr('disabled', !$(this).is(':checked'));
                                 $('#inputGiamGia').attr('disabled', !$(this).is(':checked'));
                                 $('#inputTinhTrang').attr('disabled', !$(this).is(':checked'));
                                 $('#inputSoLuongBan').attr('disabled', !$(this).is(':checked'));   
                                 $('#inputSoLuongXem').attr('disabled', !$(this).is(':checked'));   
                                 $('#inputSoLuongTon').attr('disabled', !$(this).is(':checked'));
                                 $('#inputSoLuongDaBan').attr('disabled', !$(this).is(':checked')); 
                                 $('#inputNgayTiepNhan').attr('disabled', !$(this).is(':checked')); 
                                 $('#inputNgayCapNhat').attr('disabled', !$(this).is(':checked'));
                                 $('#textareaMoTa').attr('disabled', !$(this).is(':checked'));
                                 $('#textareaHinhAnh').attr('disabled', !$(this).is(':checked'));
                                 $('#chkDaXoa').attr('disabled', !$(this).is(':checked'));
                                 $('#btnLuu').attr('disabled', !$(this).is(':checked'));                                                      
			}
		);
        });

    function InitDisabledState()
    {
    	$('#inputMaDoChoi').attr('disabled', true);
        $('#inputTenDoChoi').attr('disabled', true);   
        $('#selectLoaiDoChoi').attr('disabled', true);   
        $('#selectNhaSanXuat').attr('disabled', true);   
        $('#selectXuatXu').attr('disabled', true);   
        $('#inputGiaBanDau').attr('disabled', true);   
        $('#inputGiaHienTai').attr('disabled', true);
        $('#inputGiamGia').attr('disabled', true);
        $('#inputTinhTrang').attr('disabled', true);
        $('#inputSoLuongBan').attr('disabled', true);   
        $('#inputSoLuongXem').attr('disabled', true);   
        $('#inputSoLuongTon').attr('disabled', true);
        $('#inputSoLuongDaBan').attr('disabled', true); 
        $('#inputNgayTiepNhan').attr('disabled', true); 
        $('#inputNgayCapNhat').attr('disabled', true);
        $('#textareaMoTa').attr('disabled', true);
        $('#textareaHinhAnh').attr('disabled', true);
        $('#chkDaXoa').attr('disabled', true);
        $('#btnLuu').attr('disabled', true); 
    }  
</script>

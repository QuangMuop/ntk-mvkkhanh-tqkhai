<%@page contentType="text/html" pageEncoding="UTF-8"%>


<script>
	$(document).ready(function() {
        
		 $("#topOfPage").click(function(){
				$.scrollTo( 0, 500);
				return false;
				});

		InitDisabledState();
                $('.request-edit').click(
			function(){
				$('#inputMaBinhLuan').attr('disabled', !$(this).is(':checked'));
                 $('#textareaNoiDung').attr('disabled', !$(this).is(':checked'));
                 $('#chkKiemDuyet').attr('disabled', !$(this).is(':checked'));
                 $('#chkDaXoa').attr('disabled', !$(this).is(':checked'));
                 $('#btnLuu').attr('disabled', !$(this).is(':checked'));                                                
			}
		);
        });

    function InitDisabledState()
    {
    	$('#inputMaBinhLuan').attr('disabled', true);
    	 $('#textareaNoiDung').attr('disabled', true);
    	 $('#chkKiemDuyet').attr('disabled', true);
         $('#chkDaXoa').attr('disabled', true);
         $('#btnLuu').attr('disabled', true);                                                  
    }  
</script>
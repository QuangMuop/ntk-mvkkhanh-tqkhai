// JavaScript Document

$(document).ready(function(){
		$("#topOfPage").click(function(){
			$.scrollTo( 0, 500);
			return false;
		});
		$('.check-all').click(
					function(){
						$(this).parent().parent().parent().parent().find("input[type='checkbox']").attr('checked', $(this).is(':checked'));   
					}
		);
});

function confirmDelete(maHoaDon) {
    if (confirm("Are you sure you want to delete ?")) {               
  	  	var url = "delete";
  	  	
	  	var message = {
	  		maHoaDon: maHoaDon
	    };
        $.ajax({
            type: 'POST',
            url: url,
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(message),
            success: function (data) {
                if(data.result == "1")
                {
                	$("#isDaXoa_" + maHoaDon).text('Rồi');
                	$("#isDaXoa_" + maHoaDon).css("color", "red");
                }
                else if(data.result == "0")
                {
                	alert('Không thể đánh dấu xóa ! Vui lòng thử lại sau');
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(textStatus);
            }
        });
    }
  }

function confirmReshow(maHoaDon) {   
	if (confirm("Are you sure you want to show again ?")) {
  	  	var url = "reshow";
  	  	
	  	var message = {
	  			maHoaDon: maHoaDon
	    };
        $.ajax({
            type: 'POST',
            url: url,
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(message),
            success: function (data) {
                if(data.result == "1")
                {
                	$("#isDaXoa_" + maHoaDon).text('Chưa');
                	$("#isDaXoa_" + maHoaDon).css("color", "blue");
                }
                else if(data.result == "0")
                {
                	alert('Không thể gỡ đánh dấu xóa ! Vui lòng thử lại sau');
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(textStatus);
            }
        });
	}
  }

function calculateMultiDelete() {
	if (confirm("Are you sure you want to delete ?")) {   
	    var arr = $.map($("input:checkbox[name='options[]']:checked"), function(e, i) {
	        return +e.value;
	    });
    
    var message = [];
    for (var i = 0; i < arr.length; i++) {
        var arrayItem = arr[i];
        var obj    = {};
        obj["maHoaDon"] = arrayItem; 
        message.push(obj);
    }
    
    var url = "multiDelete";
    $.ajax({
        type: 'POST',
        url: url,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(message),
        success: function (data) {
        	var arrSuccess = [];
        	var arrFailed = [];
            data.forEach(function(value){
            	if(value.result == "1")
            	{
            		arrSuccess.push(value.maHoaDon);
            		$("#isDaXoa_" + value.maHoaDon).text('Rồi');
                	$("#isDaXoa_" + value.maHoaDon).css("color", "red");
                	$('input[id=chkbox_' + value.maHoaDon + ']').attr('checked', false);
            	}
            	else if (value.result == "0")
            	{
            		arrFailed.push(value.maHoaDon);
            	}         		
            }       
            );
            if(arrSuccess.length > 0 && arrFailed.length > 0)
        	{
            	alert('Các hóa đơn có mã là: ' + arrSuccess.join(',') + ' đã được đánh dấu xóa');
            	alert('Các hóa đơn có mã là: ' + arrFailed.join(',') + ' không xóa được');
        	}
            else if (arrSuccess.length > 0 && arrFailed.length == 0)
            {
            	alert('Các hóa đơn có mã là: ' + arrSuccess.join(',') + ' đã được đánh dấu xóa');
        	}
            else if (arrSuccess.length == 0 && arrFailed.length > 0)
            {
            	alert('Các hóa đơn có mã là: ' + arrFailed.join(',') + 'không xóa được');
        	}
            
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus);
        }
    });
	}
}

function clearSearchNgayLap(){
	$('.datepicker').datepicker( "setDate" , null );
}

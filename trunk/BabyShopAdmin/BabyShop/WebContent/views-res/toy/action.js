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


function confirmDelete(maDoChoi) {
    if (confirm("Are you sure you want to delete ?")) {               
  	  	var url = "delete";
  	  	
	  	var message = {
	       maDoChoi: maDoChoi
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
                	$("#isDaXoa_" + maDoChoi).text('Rồi');
                	$("#isDaXoa_" + maDoChoi).css("color", "red");
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

function confirmReshow(maDoChoi) {
	if (confirm("Are you sure you want to show again ?")) {
  	  	var url = "reshow";
  	  	
	  	var message = {
	       maDoChoi: maDoChoi
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
                	$("#isDaXoa_" + maDoChoi).text('Chưa');
                	$("#isDaXoa_" + maDoChoi).css("color", "blue");
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

function calculate() {
	if (confirm("Are you sure you want to delete ?")) {   
    var arr = $.map($("input:checkbox[name='options[]']:checked"), function(e, i) {
        return +e.value;
    });
    
    var message = [];
    for (var i = 0; i < arr.length; i++) {
        var arrayItem = arr[i];
        var obj    = {};
        obj["maDoChoi"] = arrayItem; 
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
            		arrSuccess.push(value.maDoChoi);
            		$("#isDaXoa_" + value.maDoChoi).text('Rồi');
                	$("#isDaXoa_" + value.maDoChoi).css("color", "red");
                	$('input[id=chkbox_' + value.maDoChoi + ']').attr('checked', false);
            	}
            	else if (value.result == "0")
            	{
            		arrFailed.push(value.maDoChoi);
            	}         		
            }       
            );
            if(arrSuccess.length > 0 && arrFailed.length > 0)
        	{
            	alert('Các đồ chơi có mã là: ' + arrSuccess.join(',') + ' đã được đánh dấu xóa');
            	alert('Các đồ chơi có mã là: ' + arrFailed.join(',') + ' không xóa được');
        	}
            else if (arrSuccess.length > 0 && arrFailed.length == 0)
            {
            	alert('Các đồ chơi có mã là: ' + arrSuccess.join(',') + ' đã được đánh dấu xóa');
        	}
            else if (arrSuccess.length == 0 && arrFailed.length > 0)
            {
            	alert('Các đồ chơi có mã là: ' + arrFailed.join(',') + 'không xóa được');
        	}
            
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus);
        }
    });
	}
}
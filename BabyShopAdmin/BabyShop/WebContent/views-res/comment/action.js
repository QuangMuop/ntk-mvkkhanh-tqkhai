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


function clearSearchNgayLap(){
	$('.datepicker').datepicker( "setDate" , null );
}

function confirmDelete(maBinhLuan) {
    if (confirm("Are you sure you want to delete ?")) {               
  	  	var url = "delete";
  	  	
	  	var message = {
	  			maBinhLuan: maBinhLuan
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
                	$("#isDaXoa_" + maBinhLuan).text('Rồi');
                	$("#isDaXoa_" + maBinhLuan).css("color", "red");
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

function confirmReshow(maBinhLuan) {   
	if (confirm("Are you sure you want to show again ?")) {
  	  	var url = "reshow";
  	  	
	  	var message = {
	  			maBinhLuan: maBinhLuan
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
                	$("#isDaXoa_" + maBinhLuan).text('Chưa');
                	$("#isDaXoa_" + maBinhLuan).css("color", "blue");
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
        obj["maBinhLuan"] = arrayItem; 
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
            		arrSuccess.push(value.maBinhLuan);
            		$("#isDaXoa_" + value.maBinhLuan).text('Rồi');
                	$("#isDaXoa_" + value.maBinhLuan).css("color", "red");
                	$('input[id=chkbox_' + value.maBinhLuan + ']').attr('checked', false);
            	}
            	else if (value.result == "0")
            	{
            		arrFailed.push(value.maBinhLuan);
            	}         		
            }       
            );
            if(arrSuccess.length > 0 && arrFailed.length > 0)
        	{
            	alert('Các bình luận có mã là: ' + arrSuccess.join(',') + ' đã được đánh dấu xóa');
            	alert('Các bình luận có mã là: ' + arrFailed.join(',') + ' không xóa được');
        	}
            else if (arrSuccess.length > 0 && arrFailed.length == 0)
            {
            	alert('Các bình luận có mã là: ' + arrSuccess.join(',') + ' đã được đánh dấu xóa');
        	}
            else if (arrSuccess.length == 0 && arrFailed.length > 0)
            {
            	alert('Các bình luận có mã là: ' + arrFailed.join(',') + 'không xóa được');
        	}
            
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus);
        }
    });
	}
}

function confirmApprove(maBinhLuan) {
    if (confirm("Are you sure you want to approve ?")) {               
  	  	var url = "approve";
  	  	
	  	var message = {
	  			maBinhLuan: maBinhLuan
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
                	$("#isDaDuyet_" + maBinhLuan).text('Rồi');
                	$("#isDaDuyet_" + maBinhLuan).css("color", "red");
                }
                else if(data.result == "0")
                {
                	alert('Không thể duyệt ! Vui lòng thử lại sau');
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(textStatus);
            }
        });
    }
  }

function confirmDisapprove(maBinhLuan) {   
	if (confirm("Are you sure you want to disapprove ?")) {
  	  	var url = "disapprove";
  	  	
	  	var message = {
	  			maBinhLuan: maBinhLuan
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
                	$("#isDaDuyet_" + maBinhLuan).text('Chưa');
                	$("#isDaDuyet_" + maBinhLuan).css("color", "blue");
                }
                else if(data.result == "0")
                {
                	alert('Không thể gỡ duyệt ! Vui lòng thử lại sau');
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(textStatus);
            }
        });
	}
  }

function calculateMultiApprove() {
	if (confirm("Are you sure you want to approve ?")) {   
	    var arr = $.map($("input:checkbox[name='options[]']:checked"), function(e, i) {
	        return +e.value;
	    });
    
    var message = [];
    for (var i = 0; i < arr.length; i++) {
        var arrayItem = arr[i];
        var obj    = {};
        obj["maBinhLuan"] = arrayItem; 
        message.push(obj);
    }
    
    var url = "multiApprove";
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
            		arrSuccess.push(value.maBinhLuan);
            		$("#isDaDuyet_" + value.maBinhLuan).text('Rồi');
                	$("#isDaDuyet_" + value.maBinhLuan).css("color", "red");
                	$('input[id=chkbox_' + value.maBinhLuan + ']').attr('checked', false);
            	}
            	else if (value.result == "0")
            	{
            		arrFailed.push(value.maBinhLuan);
            	}         		
            }       
            );
            if(arrSuccess.length > 0 && arrFailed.length > 0)
        	{
            	alert('Các bình luận có mã là: ' + arrSuccess.join(',') + ' đã được duyệt');
            	alert('Có lỗi xảy ra ! Các bình luận có mã là: ' + arrFailed.join(',') + ' không duyệt');
        	}
            else if (arrSuccess.length > 0 && arrFailed.length == 0)
            {
            	alert('Các bình luận có mã là: ' + arrSuccess.join(',') + ' đã được duyệt');
        	}
            else if (arrSuccess.length == 0 && arrFailed.length > 0)
            {
            	alert('Có lỗi xảy ra ! Các bình luận có mã là: ' + arrFailed.join(',') + 'không duyệt được');
        	}
            
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus);
        }
    });
	}
}




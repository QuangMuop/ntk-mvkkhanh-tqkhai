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

function confirmDelete(maTaiKhoan) {
    if (confirm("Are you sure you want to delete ?")) {               
  	  	var url = "delete";
  	  	
	  	var message = {
	  		maTaiKhoan: maTaiKhoan
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
                	$("#isDaXoa_" + maTaiKhoan).text('Rồi');
                	$("#isDaXoa_" + maTaiKhoan).css("color", "red");
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

function confirmReshow(maTaiKhoan) {
	if (confirm("Are you sure you want to show again ?")) {
  	  	var url = "reshow";
  	  	
	  	var message = {
	  			maTaiKhoan: maTaiKhoan
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
                	$("#isDaXoa_" + maTaiKhoan).text('Chưa');
                	$("#isDaXoa_" + maTaiKhoan).css("color", "blue");
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
    
    var arr = $("input:checkbox[name='options[]']:checked").map(function(i,n) {
        return $(n).val();
    }).get();
    
    var message = [];
    for (var i = 0; i < arr.length; i++) {
        var arrayItem = arr[i];
        var obj    = {};
        obj["maTaiKhoan"] = arrayItem; 
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
            		arrSuccess.push(value.maTaiKhoan);
            		$("#isDaXoa_" + value.maTaiKhoan).text('Rồi');
                	$("#isDaXoa_" + value.maTaiKhoan).css("color", "red");
                	$('input[id=chkbox_' + value.maTaiKhoan + ']').attr('checked', false);
            	}
            	else if (value.result == "0")
            	{
            		arrFailed.push(value.maTaiKhoan);
            	}         		
            }       
            );
            if(arrSuccess.length > 0 && arrFailed.length > 0)
        	{
            	alert('Các tài khoản có mã là: ' + arrSuccess.join(',') + ' đã được đánh dấu xóa');
            	alert('Các tài khoản có mã là: ' + arrFailed.join(',') + ' không xóa được');
        	}
            else if (arrSuccess.length > 0 && arrFailed.length == 0)
            {
            	alert('Các tài khoản có mã là: ' + arrSuccess.join(',') + ' đã được đánh dấu xóa');
        	}
            else if (arrSuccess.length == 0 && arrFailed.length > 0)
            {
            	alert('Các tài khoản có mã là: ' + arrFailed.join(',') + 'không xóa được');
        	}
            
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus);
        }
    });
	}
}


function confirmBan(maTaiKhoan) {
    if (confirm("Are you sure you want to ban this account ?")) {               
  	  	var url = "ban";
  	  	
	  	var message = {
	  		maTaiKhoan: maTaiKhoan
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
                	$("#isDaKhoa_" + maTaiKhoan).text('Rồi');
                	$("#isDaKhoa_" + maTaiKhoan).css("color", "red");
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

function confirmUnban(maTaiKhoan) {         
	  	var url = "unban";
	  	
  	var message = {
  			maTaiKhoan: maTaiKhoan
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
            	$("#isDaKhoa_" + maTaiKhoan).text('Chưa');
            	$("#isDaKhoa_" + maTaiKhoan).css("color", "blue");
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

function calculateMultiBan() {
	if (confirm("Are you sure you want to ban these accounts ?")) {   
    
    var arr = $("input:checkbox[name='options[]']:checked").map(function(i,n) {
        return $(n).val();
    }).get();
    
    var message = [];
    for (var i = 0; i < arr.length; i++) {
        var arrayItem = arr[i];
        var obj    = {};
        obj["maTaiKhoan"] = arrayItem; 
        message.push(obj);
    }
    
    var url = "multiBan";
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
            		arrSuccess.push(value.maTaiKhoan);
            		$("#isDaKhoa_" + value.maTaiKhoan).text('Rồi');
                	$("#isDaKhoa_" + value.maTaiKhoan).css("color", "red");
                	$('input[id=chkbox_' + value.maTaiKhoan + ']').attr('checked', false);
            	}
            	else if (value.result == "0")
            	{
            		arrFailed.push(value.maTaiKhoan);
            	}         		
            }       
            );
            if(arrSuccess.length > 0 && arrFailed.length > 0)
        	{
            	alert('Các tài khoản có mã là: ' + arrSuccess.join(',') + ' đã được đánh dấu khóa');
            	alert('Các c có mã là: ' + arrFailed.join(',') + ' không khóa được');
        	}
            else if (arrSuccess.length > 0 && arrFailed.length == 0)
            {
            	alert('Các nhà sản xuất có mã là: ' + arrSuccess.join(',') + ' đã được đánh dấu khóa');
        	}
            else if (arrSuccess.length == 0 && arrFailed.length > 0)
            {
            	alert('Các nhà sản xuất có mã là: ' + arrFailed.join(',') + 'không khóa được');
        	}
            
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus);
        }
    });
	}
}

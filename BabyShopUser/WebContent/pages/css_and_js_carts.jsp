<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/objects.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ok-ui-kit.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/carts/carts.css"/>
<!--Thư viện Jquery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>

<script>
function checkout(){
	window.location = "checkout";
}

function update(size) {
	var message = [];
	var thanhTien = 0;
	
	
	for ( var i = 0; i < size; i++) {
		var donGia = $("#product-price-" + i).val();
		var soLuong = $("#product-" + i).val();

		var temp = donGia * soLuong;
		thanhTien = thanhTien + temp;	
		$("#total-" + i).text(addCommas(temp) + " VNĐ");		
	}

	$("#thanhTien").text(addCommas(thanhTien) + " VNĐ");
	$("#thanhTien1").text(addCommas(thanhTien) + " VNĐ");	
	
	for ( var i = 0; i < size; i++) {
		var arrayItem = $("#product-" + i).val();
		var obj = {};
		obj["soLuong"] = arrayItem;
		message.push(obj);
	}
	
	var url = "update-cart";
	$.ajax({
		type : 'POST',
		url : url,
		dataType : "json",
		contentType : "application/json",
		data : JSON.stringify(message),
		success : function(data) {
			alert("Cập nhật thành công!");
		},
		error : function() {
			alert("Cập nhật thất bại!");
		}
	});
}

function remove(maDoChoi) {
	if (confirm("Xóa đồ chơi khỏi giỏ hàng ?")) {
		var message = {
			maDoChoi : maDoChoi
		};
	
		var url = "remove-toy";
		$.ajax({
			type : 'POST',
			url : url,
			dataType : "json",
			contentType : "application/json",
			data : JSON.stringify(message),
			success : function(data) {
				if(data.minicart != 0)
				{
					$("#tr-" + maDoChoi).remove();
					$(".minicart").text(data.minicart + " sản phẩm");
					$("#thanhTien").text(addCommas(data.thanhTien) + " VNĐ");
					$("#thanhTien1").text(addCommas(data.thanhTien) + " VNĐ");	
				}
				else
				{
					window.location.href = "/BabyShopUser/home/index";
				}
				
			},
			error : function() {
				alert("Xóa thất bại!");
			}
		});
	}
}

function addCommas(str) {
    var amount = new String(str);
    amount = amount.split("").reverse();
    
    var output = "";
    for ( var i = 0; i <= amount.length-1; i++ ){
        output = amount[i] + output;
        if ((i+1) % 3 == 0 && (amount.length-1) !== i)output = ',' + output;
    }
    return output;
}
</script>
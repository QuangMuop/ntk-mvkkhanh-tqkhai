<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/objects.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ok-ui-kit.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/checkout/checkout.css"/>
<!--Thư viện Jquery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>

<script>
function show(index) {
	if (index == 1) {
		$("#offline").css("visibility", "hidden");
		$("#online").css("visibility", "visible");
	}

	if (index == 2) {
		$("#offline").css("visibility", "visible");
		$("#online").css("visibility", "hidden");
	}
}

function checkoutPaypal() {
	var message = {
		hoTen : $("#hoTen").val(),
		diaChi: $("#diaChi").val()
	};
		
	var url = "checkout-paypal";
	
	$.ajax({
		type : 'POST',
		url : url,
		dataType : "json",
		contentType : "application/json",
		data : JSON.stringify(message),
		success : function(data) {
			//alert("Thiết lập thành công");
		},
		error : function() {
			//alert("Thiết lập thất bại!");
		}
	});
}
</script>
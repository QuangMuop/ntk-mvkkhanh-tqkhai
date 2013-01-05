<%-- 
    Document   : css_and_js_product_details
    Created on : Oct 29, 2012, 10:45:14 AM
    Author     : TrongKhoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/objects.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/product-details/product-details.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ok-ui-kit.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/shared-css/aviaslider/aviaslider.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/shared-css/prettyPhoto/prettyPhoto.css"/>
<!--JQUERY UI-->

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aviaslider/jquery.aviaSlider.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aviaslider/jquery.callAviaSlider.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/prettyPhoto/jquery.prettyPhoto.js"></script>

<script>
    $(function() {
        $( "#tabs" ).tabs();
    });

    function sendComment(maDoChoi, myAvatar, myName){
    	var strComment = $("#comment-content").val();

    	var a_p = "";
    	var curr_time = new Date();
    	var curr_hour = curr_time.getHours();
    	if (curr_hour < 12)
    	   {
    	   a_p = "AM";
    	   }
    	else
    	   {
    	   a_p = "PM";
    	   }
    	if (curr_hour == 0)
    	   {
    	   curr_hour = 12;
    	   }
    	if (curr_hour > 12)
    	   {
    	   curr_hour = curr_hour - 12;
    	   }

    	var curr_min = curr_time.getMinutes();

    	curr_min = curr_min + "";

    	if (curr_min.length == 1)
    	   {
    	   curr_min = "0" + curr_min;
    	   }
 	   
    	var curr_date = curr_time.getDate();

    	var curr_month = curr_time.getMonth();
    	curr_month++;
    	var curr_year = curr_time.getFullYear();
    	var myDate = curr_hour + ':' + curr_min + ' ' + curr_date + "/" + curr_month + "/" + curr_year;
    	
    	var message = {
    		maDoChoi: maDoChoi,	
    		noiDung: strComment,
    		thoiGian: curr_time
    	};
    	
    	var url = "/BabyShopUser/account/add-comment";
    	$.ajax({
    		type : 'POST',
    		url : url,
    		dataType : "json",
    		contentType : "application/json",
    		data : JSON.stringify(message),
    		success : function(data) {
    			if(data.result == "1")
                {
    				$("#commentlist").append('<li class="comment">'
    	    				+ '<div class="avatar32x32"><img src="' + myAvatar + '" alt="Avatar"/></div>' 
    	    				+ '<div class="comment-container">'
                            + '<p> Bởi <span class="user">'+ myName + '</span> vào <span>' + myDate +'</span> : </p>'
							+ '<div class="comment-text">'
							+ '<p>' + strComment + '</p>'
							+ '</div>'
                        	+ '</div>'
                        	+ ' <div class="clear"></div>'
    	    				+ '</li>'
    	    		);
    				$("#nComments").text('Nhận xét (' + data.nComments + ')');
                } 			
    		},
    		error : function() {
    			alert("Nhận xét thất bại!");
    		}
    	});
    }
</script>

<!--END JQUERY UI-->
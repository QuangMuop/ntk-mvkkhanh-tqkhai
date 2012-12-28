$("#hinh1").css('opacity','0');
$("#hinh2").css('opacity','0');
$("#hinh3").css('opacity','0');
$("#hinh4").css('opacity','0');


$("#chon_hinh_1").click(function(){
    if($('.request-edit').is(':checked'))
    {
        $("#hinh1").trigger('click');
        return false;
    }
    else
    {
        return false;
    }
});
$("#chon_hinh_2").click(function(){
    if($('.request-edit').is(':checked'))
    {
        $("#hinh2").trigger('click');
        return false;
    }
    else
    {
        return false;
    }
});
$("#chon_hinh_3").click(function(){
    if($('.request-edit').is(':checked'))
    {
        $("#hinh3").trigger('click');
        return false;
    }
    else
    {
        return false;
    }
});
$("#chon_hinh_4").click(function(){
    if($('.request-edit').is(':checked'))
    {
        $("#hinh4").trigger('click');
        return false;
    }
    else
    {
        return false;
    }
});

function readURL(input, imgFrame) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function (e) {
			$("#" + imgFrame)
				.attr('src', e.target.result)
				.width(119)
				.height(82);
		};

		reader.readAsDataURL(input.files[0]);
	}
}

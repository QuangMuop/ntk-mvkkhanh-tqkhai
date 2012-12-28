

function callUploader(uploader)
{
	if($('.request-edit').is(':checked'))
    {
		$("#" + uploader).trigger('click');
        return false;
    }
    else
    {
        return false;
    }
}

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

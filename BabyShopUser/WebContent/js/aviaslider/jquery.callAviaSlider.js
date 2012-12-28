$(document).ready(function(){		
	 //Chạy LightBox
    jQuery('a[href$=jpg], a[href$=png], a[href$=gif], a[href$=jpeg]').prettyPhoto({ theme: "light_rounded" });


    //Chạy Slideshow
    $('#frontpage-slider').aviaSlider({ blockSize: { height: 80, width: 80 },
        transition: 'slide',
        display: 'all',
        transitionOrder: ['diagonaltop', 'diagonalbottom', 'topleft', 'bottomright', 'random']
    });	
});









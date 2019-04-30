/*
Assan Education template
Author - design_mylife
Project Version - v1.0
 */


(function ($) {

    "use strict";
    /**Preloader*/
    $(window).preloader({
        delay: 500
    });
    //auto close navbar-collapse on click a
    $('.nav-item>[data-scroll]').on('click', function () {
        $('.navbar-toggler:visible').click();
    });
    /*sticky navbar*/
    $("#navbar-sticky").sticky({ topSpacing: 0 });
    /**transparent header fixed-top */
    /*header shrink*/
    $(window).scroll(function () {
        var scroll = $(window).scrollTop();

        if (scroll >= 100) {

            $("#experience-class").removeClass("experience-class-none");


        } else {

            $("#experience-class").addClass("experience-class-none");
        }
    });
    /*Tetimonials carousel*/
    $('.carousel-testimonials').owlCarousel({
        loop: false,
        autoPlay: false,
        margin: 15,
        nav: true,
        dots: true,
        responsive: {
            0: {
                items: 1
            },
            600: {
                items: 2
            },
            1000: {
                items: 3
            }
        }
    });
    /*video modal*/
    $('.play-video').magnificPopup({
        type: 'iframe',
        midClick: true // Allow opening popup on middle mouse click. Always set it to true if you don't provide alternative source in href.
    });
    /*smooth scroll*/
    smoothScroll.init({
        selector: '[data-scroll]', // Selector for links (must be a class, ID, data attribute, or element tag)
        speed: 800, // Integer. How fast to complete the scroll in milliseconds
        easing: 'easeInOutCubic', // Easing pattern to use
        offset: 70, // Integer. How far to offset the scrolling anchor location in pixels
        callback: function (anchor, toggle) { } // Function to run after scrolling
    });
    /**sticky sidebar */
    jQuery('.sticky-content, .sticky-sidebar').theiaStickySidebar({
        // Settings
        additionalMarginTop: 80
    });
    /*jquery ui search */
    $( ".select-ui" ).selectmenu();
    /* 课程列表展示 */


    /* 教师列表展示 */
    $.ajax({
         url:"portal/teacherGetList"
        ,method:"POST"
        ,async:false
        ,success:function (res) {
            if(res){
                 for(var i=0;i<res.length;i++){
                     $('#teacherPortalShow').append(
                         '<div class="swiper-slide" >'
                         +'<img src="'+ res[i].photoUrl+'" alt=""  style="" width="200" height="200"/>'
                         +'<div  style="margin-top: 10px;text-align: center;color: #333;font-size: 16px">'
                         +'<h5>'+res[i].name+'</h5>'
                         +' <p>'+res[i].introduction+'</p>'
                         + '</div>'
                         + '</div>'
                     );
                 }
            }else {
                $('#teacherPortalShow').html("");
            }
        }
        ,error:function (res) {
            
        }
    });
})(jQuery);
$(function () {
    "use strict";
    if ($('.scrollReveal').length && !$('html.ie9').length) {
        $('.scrollReveal').parent().css('overflow', 'hidden');
        window.sr = ScrollReveal({
            reset: true,
            distance: '25px',
            mobile: true,
            duration: 850,
            scale: 1,
            viewFactor: 0.3,
            easing: 'ease-in-out'
        });
        sr.reveal('.sr-top', { origin: 'top' });
        sr.reveal('.sr-bottom', { origin: 'bottom' });
        sr.reveal('.sr-left', { origin: 'left' });
        sr.reveal('.sr-long-left', { origin: 'left', distance: '70px', duration: 1000 });
        sr.reveal('.sr-right', { origin: 'right' });
        sr.reveal('.sr-scaleUp', { scale: '0.8' });
        sr.reveal('.sr-scaleDown', { scale: '1.15' });

        sr.reveal('.sr-delay-1', { delay: 200 });
        sr.reveal('.sr-delay-2', { delay: 400 });
        sr.reveal('.sr-delay-3', { delay: 600 });
        sr.reveal('.sr-delay-4', { delay: 800 });
        sr.reveal('.sr-delay-5', { delay: 1000 });
        sr.reveal('.sr-delay-6', { delay: 1200 });
        sr.reveal('.sr-delay-7', { delay: 1400 });
        sr.reveal('.sr-delay-8', { delay: 1600 });

        sr.reveal('.sr-ease-in-out-quad', { easing: 'cubic-bezier(0.455,  0.030, 0.515, 0.955)' });
        sr.reveal('.sr-ease-in-out-cubic', { easing: 'cubic-bezier(0.645,  0.045, 0.355, 1.000)' });
        sr.reveal('.sr-ease-in-out-quart', { easing: 'cubic-bezier(0.770,  0.000, 0.175, 1.000)' });
        sr.reveal('.sr-ease-in-out-quint', { easing: 'cubic-bezier(0.860,  0.000, 0.070, 1.000)' });
        sr.reveal('.sr-ease-in-out-sine', { easing: 'cubic-bezier(0.445,  0.050, 0.550, 0.950)' });
        sr.reveal('.sr-ease-in-out-expo', { easing: 'cubic-bezier(1.000,  0.000, 0.000, 1.000)' });
        sr.reveal('.sr-ease-in-out-circ', { easing: 'cubic-bezier(0.785,  0.135, 0.150, 0.860)' });
        sr.reveal('.sr-ease-in-out-back', { easing: 'cubic-bezier(0.680, -0.550, 0.265, 1.550)' });
    }
});
/* Initialize Swiper  */
var swiper = new Swiper('.swiper-container', {
    initialSlide:3,
    effect: 'coverflow',
    grabCursor: true,
    centeredSlides: true,
    slidesPerView: 'auto',
    zoom:true,
    coverflowEffect: {
        rotate: 50,
        stretch: 0,
        depth: 100,
        modifier: 1,
        slideShadows : true,
    },
    pagination: {
        el: '.swiper-pagination',
    },
});


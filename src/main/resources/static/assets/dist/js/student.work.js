/*
Assan Education template
Author - design_mylife
Project Version - v1.0
 */
var studentWorkList=[];
var JQ$;
function  studentWorkListDoM() {

    //学生作品列表
    layui.use(['laypage', 'layer'], function(){
        var laypage = layui.laypage
            ,layer = layui.layer;
        //测试数据
        var data =studentWorkList;
        $('#studentWorkList').html("");
        //调用分页
        laypage.render({
            elem: 'pagination'
            ,count: data.length
            ,limit:4
            ,jump: function(obj){
                $('#studentWorkList').html("");
                /*  //模拟渲染
                  document.getElementById('pagination').innerHTML = function(){
                      var arr = []
                          ,thisData = data.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);
                      layui.each(thisData, function(index, item){
                          arr.push('<li>'+ item +'</li>');
                      });
                      return arr.join('');
                  }();*/
                var arr = []
                    ,thisData = studentWorkList.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);
                if(thisData){
                    for(var i=0;i<thisData.length;i++){
                        $('#studentWorkList').append( '<div class="col-lg-6 mb">'
                           +' <div class="img-card-alt mb-30">'
                            +'<div class="img-desc">'
                            +' <a href="course-single.html" class="h5">'
                            +' 图形作品'
                            +' </a>'
                            +' <p class="pt-2">'
                            +'  该作品获得本月一等奖'
                            +'</p>
                            +'<ul class="list-inline mb-0">'

                            +'<li class="list-inline-item mr-3">'
                            +'<i class="fa fa-user mr-2"></i>'
                            +' <a href="#!">清风若雨</a>'
                            +' </li>'
                            +' </ul>'
                            +'</div>'
                            +'</div>'
                            +'</div>'
                            );
                    }
                }else {
                    $('#studentWorkList').html("");
                }
            }
        });

    });
}
function selectMaterial(typeId){
    JQ$.ajax({
        url:"portal/materialGetList"
        ,method:"POST"
        ,async:false
        ,data:{
            typeId:typeId
        }
        ,success:function (res) {

            if(res){
                //给学生作品赋值
                studentWorkList=res;
                studentWorkListDoM();
                //window.location.reload();
            }
        }
        ,error:function (res) {

        }
    });
}
(function ($) {
    JQ$=$;
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
    /*******************Material START*****************************/

    //学生作品类型

    $.ajax({
        url:"portal/materialTypeGetList"
        ,method:"POST"
        ,async:false
        ,success:function (res) {
            if(res){
                for(var i=0;i<res.length;i++){
                    $('#materialType').append(
                    '<a onclick="selectMaterial('+res[i].id+');" class="btn btn-light">'+res[i].name+'</a>'
                    )
                }
            }else {
                $('#materialType').html("");
            }
        }
        ,error:function (res) {

        }
    });

    //热门学生作品推送列表

    $.ajax({
        url:"portal/materialGetList"
        ,method:"POST"
        ,async:false
        ,data:{
            typeId:""
        }
        ,success:function (res) {

            if(res){
                //给学生作品赋值
                studentWorkList=res;
                for(var i=0;i<res.length;i++){

                    if(i==6) return;

                    if(res[i].downloadNumber>20){
                        $('#materialPush').append(
                            '<li>'
                            +'<a target="_blank"  download="'+res[i].originName+'" href="'+res[i].fileUrl+'">' +res[i].originName +'  <span class="badge rounded bg-secondary text-white d-inline-block ml-2"> <i class="fa fa-arrow-up" ></i></span>'
                            +' </a>'
                            +' </li>'
                        );
                    }else{
                        $('#materialPush').append(
                            '<li>'
                            +'<a target="_blank" href="'+res[i].fileUrl+'" download="'+res[i].originName+'">'+res[i].originName +' </a>'
                            +' </li>');
                    }

                }
            }else {
                $('#materialType').html("");
            }
        }
        ,error:function (res) {

        }
    });
    //学生作品列表
    studentWorkListDoM();

    /*******************Material END*****************************/

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

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
    /*******************Code START*****************************/
   var currentUser = window.sessionStorage.getItem("currentUser");
   if(currentUser){
       currentUser = JSON.parse(currentUser);
       $("#login").css("display","none");
       $("#logout").css("display","block");
       $("#CurrentUserName").text(currentUser.data.name);
   }
    layui.use(['laypage', 'layer','element'], function(){
        var laypage = layui.laypage
            ,layer = layui.layer,
            element=layui.element;

        $("#loginBtn").click(function () {

            layer.open({
                type: 1 //Page层类型
                ,area: ['600px', '350px']
                ,title: false //不显示标题
                ,shade: 0.6 //遮罩透明度
                ,fixed: true //位置固定
                ,maxmin: false //开启最大化最小化按钮
                ,anim: 5 //0-6的动画形式，-1不开启
                ,content: '<div class="col-md-12 mr-auto margin-b-30 text-center" style="margin-top: 50px"> <h2>登录</h2></div>'+
                ' <div class="col-md-12 mr-auto margin-b-30">' +
                '                     <div class="p-4 rounded ">' +
                '                         <div  style="margin:20px 100px">' +
                '                             <div class="form-group">' +
                '                                 <input id="loginId" type="text" class="form-control" placeholder="手机号">' +
                '                             </div>' +
                '                             <div class="form-group">' +
                '                                 <input id="password" type="password" class="form-control" placeholder="密码">' +
                '                             </div>' +
                '                             <div class="form-group text-center">' +
                '                                 <button  id="submitBtn" type="button" class="btn btn-lg btn-danger text-white" >登录</button>' +
                '                             </div>' +
                '                         </div>' +
                '                     </div>' +
                '                     <!-- end .smart-wrap section -->' +
                '                 </div>'
                ,success: function(layero, index){
                    $("#submitBtn").click(function () {
                        var loginId = $("#loginId").val();
                        var password = $("#password").val();
                         if(loginId==null||loginId==""){
                             layer.msg('请输入手机号！', {time: 2000, icon:0});
                             return;
                         }
                        if(password==null||password==""){
                            layer.msg('请输入密码！', {time: 2000, icon:0});
                            return;
                        }
                        if(/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/.test(loginId)){

                             $.ajax({
                                url:"../../../portal/studentLogin"
                                ,method:"POST"
                                ,async:false
                                ,data:{
                                    loginId:loginId,
                                    password:password
                                }
                                ,success:function (res) {
                                     if(res){
                                         if(res.status==200||res.status=="200"){
                                             $("#login").css("display","none");
                                             $("#logout").css("display","block");

                                             window.sessionStorage.setItem("currentUser",JSON.stringify(res));
                                             $("#CurrentUserName").text(res.data.name);
                                             layer.close( index);
                                         }else{
                                             layer.msg(res.msg, {time: 2000, icon:5});
                                         }

                                     }else{
                                         layer.msg('登录失败！', {time: 2000, icon:5});

                                     }
                                }
                                ,error:function (res) {
                                     layer.msg('登录失败！', {time: 2000, icon:5});
                                }
                            });
                        }else{
                            layer.msg('请输入正确手机号！', {time: 2000, icon:0});
                            return;
                        }
                    });
                }

            });
        });

        $("#logoutBtn").click(function () {
            var index;
            index=layer.confirm('您确定要退出吗？', {
                btn: ['确定','取消'] //按钮
                ,title: false //不显示标题
            }, function(){

                 $.ajax({
                                url:"../../../portal/studentLogout"
                                ,method:"POST"
                                ,async:false
                                ,success:function (res) {
                                    if(res){
                                        if(res.status==200||res.status=="200"){
                                            window.sessionStorage.clear();
                                            $("#login").css("display","block");
                                            $("#logout").css("display","none");
                                            layer.close( index);
                                            window.location.href="code-square.html";

                                        }else{
                                            layer.msg('退出失败！', {time: 2000, icon:5});
                                        }

                                    }else{
                                        layer.msg('退出失败！', {time: 2000, icon:5});

                                    }
                                }
                                ,error:function (res) {
                                              layer.msg('退出失败！', {time: 2000, icon:5});
                                }
                            });

            }, function(){

            });
        })

        $("#work").click(function () {

            //学生作品列表
            $.ajax({
                url:"../../../portal/studentWorkGetList"
                ,method:"POST"
                ,async:false
                ,success:function (res) {

                    if(res){
                        //给学生作品赋值
                        var studentWorkList=res;
                        //学生作品列表
                        //调用分页
                        laypage.render({
                            elem: 'pagination'
                            ,count: studentWorkList.length
                            ,limit:4
                            ,jump: function(obj){
                                $('#workContent').html("");
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
                                        $('#workContent').append( '<div class="col-lg-6 mb">'
                                            +' <div class="img-card-alt mb-30">'
                                            +'<a class="img-thumb" onclick="studentWork('+thisData[i].id +');">'
                                            +'<img src="../../../assets/images/scratchGif.gif" alt="" class="img-fluid">'
                                            +' <span class="thumb-overlay">'
                                            +' <i class="icon-link"></i>'
                                            +' </span>'
                                            +' </a>'
                                            +'<div class="img-desc">'
                                            +' <a href="student-work-single.html" class="h5">'
                                            +thisData[i].workName
                                            +' </a>'
                                            +' <p class="pt-2">'
                                            +thisData[i].description
                                            +'</p>'
                                            +'<ul class="list-inline mb-0">'

                                            +'<li class="list-inline-item mr-3">'
                                            +'<i class="fa fa-user mr-2"></i>'
                                            +' <a href="#!">'+thisData[i].studentName +'</a>'
                                            +' </li>'
                                            +' </ul>'
                                            +'</div>'
                                            +'</div>'
                                            +'</div>'
                                        );
                                    }
                                }else {
                                    $('#workContent').html("");
                                }
                            }
                        });

                    }
                }
                ,error:function (res) {
                }
            });
        });
    });



    /*******************Code END*****************************/

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

window.onbeforeunload =function() {
    // code...
    return null;
}
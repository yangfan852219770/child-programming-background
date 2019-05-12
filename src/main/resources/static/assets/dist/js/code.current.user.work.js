/*
Assan Education template
Author - design_mylife
Project Version - v1.0
 */
 var  _$,_layer,_laypage;
function currentWorkList(workName,word) {
    _$.ajax({
        url:"../../../portal/currentStudentWorkGetList"
        ,method:"POST"
        ,async:false
        ,data:{
            workName:workName
        }
        ,success:function (res) {
            if(res){
                //学生作品列表
                //调用分页
                _laypage.render({
                    elem: 'pagination'
                    ,count: res.length
                    ,limit:4
                    ,jump: function(obj){
                        _$('#currentWorkListContent').html("");
                        var arr = []
                            ,thisData = res.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);
                        if(thisData){
                            for(var i=0;i<thisData.length;i++){
                                _$('#currentWorkListContent').append('                    <div class="mb-30 event-list " style="background-color: #F3EFE6!important;">\n' +
                                    '                        <div class="row align-items-center d-flex flex">\n' +
                                    '\n' +
                                    '                            <div class="col-md-3 col-sm-12 py-3 text-center">\n' +
                                    '                                <a href="#!">\n' +
                                    '                                    <img src="'+thisData[i].coverUrl+'" alt="" class="img-fluid">\n' +
                                    '                                </a>\n' +
                                    '                            </div>\n' +
                                    '                            <div class="col-md-6 col-sm-12 text-center text-sm-left py-3">\n' +
                                    '                                <h3  class="h3"> '+thisData[i].workName+'</h3>\n' +
                                    '                                <div class="pt-3">\n' +
                                    '                                    <a  href="javascript:;"  onclick="currentStudentWorkDel('+thisData[i].id+')" > <span class="d-inline-block mr-3"><i class="fa fa-trash mr-1" ></i> 删除作品</span></a>\n' +
                                    '\n' +
                                    '                                </div>\n' +
                                    '                            </div>\n' +
                                    '                            <div class="col-md-3 col-sm-12 py-3 text-center">\n' +
                                    '                                <div class="btn-group">\n' +
                                    '                                    <a id="currentStudentWorkPlay" onclick="currentStudentWorkPlay('+thisData[i].id+')"  class="btn btn-outline-primary btn-sm">\n' +
                                    '                                        <i class="fa fa-play mr-1" ></i>  痛快玩\n' +
                                    '                                    </a>\n' +
                                    '                                    <a id="currentStudentWorkEdit" onclick="currentStudentWorkEdit('+thisData[i].id+')" class="btn btn-outline-primary btn-sm">\n' +
                                    '                                        <i class="fa fa-edit mr-1" ></i>  微编辑\n' +
                                    '                                    </a>\n' +
                                    '                                </div>\n' +
                                    '\n' +
                                    '                            </div>\n' +
                                    '                        </div>\n' +
                                    '                    </div><!--/.event list-->\n');
                            }
                        }else {
                            _$('#currentWorkListContent').html('<div class="mb-30 event-list " style="background-color: #F3EFE6!important;">\n' +
                                '                        <div class="row align-items-center">\n' +
                                '\n' +
                                '                            <div class="col-md-12  text-center  ">\n' +
                                '                                <a href="code-square.html" > '+word+'</a>\n' +
                                '                            </div>\n' +
                                '                        </div>\n' +
                                '                    </div><!--/.event list-->\n');
                        }
                    }
                });

            }else{
                _$('#currentWorkListContent').html('<div class="mb-30 event-list " style="background-color: #F3EFE6!important;">\n' +
                    '                        <div class="row align-items-center">\n' +
                    '\n' +
                    '                            <div class="col-md-12  text-center  ">\n' +
                    '                                <a href="code-square.html" > '+word+'</a>\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                    </div><!--/.event list-->\n');
            }
        }
        ,error:function (res) {
        }
    });
}
function currentStudentWorkPlay(id) {
    window.sessionStorage.setItem("currentUserWorkPlayId",id);
    window.location.href="current-student-work-play.html";
}
function currentStudentWorkDel(id) {
    var index;
    index=_layer.confirm('您确定要删除该作品吗？', {
        btn: ['确定','取消'] //按钮
        ,title: false //不显示标题
    }, function(){

        _$.ajax({
            url:"../../../portal/currentStudentWorkDel"
            ,method:"POST"
            ,async:false
            ,data:{
                ids:id
            }
            ,success:function (res) {
                if(res){
                    if(res.status==200||res.status=="200"){
                        currentWorkList(null,"你没有作品点我创作吧！");
                        _layer.msg(res.msg, {time: 1000, icon:6});
                        _layer.close(index);
                    }else{
                        _layer.msg(res.msg, {time: 1000, icon:5});
                    }

                }else{
                    _layer.msg('删除失败！', {time: 1000, icon:5});

                }
            }
            ,error:function (res) {
                _layer.msg('删除失败！', {time: 1000, icon:5});
            }
        });

    }, function(){

    });

}
function currentStudentWorkEdit(id) {
    window.sessionStorage.setItem("currentUserWorkId",id);
    window.location.href="current-student-work-detail.html";
}
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
    layui.use(['laypage', 'layer','element','upload'], function(){
        var laypage = layui.laypage
            ,layer = layui.layer
            ,upload = layui.upload
            ,element=layui.element;
        _layer=layer;
        _$=layui.$;
        _laypage=laypage;
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
        });

        $("#queryWorkNameSubmit").click(function () {

            currentWorkList($("#queryWorkName").val(),"未找到该作品！");
        })




        currentWorkList(null,"你没有作品点我创作吧！");


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

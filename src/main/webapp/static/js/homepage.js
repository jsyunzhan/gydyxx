$(function () {
    // 新闻中心
    $.ajax({
        url:path + "/homepage/news/list",
        type:"GET",dataType:"json",
        success:function (event) {
            var _html = "";
            for (var i=0;i<event.length;i++){
                if(i<8){
                    _html += '<div class="news_list" name="'+event[i].id+'"><span><img src="../static/images/icon_round.png"></span>'+event[i].newsTitle+'</div>';
                }
            }
            $(".main_content_right").append(_html);
            // 新闻中心跳转详情
            $(".notification").click(function () {
                var url = path + '/homepage/news/details/'+$(this).attr("name");
                window.location.href = url;
            })
        }
    });
    // 新闻中心主题数据
    $.ajax({
        url:path + "/homepage/news/main/list",
        type:"GET",dataType:"json",
        success:function (event) {
            var _html = "";
            var font = event[0].newsDetails;
            var dd=font.replace(/<\/?.+?>/g,"");
            var dds=dd.replace(/ /g,"");
            var dds=dds.slice(0,30);
            _html += '<div class="main_title" name="'+event[0].id+'">'+event[0].newsTitle+'</div>';
            _html += '<div class="secondary_title" name="'+event[0].id+'">'+dds+'<a href="javascript:;">[更多]</a></div>'
            $(".content_news").append(_html);
            // 新闻中心跳转详情
            $(".main_title,.secondary_title").click(function(){
                var url = path + '/homepage/news/details/'+$(this).attr("name");
                window.location.href = url;
            })
        }
    });

    // 新闻中心轮播图
    $.ajax({
        url:path + "/homepage/news/change/list",
        type:"GET",dataType:"json",
        success:function (event) {
            for(var i=0;i<event.length;i++) {
                var data = {picturePath: event[i].picturePath};
                $.ajax({
                    url:path + "/home/picture/show",
                    type:"POST",contentType: "application/json",data:JSON.stringify(data),async:false,
                    success:function (r) {
                        var _html = "";
                        _html += '<div class="swiper-slide" name="'+event[0].id+'"><img src="data:image/gif;base64,'+r+'"><div class="newsPic_font">'+event[i].newsTitle+'</div><div class="newsPic_title"></div></div>';
                        $(".news_banner .swiper-wrapper").append(_html);
                    }
                })
            }
            // 新闻轮播图
            var news_banner = new Swiper(".news_banner",{
                autoplay : 3000,
                speed: 1000,
                loop:true,
                pagination: '.pagination01',
                paginationClickable: true,
                autoplayDisableOnInteraction : false
            });
            // 新闻中心跳转详情
            $(".news_banner .swiper-slide .newsPic_font").click(function(){
                var url = path + '/homepage/news/details/'+$(this).parent(".swiper-slide").attr("name");
                window.location.href = url;
            })
        }
    });

    // 通知公告
    $.ajax({
        url:path + "/homepage/notice/list",
        type:"GET",dataType:"json",
        success:function (event) {
            var _html = "";
            for (var i=0;i<event.length;i++){
                if(i<8) {
                    _html += '<div class="notification" name="'+event[i].id+'">' + event[i].noticeTitle + '</div>';
                }
            }
            $(".notice_scroll").append(_html);
            // 通知公告跳转详情
            $(".notification").click(function () {
                var url = path + '/homepage/notice/details/'+$(this).attr("name");
                window.location.href = url;
            })
        }
    });

    // 作品展示
    $.ajax({
        url:path + "/homepage/works/list",
        type:"GET",dataType:"json",
        success:function (event) {
            var _html = "";
            for (var i=0;i<event.length;i++){
                if(i<6) {
                    _html += '<div class="ex_list clearfix" name="'+event[i].id+'"><p>' + event[i].worksTitle + '</p><span>' + timestampToTime02(event[i].createDate) + '</span></div>';
                }
            }
            $(".ex_child:nth-child(1) .content_ex").append(_html);
            // 作品展示跳转详情
            $(".ex_child:nth-child(1) .ex_list").click(function () {
                var url = path + '/homepage/works/details/'+$(this).attr("name");
                window.location.href = url;
            })
        }
    });

    // 名师风采
    $.ajax({
        url:path + "/homepage/teacher/list",
        type:"GET",dataType:"json",
        success:function (event) {
            var _html = "";
            for (var i=0;i<event.length;i++){
                if(i<6) {
                    _html += '<div class="ex_list clearfix" name="'+event[i].id+'"><p>' + event[i].teacherTitle+ '</p><span>' + timestampToTime02(event[i].createDate) + '</span></div>';
                }
            }
            $(".ex_child:nth-child(2) .content_ex").append(_html);
            // 名师风采跳转详情
            $(".ex_child:nth-child(2) .ex_list").click(function () {
                var url = path + '/homepage/teacher/details/'+$(this).attr("name");
                window.location.href = url;
            })
        }
    });

    // 学子风采
    $.ajax({
        url:path + "/homepage/student/list",
        type:"GET",dataType:"json",
        success:function (event) {
            var _html = "";
            for (var i=0;i<event.length;i++){
                if(i<6) {
                    _html += '<div class="ex_list clearfix" name="'+event[i].id+'"><p>' + event[i].studentTitle + '</p><span>' + timestampToTime02(event[i].createDate) + '</span></div>';
                }
            }
            $(".ex_child:nth-child(3) .content_ex").append(_html);
            // 学子风采跳转详情
            $(".ex_child:nth-child(3) .ex_list").click(function () {
                var url = path + '/homepage/student/details/'+$(this).attr("name");
                window.location.href = url;
            })
        }
    });

    // 课题研究
    $.ajax({
        url:path + "/homepage/subject/list",
        type:"GET",dataType:"json",
        success:function (event) {
            var _html = "";
            for (var i=0;i<event.length;i++){
                if(i<6) {
                    _html += '<div class="ex_list clearfix" name="'+event[i].id+'"><p>' + event[i].subjectTitle + '</p><span>' + timestampToTime02(event[i].createDate) + '</span></div>';
                }
            }
            $(".ex_child:nth-child(4) .content_ex").append(_html);
            // 课题研究跳转详情
            $(".ex_child:nth-child(4) .ex_list").click(function () {
                var url = path + '/homepage/subject/details/'+$(this).attr("name");
                window.location.href = url;
            })
        }
    });

    // 校园风光
    $.ajax({
        url:path + "/homepage/school/list",
        type:"GET",dataType:"json",async:false,
        success:function (event) {
            for(var i=0;i<event.length;i++){
                var data = {picturePath:event[i].picturePath};
                $.ajax({
                    url:path + "/home/picture/show",
                    type:"POST",contentType: "application/json",data:JSON.stringify(data),async:false,
                    success:function (r) {
                        var _html = "";
                        _html += '<div class="swiper-slide clearfix"><div class="mien_pic_child"><img src="data:image/gif;base64,'+r+'"><img src="../static/images/frame.png"></div></div>';
                        $(".mien_pic .swiper-wrapper").append(_html);
                    }
                })
            }
            if(event.length>4){
                // 学校风采图片切换
                var mien_pic = new Swiper(".mien_pic",{
                    autoplay : 5000,
                    speed: 1000,
                    loop:true,
                    pagination: '.pagination02',
                    paginationClickable: true,
                    slidesPerView: 4,
                    autoplayDisableOnInteraction : false
                });
            }
        }
    });

    // banner轮播图
    $.ajax({
        url:path + "/homepage/banner/list",
        type:"GET",dataType:"json",
        success:function(event){
            for(var i=0;i<event.length;i++){
                var data = {picturePath: event[i].picturePath};
                $.ajax({
                    url:path + "/home/picture/show",
                    type:"POST",contentType: "application/json",data:JSON.stringify(data),async:false,
                    success:function (r) {
                        var _html = "";
                        _html += '<div class="swiper-slide" name="'+event[0].id+'"><img src="data:image/gif;base64,'+r+'"></div>';
                        $(".banner_wrapper").append(_html);
                    }
                })
            }
            // 调用swiper插件
            var banner = new Swiper(".banner",{
                autoplay : 3000,
                speed: 1000,
                loop:true,
                pagination: '.pagination',
                paginationClickable: true,
                autoplayDisableOnInteraction : false
            });
            $(".arrow-left").on("click", function(e){
                e.preventDefault();
                banner.swipePrev();
            });
            $(".arrow-right").on("click", function(e){
                e.preventDefault();
                banner.swipeNext();
            });
            // 点击飘窗跳转
            $(".banner_wrapper .swiper-slide").click(function () {
                var url = path + '/homepage/banner/details/'+$(this).attr("name");
                window.location.href = url;
            })
        }
    });

    // 飘窗
    $.ajax({
        url:path + "/homepage/window/list",
        type:"GET",dataType:"json",
        success:function(event){
            var _html = "";
            _html += '<div class="floatImage">';
            for(var i=0;i<event.length;i++){
                var data = {picturePath: event[i].picturePath};
                $.ajax({
                    url:path + "/home/picture/show",
                    type:"POST",contentType: "application/json",data:JSON.stringify(data),async:false,
                    success:function (r) {
                        _html += '<a href="javascript:;" name="'+event[i].id+'" ><img src="data:image/gif;base64,'+r+'"></a>';
                    }
                })
            }
            _html += '<div class="imgClose">x&nbsp;关闭</div>';
            $("body").append(_html);
            // 调用飘窗
            var floatImage = new imgFloat(".floatImage",{
                "time":30,
                "speedx":0.5,
                "speedy":1
            });
            floatImage.resize();
            floatImage.move();
            $(".floatImage .imgClose").click(function(){
                $(this).parent(".floatImage").remove();
            })
            // 点击飘窗跳转
            $(".floatImage a").click(function () {
                var url = path + '/homepage/window/details/'+$(this).attr("name");
                window.location.href = url;
            })
        }
    })

    /*功能模块快捷方式*/
    $(".infor .icon").mouseover(function(){
        $(".infor .icon .icon_font").css("color","#565656");
        $(this).find(".icon_font").css("color","#ce6e0a");
    });
    $(".infor .icon").mouseout(function(){
        $(".infor .icon .icon_font").css("color","#565656");
    });

    // 平台链接
    $(".platform").mouseover(function(){
        $(".linking_list").show();
        $(".linking_list p").mouseover(function () {
            $(this).addClass("linkChoose");
        })
        $(".linking_list p").mouseout(function () {
            $(this).removeClass("linkChoose");
        })
    });
    $(".platform").mouseout(function(){
        $(".linking_list").hide();
    });

})
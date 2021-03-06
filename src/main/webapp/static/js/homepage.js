$(function () {
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
                        _html += '<li><a href="javascript:;"><img src="data:image/gif;base64,'+r+'" /></a></li>';
                        $(".slideBox01 .bd ul").append(_html);
                        $(".slideBox01 .hd ul").append("<li></li>");
                    }
                })
            }
            jQuery(".slideBox01").slide({mainCell:".bd ul",effect:"left",autoPlay:true,easing:"easeOutCirc",interTime:8000});
        }
    });

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
            $(".news_list").click(function () {
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
            _html += '<div class="main_title" name="'+event[0].id+'">'+event[0].newsTitle+'</div><br/>';
            _html += '<div class="secondary_title" name="'+event[0].id+'">——&nbsp;&nbsp;'+dds+' . . . <a href="javascript:;">[更多]</a></div>'
            $(".content_news").append(_html);
            // 新闻中心跳转详情
            $(".main_title,.secondary_title a").click(function(){
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
                        _html += '<li name="'+event[i].id+'"><a href="javascript:;"><img src="data:image/gif;base64,'+r[0]+'" /></a><p>'+event[i].newsTitle+'</p></li>';
                        $(".slideBox02 .bd ul").append(_html);
                        $(".slideBox02 .hd ul").append("<li>"+(i+1)+"</li>");
                    }
                })
            }
            jQuery(".slideBox02").slide({mainCell:".bd ul",effect:"fold",autoPlay:true,easing:"easeInQuint",interTime:5000});
            // 新闻中心跳转详情
            $(".slideBox02 .bd ul li").click(function(){
                var url = path + '/homepage/news/details/'+$(this).attr("name");
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
            var _html = "";
            for(var i=0;i<event.length;i++){
                var data = {picturePath:event[i].picturePath};
                $.ajax({
                    url:path + "/home/picture/show",
                    type:"POST",contentType: "application/json",data:JSON.stringify(data),async:false,
                    success:function (r) {
                        _html += '<div class="banner-solid" title="'+event[i].schoolTitle+'" name="'+event[i].id+'"><div class="mien_pic_child"><img src="data:image/gif;base64,'+r[0]+'"><img src="../static/images/frame.png"></div></div>';
                    }
                })
            }
            $("#banner03 .banner-wrapper").append('<div class="banner_con">'+_html+'</div>');
            if(event.length>4){
                $("#banner03 .banner-wrapper").append('<div class="banner_con">'+_html+'</div>');
                var num =0;
                var ML = parseInt($("#banner03 .banner-wrapper").css("margin-left"));
                function xyfg(){
                    if (num>-275*event.length){
                        num -= 1;
                        var newML = ML+num;
                        $("#banner03 .banner-wrapper:nth-child(1)").css("margin-left",newML+"px");
                    }else{
                        $("#banner03 .banner-wrapper:nth-child(1)").css("margin-left","0px");
                        num = 0;
                    }
                }
                var timer = setInterval(xyfg,40);
                $("#banner03 .banner-solid").mouseover(function () {
                    clearInterval(timer);
                });
                $("#banner03 .banner-solid").mouseout(function () {
                    timer = setInterval(xyfg,40);
                });
            }
            // 校园风光跳转详情
            $("#banner03 .banner-solid").click(function(){
                var url = path + '/homepage/school/details/'+$(this).attr("name");
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
                        _html += '<a href="'+event[i].windowUrl+'" name="'+event[i].id+'" title="'+event[i].windowTitle+'" ><img src="data:image/gif;base64,'+r+'"></a>';
                    }
                })
            }
            _html += '<div class="imgClose">x&nbsp;关闭</div>';
            $("body").append(_html);
            // 调用飘窗
            var floatImage = new imgFloat(".floatImage",{
                "time":12,
                "speedx":0.8,
                "speedy": 0.6
            });
            floatImage.resize();
            floatImage.move();
            $(".floatImage .imgClose").click(function(){
                $(this).parent(".floatImage").remove();
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
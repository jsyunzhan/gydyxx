$(function () {

    // banner轮播图
    var banner = new Swiper(".banner",{
        autoplay : 3000,
        speed: 1000,
        loop:true,
        pagination: '.pagination',
        paginationClickable: true,
        autoplayDisableOnInteraction : false,
    });
    $(".arrow-left").on("click", function(e){
        e.preventDefault();
        banner.swipePrev();
    })
    $(".arrow-right").on("click", function(e){
        e.preventDefault();
        banner.swipeNext();
    })

    // 滚动通知
    rollDisplay(".content_notice",2000,2000)

    // 新闻轮播图
    var news_banner = new Swiper(".news_banner",{
        autoplay : 3000,
        speed: 1000,
        loop:true,
        pagination: '.pagination01',
        paginationClickable: true,
        autoplayDisableOnInteraction : false,
    });

    /*功能模块快捷方式*/
    $(".infor .icon").mouseover(function(){
        $(".infor .icon .icon_font").css("color","#565656");
        $(this).find(".icon_font").css("color","#ce6e0a");
    })
    $(".infor .icon").mouseout(function(){
        $(".infor .icon .icon_font").css("color","#565656");
    })

    // 学校风采图片切换
    var mien_pic = new Swiper(".mien_pic",{
        autoplay : 5000,
        speed: 1000,
        loop:true,
        pagination: '.pagination02',
        paginationClickable: true,
        autoplayDisableOnInteraction : false,
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
    })
    $(".platform").mouseout(function(){
        $(".linking_list").hide();
    })

    // 飘窗
    var floatImage01 = new imgFloat(".floatImage01",{
        "time":5,
        "speedx":1,
        "speedy":1.5
    })
    floatImage01.resize();
    floatImage01.move();

    var floatImage02 = new imgFloat(".floatImage02",{
        "time":3,
        "speedx":1,
        "speedy":1
    })
    floatImage02.resize();
    floatImage02.move();
    $(".floatImage .imgClose").click(function(){
        $(this).parent(".floatImage").remove();
    })
})
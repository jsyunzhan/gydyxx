var data_number = "";
$(function () {
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
    function urlLinkto(){
        data_number = jsString.length;
        var _html = "";
        for(var i=0;i<jsString.length;i++){
            _html += '<div class="every_search clearfix" name="'+jsString[i].tableId+'" url="'+jsString[i].url+'"><span class="listChild01">'+jsString[i].title+'</span><span class="listChild02">' + timestampToTime03(jsString[i].createDate) + '</span></div>';
        }
        $(".newsCon").append(_html);
        $(".every_search").hide();
        for(var i=0;i<jsString.length;i++){
            if(i<30){
                $($(".every_search")[i]).show();
            }
        }
        // 搜索跳转详情
        $(".every_search").click(function () {
            var url = path + $(this).attr("url") + $(this).attr("name");
            window.location.href = url;
        })
    }
    urlLinkto()

    paging(30);

})
var data_number = "";
$(function () {

    $.ajax({
        url:path + "/homepage/resources/list",
        type:"GET",dataType:"json",async:false,
        success:function (event) {
            data_number = event.length;
            var _html = "";
            for (var i=0;i<event.length;i++){
                _html += '<div class="every_search clearfix" name="'+event[i].id+'"><span class="listChild01">'+event[i].resourcesTitle+'</span><span class="listChild02">' + timestampToTime03(event[i].createDate) + '</span></div>';
            }
            $(".newsCon").append(_html);
            $(".every_search").hide();
            for(var i=0;i<event.length;i++){
                if(i<30){
                    $($(".every_search")[i]).show();
                }
            }
            // 家校资源跳转详情
            $(".every_search").click(function () {
                var url = path + '/homepage/resources/details/'+$(this).attr("name");
                window.location.href = url;
            })
        }
    });

    paging(30);

})
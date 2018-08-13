$(function () {

    // 导航栏下拉菜单
    $(".title_tab").mouseover(function () {
        $(this).find(".second_title").show();

        $(".title_tab .second_title a").mouseover(function () {
            $(this).addClass("title_over");
        })
        $(".title_tab .second_title a").mouseout(function () {
            $(this).removeClass("title_over");
        })
    });
    $(".title_tab").mouseout(function () {
        $(this).find(".second_title").hide();
    });

    // 搜索
    function searchMethod(){
        var title = $(".search_input input").val();
        var url = path + "/homepage/home/search?title=" + title;
        window.location.href = url;
    }

    $(".wrapper .search span").click(function () {
        searchMethod()
    });

    $(document).keydown(function(event){
        if(event.keyCode == 13){
            searchMethod();
        }
    });


    // 校史天地
    $.ajax({
        url:path + "/homepage/history/list",
        type:"GET",dataType:"json",async:false,
        success:function (event) {
            var _html = "";
            for (var i=0;i<event.length;i++){
                if(i<6) {
                    _html += '<div class="history_list" name="'+event[i].id+'">' + event[i].historyTitle + '</div>';
                }
            }
            $(".history_con").append(_html);
            // 校史天地跳转详情
            $(".history_con .history_list").click(function () {
                var url = path + '/homepage/history/details/'+$(this).attr("name");
                window.location.href = url;
            })
        }
    });
});
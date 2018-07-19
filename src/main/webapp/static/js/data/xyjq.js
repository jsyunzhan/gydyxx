var data_number = "";
$(function () {

    $.ajax({
        url:path + "/homepage/celebrate/list",
        type:"GET",dataType:"json",async:false,
        success:function (event) {
            data_number = event.length;
            var _html = "";
            for (var i=0;i<event.length;i++){
                _html += '<div class="every_search clearfix" name="'+event[i].id+'"><span class="listChild01">'+event[i].celebrateTitle+'</span><span class="listChild02">' + timestampToTime03(event[i].createDate) + '</span></div>';
            }
            $(".newsCon").append(_html);
            $(".every_search").hide();
            for(var i=0;i<event.length;i++){
                if(i<30){
                    $($(".every_search")[i]).show();
                }
            }
        }
    });

    paging(30);

})
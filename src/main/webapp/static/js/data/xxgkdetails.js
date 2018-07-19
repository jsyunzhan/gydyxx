$(function () {

    $.ajax({
        url:path + "/homepage/profile/list",
        type:"GET",dataType:"json",
        success:function(event){
            $(".main_title01").text(event[0].profileTitle);
            $(".newsCon").append(event[0].profileDetails);
        }
    })

})
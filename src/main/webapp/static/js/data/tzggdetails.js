$(function () {

    $(".main_title01").text(title);
    var data = {picturePath:picturePath};
    var _html = "";
    if(picturePath!=""){
        $.ajax({
            url:path + "/home/picture/show",
            type:"POST",contentType: "application/json",data:JSON.stringify(data),async:false,
            success:function(event){
                for (var i=0;i<event.length;i++){
                    _html += '<div class="imgDetial"><img src="data:image/gif;base64,'+event[i]+'"></div>';
                }
            }
        })
    }
    _html += '<p>'+details+'</p>';
    $(".newsCon").append(_html);


})
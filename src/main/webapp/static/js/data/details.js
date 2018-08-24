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
                    _html += '<img src="data:image/gif;base64,'+event[i]+'"  class="imgDetial">';
                }
            }
        })
    }
    var dd = details.replace(/<p>/g,"<div class='newsCon_font'>");
    dd = dd.replace(/<\/p>/g,"</div>");
    _html += dd;
    $(".newsCon").append(_html);

})
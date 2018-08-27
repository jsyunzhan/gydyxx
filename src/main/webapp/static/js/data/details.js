$(function () {

    $(".main_title01").text(title);
    var data = {picturePath:picturePath};
    var _html = "";
    if(picturePath!=""){
        $.ajax({
            url:path + "/home/picture/show",
            type:"POST",contentType: "application/json",data:JSON.stringify(data),async:false,
            success:function(event){
                var dd = details.replace(/<p>/g,"<div class='newsCon_font'>");
                dd = dd.replace(/<\/p>/g,"</div>");
                dd = dd.replace(/{\d+}/g,function (str) {
                    var str = Number(str.match(/\d+/g));
                    str--;
                    return '<img src="data:image/gif;base64,'+event[str]+'" class="imgDetial">';
                });
                _html += dd;
                $(".newsCon").append(_html);
            }
        })
    }
})
$(function(){
    $(".submit").click(function(){
        var name = $(".message:nth-child(1) input").text();
        console.log(name);
        $.ajax({
            url:path + "/homepage/email/add",
            type:"GET",dataType:"json",
            success:function (event) {

            }
        })
    })

})
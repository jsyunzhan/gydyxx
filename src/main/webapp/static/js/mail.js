$(function(){
    var count = 0;
    $(".submit").click(function(){
        count++;
        if(count==1){
            var sendName = $("#sendName").val();
            var sendEmail = $("#sendEmail").val();
            var sendNumber = $("#sendNumber").val();
            var sendDetails = $("#sendDetails").val();
            if(sendName!=""&&sendEmail!=""&&sendDetails!=""){
                var data = {sendName:sendName,sendEmail:sendEmail,sendNumber:sendNumber,sendDetails:sendDetails};
                $.ajax({
                    url:path + "/homepage/email/add",type:"POST",contentType: "application/json",data:JSON.stringify(data),
                    success:function (r) {
                        $(".timeLimit").show();
                        setTimeout(function(){
                            window.location.href = path + "/pc/homepage.jsp";
                        },5000);
                    }
                })
            }else{
                alert("抱歉，您的信息未填写完整！");
                count = 0;
            }
        }
    })

    $(".reset").click(function(){
        $("#sendName").val("");
        $("#sendEmail").val("");
        $("#sendNumber").val("");
        $("#sendDetails").val("");
    })

})
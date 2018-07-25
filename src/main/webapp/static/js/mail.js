$(function(){
    $(".submit").click(function(){
        var sendName = $("#sendName").val();
        var sendEmail = $("#sendEmail").val();
        var sendNumber = $("#sendNumber").val();
        var sendDetails = $("#sendDetails").val();
        var data = {sendName:sendName,sendEmail:sendEmail,sendNumber:sendNumber,sendDetails:sendDetails};

        $.ajax({
            url:path + "/homepage/email/add",type:"POST",contentType: "application/json",data:JSON.stringify(data),
            success:function (r) {
                alert("1")
            }
        })
    })

})
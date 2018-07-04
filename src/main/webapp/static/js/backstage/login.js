$(function () {
    $("#loginSub").on('click',function () {
        login();
    });

    $(document).keydown(function(event){
        if(event.keyCode == 13){
            login();
        }
    })

    function login() {
        var data = $("#loginForm").serializeObject();

        $.ajax({
            url: path +'/security/login',
            type: 'POST', dataType: "json",
            data: data,
            success: function (serverResponse) {
                if (serverResponse.success) {
                    location.href = path + "/security/home";
                } else {
                    $("#userError").text(serverResponse.reason);
                    $("#userError").addClass("red_1");
                }
            },
            error: function (xmlHttpReq, textStatus, errorThrow) {

            }
        });
    }


});
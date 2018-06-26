$(function () {
    $("#loginSub").on('click',function () {
        login();
    });

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

                }
            },
            error: function (xmlHttpReq, textStatus, errorThrow) {

            }
        });
    }
});
$(function () {

    $.ajax({
        url: path + '/wages/wagesquerymanpage/list',
        type: 'get',
        dataTye: 'json',
        success: function (data) {
            console.log(data)
        }
    });

});
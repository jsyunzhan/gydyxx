$(function () {
    // 日期格式转换
    function timestampToTime01(timestamp){
        var date = new Date(timestamp);
        Y = date.getFullYear() + '-';
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        D = date.getDate() + ' ';
        return Y+M+D;
    }

    $.ajax({
        url: path + '/wages/wagesquerymanpage/list',
        type: 'get',
        dataTye: 'json',
        success: function (data) {
            console.log(data);
            var _html = "";
            for(var i=0;i<data.length;i++){
                _html += '<div class="pay"><div class="payNum"><p title="姓名">姓名</p><p>'+data[i].accountName+'</p></div>';
                _html += '<div class="payNum"><p title="发放时间">发放时间</p><p>'+timestampToTime01(data[i].wagesData)+'</p></div>';
                _html += '<div class="payNum"><p title="工资名称">工资名称</p><p>'+data[i].wagesName+'</p></div>';
                var data01 = data[i].wagesdetails.split(";");
                for(var j=0;j<data01.length-1;j++){
                    var data02 = data01[j].split(":");
                    _html += '<div class="payNum"><p title="'+data02[0]+'">'+data02[0]+'</p><p>'+data02[1]+'</p></div>';
                }
                _html += '</div>';
            }
            $(".wrapper").append(_html);
        }
    });

});
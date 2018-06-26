
//计算分页page
function getPage(param) {
    var rows = param.rows;
    var page = param.page;
    param.page = (page-1)*rows;
    return param;
}

//角色验证是否重复
function checkRoleName(data) {
    var flag = false;
    $.ajax({
        url: path + "/system/rolemanpage/checkrolename" , type: "POST", dataType:"json",data:data,async: false,
        success: function (r) {
            flag = r;
        }
    });
    return flag;
}

//登录名验证是否重复
function checkLoginName(data) {
    var flag = false;
    $.ajax({
        url: path + "/system/accountmanpage/checkaccountname" , type: "POST", dataType:"json",data:data,async: false,
        success: function (r) {
            flag = r;
        }
    });
    return flag;
}

//验证密码是否一样
$.extend($.fn.validatebox.defaults.rules, {
    passwordMatch: {
        validator: function (value, param) {
            return value == $(param[0]).val();
        },
        message: "两次输入密码不匹配"
    }
});

Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

/**
 * 设置日期格式，按照全局格式
 * @param value
 * @returns {string}
 */
function formatDate(value) {
    if (value) {
        return new Date(value).Format("yyyy-MM-dd");
    } else {
        return "";
    }
}
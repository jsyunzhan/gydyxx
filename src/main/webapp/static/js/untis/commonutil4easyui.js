function nullToBlank(str) {
    if (str == null || str == 'null' || str == undefined) {
        return "";
    } else {
        return str;
    }
}
function nullToEmpty(str) {
    if (str == null || str == 'null' || str == undefined || str == "") {
        return " ";
    } else {
        return str;
    }
}

function nullOrEmptyToZero(str) {
    if (str == null || str == 'null' || str == undefined || $.trim(str) == '') {
        return 0;
    } else {
        return str;
    }
}

/**
 * 字符串是否为空
 * @return {boolean}
 * @param str
 */
function isNullOrEmpty(str) {
    var strVal = $.trim(str);
    return strVal == '';
}

function getFileType(fileName) {
    var varIndex = fileName.lastIndexOf(".");

    if (varIndex == -1)return "";

    return fileName.substr(varIndex + 1, fileName.length);
}

function booleanRenders(value) {
    if (value == '1') {
        return "<span style='color:green;'>" + CLIENT_COMMON_I18N.lab_true + "</span>";
    }
    else return "<span style='color:red;'>" + CLIENT_COMMON_I18N.lab_false + "</span>";
}

//格式化性别
function formatGender(value) {
    if (value == "1") {
        return CLIENT_COMMON_I18N.lab_gender_male;
    } else if (value == "2") {
        return CLIENT_COMMON_I18N.lab_gender_female;
    } else return CLIENT_COMMON_I18N.lab_gender_unknown;
}

function formatterNull(str) {
    if (str == null || str == 'null' || str == undefined) {
        return "<span style='color:green;'></span>";
    }
}

function booleanRender(value) {
    if (value === true) {
        return "<span style='color:green;'>" + CLIENT_COMMON_I18N.lab_true + "</span>";
    }
    else return "<span style='color:red;'>" + CLIENT_COMMON_I18N.lab_false + "</span>";
}

function enableRender(value) {
    if (value === true) {
        return "<span style='color:green;'>" + CLIENT_COMMON_I18N.lab_enable + "</span>";
    }
    else return "<span style='color:red;'>" + CLIENT_COMMON_I18N.lab_unable + "</span>";
}

function enableFreezeRender(value) {
    if (value === true) {
        return "<span style='color:green;'>" + CLIENT_COMMON_I18N.lab_unfreeze + "</span>";
    }
    else return "<span style='color:red;'>" + CLIENT_COMMON_I18N.lab_freeze + "</span>";
}
/**
 * 获得文件后缀名
 * @param fileName
 * @return {string} 获得文件后缀名
 */
function getFileExtension(fileName) {
    var varIndex = fileName.lastIndexOf(".");

    if (varIndex == -1)return "";

    return fileName.substr(varIndex + 1, fileName.length);
}

//获取时间的拼接字符串
function getDateTimeString() {
    var now = new Date();
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    if (month<10){
        month = '0' + month;
    }
    var day = now.getDate();            //日
    if (day<10){
        day = '0' + day;
    }
    var hours = now.getHours();         //时
    if (hours<10){
        hours = '0' + hours;
    }
    var minutes = now.getMinutes();     //分
    if (minutes<10){
        minutes = '0' + minutes;
    }
    var seconds = now.getSeconds();     //秒
    if (seconds<10){
        seconds = '0' + seconds;
    }
    return year + ""+ month + ""+ day + ""+hours + ""+minutes + ""+seconds;
}

function showMessage(msg) {
    $.messager.alert("系统提示", msg);
}
function showErrorMessage(msg) {
    $.messager.alert("系统提示", msg, 'error');
}
function showInfoMessage(msg) {
    $.messager.alert("系统提示", msg, 'info');
}

function showInfoMessageWithFn(msg, fn) {
    $.messager.alert("系统提示", msg, 'info', fn);
}

function showQuestionMessage(msg) {
    $.messager.alert("系统提示", msg, 'question');
}
function showWarningMessage(msg) {
    $.messager.alert("系统提示", msg, 'warning');
}

function showConfirm(msg, fn) {
    $.messager.confirm("系统提示", msg, function (r) {
        if (r) {
            fn();
        }
    });
}

/***
 * @param theUri
 * @param theObject
 * @param theHttpMethod
 * @param theInvokeAfter
 */
function invokeAjaxRequest(theUri, theObject, theHttpMethod, theInvokeAfter) {

    $.ajax({
        url: theUri, type: theHttpMethod, dataType: "json", timeout: 360000, cache: false,
        data: theObject,
        success: function (serverResponse) {
            if (theInvokeAfter != null && theInvokeAfter != undefined) {
                theInvokeAfter(serverResponse);
            }
        },
        error: function (xmlHttpReq, textStatus, errorThrow) {
            showErrorMessage("服务器内部错误" + ':' + xmlHttpReq.status);
        }
    });
}

/***
 * @param theUri
 * @param theObject
 * @param theHttpMethod
 * @param theInvokeAfter
 * @param theSuccessMsg
 * @param theErrorMsg
 */
function invokeAjaxRequestJson(theUri, theObject, theHttpMethod, theInvokeAfter, theSuccessMsg, theErrorMsg) {
    $.ajaxSetup({
        complete: function (XMLHttpRequest, textStatus) {
            if (textStatus == "parsererror") {
                $.messager.alert(CLIENT_COMMON_I18N.msg_prompt_message, CLIENT_COMMON_I18N.msg_error_login_timeout, 'info', function () {
                    top.location.href = webroot + 'index.jsp';
                });
            } else if (textStatus == "error") {
                $.messager.alert(CLIENT_COMMON_I18N.msg_prompt_message, CLIENT_COMMON_I18N.msg_error_request_timeout, 'info', function () {
                    //top.location.href = webroot + 'index.jsp';
                });
            }
        }
    });
    $.ajax({
        url: theUri, type: theHttpMethod, contentType: "application/json", timeout: 360000, cache: false,
        data: JSON.stringify(theObject),
        success: function (serverResponse) {
            if (serverResponse.success) {
                if (theInvokeAfter != null && theInvokeAfter != undefined) {
                    theInvokeAfter(serverResponse);
                }

                showInfoMessage($.trim(theSuccessMsg) == '' ? CLIENT_COMMON_I18N.msg_action_success : theSuccessMsg);
            }
            else {
                if (serverResponse.reason) {
                    showErrorMessage(serverResponse.reason);
                } else {
                    showErrorMessage($.trim(theErrorMsg) == '' ? CLIENT_COMMON_I18N.msg_action_failure : theErrorMsg);
                }

            }
        },
        error: function (xmlHttpReq, textStatus, errorThrow) {
            showErrorMessage(CLIENT_COMMON_I18N.msg_server_error + ':' + xmlHttpReq.status);
        }
    });
}

function formatDateLine(val) {
    if (val == null || val == '' || val == 'undefined') {
        return "";
    }
    return Date.format(val, 'yyyy-MM-dd');
}

function formatDateTimeLine(val) {
    if (val == null || val == '' || val == 'undefined') {
        return "";
    }
    return Date.format(val, 'yyyy-MM-dd HH:mm:ss');
}


function formatDateMinLine(val) {
    if (val == null || val == '' || val == 'undefined') {
        return "";
    }
    return Date.format(val, 'yyyy-MM-dd HH:mm');
}

/**
 * 单元格值过长
 */
function formatColumnValue(value, row, index) {
    if (value == null || value == '' || value == 'undefined') {
        return;
    }

    return '<span style="width: 200px;word-wrap:break-word;" title="' + value + '" class="easyui-tooltip">' + value + '</span>';
}

/**
 * 只设置数量统计
 * @param val
 * @returns {*}
 */
function formatAmount(val) {
    if (val > 0) {
        return "<span style='color:green;'>" + val + "</span>";
    } else {
        return "<span style='color:red;'>" + val + "</span>";
    }
}


/**
 * 统计专用
 * @param val
 * @returns {*}
 */
function formatDateForStatistics(val) {
    if (val == null || val == '' || val == 'undefined') {
        return "";
    }
    if (isNaN(val)){
        return val;
    }
    return Date.format(val, currentDateFormat);
}

/**
 * 设置日期格式，并且包含时间
 * @param val
 * @returns {*}
 */
function formatFullDate(val) {
    if (val == null || val == '' || val == 'undefined') {
        return "";
    }
    return Date.format(val, currentDateFormat) + ' ' + formatDateTime(val);
}

/**
 * 设置日期格式，并且包含时间(到分钟)
 * @param val
 * @returns {*}
 */
function formatFullDateToMin(val) {
    if (val == null || val == '' || val == 'undefined') {
        return "";
    }
    return Date.format(val, currentDateFormat) + ' ' + formatDateTimeToMin(val);
}

/**
 * 设置日期格式，并且包含时间(到分钟)
 * @param val
 * @returns {*}
 */
function formatFullDateToMinApm(val) {
    if (val == null || val == '' || val == 'undefined') {
        return "";
    }
    return Date.format(val, currentDateFormat) + ' ' + formatDateTimeToMinApm(val);
}
/**
 * 转化时间格式(12小时制 or 24小时制),默认24小时制
 * @param val 时间
 * @returns {*}
 */
function formatDateTime(val) {
    function pad(n) {
        return n < 10 ? '0' + n : n
    }

    if (val == null || val == '' || val == 'undefined') {
        return "";
    }
    var date = Date.toDate(val);
    var hours = date.getHours();
    var endWith = '';
    if (currentTimeFormat && currentTimeFormat == '12hour') {
        //12小时制显示模式
        if (hours < 12) {
            endWith = 'AM ';
        } else if (hours == 12) {
            endWith = 'PM';
        } else if (hours > 12 && hours < 24) {
            hours -= 12;
            endWith = 'PM';
        } else if (hours == 24) {
            endWith = 'AM ';
        }
        return pad(hours) + Date.format(val, ':mm:ss') + ' ' + endWith;
    } else {
        //24小时制显示模式
        return Date.format(val, 'HH:mm:ss');
    }
}

/**
 * 转化时间格式(12小时制 or 24小时制),默认24小时制(到分钟)
 * @param val 时间
 * @returns {*}
 */
function formatDateTimeToMin(val) {
    function pad(n) {
        return n < 10 ? '0' + n : n
    }

    if (val == null || val == '' || val == 'undefined') {
        return "";
    }
    var date = Date.toDate(val);
    var hours = date.getHours();
    if (currentTimeFormat && currentTimeFormat == '12hour') {
        //12小时制显示模式
        if (hours < 12) {
            //hours = 'AM ' + pad(hours);
        } else if (hours == 12) {
            //hours = 'PM 12';
        } else if (hours > 12 && hours < 24) {
            hours -= 12;
            //hours = 'PM ' + pad(hours);
        } else if (hours == 24) {
            //hours = 'AM 00';
        }
        return pad(hours) + Date.format(val, ':mm');
    } else {
        //24小时制显示模式
        return Date.format(val, 'HH:mm');
    }
}

/**
 * 转化时间格式(12小时制 or 24小时制),默认24小时制(到分钟) 带AM PM
 * @param val 时间
 * @returns {*}
 */
function formatDateTimeToMinApm(val) {
    function pad(n) {
        return n < 10 ? '0' + n : n
    }

    if (val == null || val == '' || val == 'undefined') {
        return "";
    }
    var date = Date.toDate(val);
    var hours = date.getHours();
    var endWith = '';
    if (currentTimeFormat && currentTimeFormat == '12hour') {
        //12小时制显示模式
        if (hours < 12) {
            endWith = 'AM ';
        } else if (hours == 12) {
            endWith = 'PM';
        } else if (hours > 12 && hours < 24) {
            hours -= 12;
            endWith = 'PM';
        } else if (hours == 24) {
            endWith = 'AM ';
        }
        return pad(hours) + Date.format(val, ':mm') + ' ' + endWith;
    } else {
        //24小时制显示模式
        return Date.format(val, 'HH:mm');
    }
}

/**
 * 格式化数字精度
 * @param val
 * @returns {*}
 */
function formatNumber(val) {
    if (val == null || val == '' || val == 'undefined' || isNaN(val)) {
        return "";
    }
    return val.toFixed(currentDataPrecision);
}

/**
 * Date格式-- 限制30天
 * 时间校验,启动(时间段查询,结束时间要大于开始时间  限制30天)
 * @beginTimeId 开始时间
 * @endTimeId 结束时间的Id
 */
function setTimeLimit(beginTime, endTimeId) {
    var endTime = $("#" + endTimeId).datebox("getValue");
    $("#" + endTimeId).datebox('calendar').calendar({
        validator: function (endTime) {
            var now = new Date(beginTime);
            var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
            var d2 = new Date(now.getFullYear(), now.getMonth(), now.getDate()+30);
            return d1<=endTime && endTime<=d2;
        }
    });
    if (beginTime > new Date(endTime)) {
        $("#" + endTimeId).datebox("setValue", "");
    }
    var end = new Date(endTime);
    if(beginTime<new Date(end.getFullYear(), end.getMonth(), end.getDate()-30)){

        $("#" + endTimeId).datebox();
    }
}


/**
 * Date格式---无时间限制
 * 时间校验,启动(时间段查询,结束时间要大于开始时间 无时间限制)
 * @beginTimeId 开始时间
 * @endTimeId 结束时间的Id
 */
function setTimeLimitOne(beginTime, endTimeId) {
    var endTime = $("#" + endTimeId).datebox("getValue");
    $("#" + endTimeId).datebox('calendar').calendar({
        validator: function (endTime) {
            return  beginTime <= endTime;
        }
    });
    if (beginTime > new Date(endTime)) {
        $("#" + endTimeId).datebox();
    }
}

/**
 * Time格式
 * 时间校验,启动(时间段查询,结束时间要大于开始时间)
 * @beginTimeId 开始时间
 * @endTimeId 结束时间的Id
 */
function setDateTimeLimit(beginTime, endTimeId) {
    var endTime = $("#" + endTimeId).datetimebox("getValue");
    $("#" + endTimeId).datetimebox('calendar').calendar({
        validator: function (endTime) {
            return new Date(beginTime) <= new Date(endTime);
        }
    });
    if (new Date(beginTime) > new Date(endTime)) {
        $("#" + endTimeId).datetimebox();
    }

}

/**
 * Date格式
 * 时间校验,关闭(时间段查询,结束时间要大于开始时间,重置时关闭)
 * @endTimeId 结束时间的Id
 */
function cancelTimeLimit(endTimeId) {
    $("#" + endTimeId).datebox('calendar').calendar({
        validator: function () {
            return true;
        }
    });
}
/**
 * Time格式
 * 时间校验,关闭(时间段查询,结束时间要大于开始时间,重置时关闭)
 * @endTimeId 结束时间的Id
 */
function cancelDateTimeLimit(endTimeId) {
    $("#" + endTimeId).datetimebox('calendar').calendar({
        validator: function () {
            return true;
        }
    });
}

//故障报告自适应弹窗高度设置  (height：高度 num:需要减少的高度)
function getbordyHeight(Height,num) {
    var bordyHeight = $(document.body).height() - num;
    if (bordyHeight > Height) {
        return Height;
    } else {
        return $(document.body).height() - num;
    }
};

//故障报告自适应弹窗宽度设置 (width：宽度 num:需要减少的宽度)
function getbordyWidth(width,num) {
    var bordyWidth = $(document.body).width() - num;
    if (bordyWidth >width) {
        return width;
    } else {
        return $(document.body).width() - num;
    }
};

//税控机自适应弹窗高度设置
function getWindowHeight() {
    var bordyHeight = $(document.body).height() - 20;
    if (bordyHeight > 500) {
        return 500;
    } else {
        return $(document.body).height() - 20;
    }
};

//查看该厂商下该设备型号的个数
function checkModel(data) {
    var count = 0;
    $.ajax({
        url:deviceroot + 'devicereg/checkdevicemodel',type:'POST',dataType:"json",data:data,async: false,
        success:function (result) {
            count = result;
        }
    });
    return count;
}

//datagrid 行号显示扩展
$.extend($.fn.datagrid.methods, {
    fixRownumber: function (jq) {
        return jq.each(function () {
            var panel = $(this).datagrid("getPanel");
            var clone = $(".datagrid-cell-rownumber", panel).last().clone();
            clone.css({
                "position": "absolute",
                left: -1000
            }).appendTo("body");
            var width = clone.width("auto").width();
            if (width > 25) {
                //多加5个像素,保持一点边距
                $(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).width(width + 5);
                $(this).datagrid("resize");
                //一些清理工作
                clone.remove();
                clone = null;
            } else {
                //还原成默认状态
                $(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).removeAttr("style");
            }
        });
    }
});
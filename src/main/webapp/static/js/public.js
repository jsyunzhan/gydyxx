// 居中效果
function center(obj){
    var win_width = $(window).width();
    var win_height = $(window).height();
    var obj_width = $(obj).width();
    var obj_height = $(obj).height();
    $(obj).css({"left":(win_width-obj_width)/2,"top":(win_height-obj_height)/2});
}

// 文字竖向滚动效果
function rollDisplay(obj,speedtime,stoptime){
    var obj = obj;
    var speedtime = speedtime;
    var stoptime = stoptime;
    function rolltimer(){
        var roll_one = $(obj);
        var roll_two = $(obj).children();
        var roll_three = $(obj).children().children();
        var long = roll_three.css("line-height");
        roll_two.animate({"margin-top":"-"+long},speedtime,function(){
            var odiv = $(obj+" div"+" div:nth-child(1)");
            odiv.remove();
            roll_two.append(odiv);
            roll_two.css({"margin-top":"0px"});
        });
    }
    setInterval(rolltimer,stoptime+speedtime);
}

// 属性扩展
function extend(obj1,obj2){
    for(var attr in obj2){
        obj1[attr] = obj2[attr];
    }
}

// 浮动飘窗效果
function imgFloat(obj,setting){
    // 默认参数
    this.defaultparam = {
        "time":10,
        "speedx":0,
        "speedy":0
    };
    this.name = obj;
    this.win_width = $(window).width();
    this.win_height = $(window).height();
    this.obj_width = $(obj).width();
    this.obj_height = $(obj).height();
    this.x = this.win_width - this.obj_width;
    this.y = this.win_height - this.obj_height;
    this.imgTop = parseInt($(obj).css("top"));
    this.imgLeft = parseInt($(obj).css("left"));
    this.top = true;
    this.left = true;
    extend(this.defaultparam,setting);
}

// 窗口大小改变
imgFloat.prototype.resize = function(){
    var This = this;
    $(window).resize(function(){
        This.win_width = $(window).width();
        This.win_height = $(window).height();
        This.x = This.win_width - This.obj_width;
        This.y = This.win_height - This.obj_height;
    })
}

imgFloat.prototype.move = function(){
    var This = this;
    console.log(This.imgTop,This.imgLeft);
    setInterval(function(){
        if (This.top == true&&This.imgTop >= This.y) {
            This.top = false;
        }else if(This.top == false&&This.imgTop <= 0){
            This.top = true;
        }
        if (This.left == true&&This.imgLeft>=This.x) {
            This.left = false;
        }else if(This.left == false&&This.imgLeft<=0){
            This.left = true;
        }
        if(This.top&&This.left){
            This.imgTop += This.defaultparam.speedy;
            This.imgLeft += This.defaultparam.speedx;
        }else if(This.top&&!This.left){
            This.imgTop += This.defaultparam.speedy;
            This.imgLeft -= This.defaultparam.speedx;
        }else if(!This.top&&This.left){
            This.imgTop -= This.defaultparam.speedy;
            This.imgLeft += This.defaultparam.speedx;
        }else if(!This.top&&!This.left){
            This.imgTop -= This.defaultparam.speedy;
            This.imgLeft -= This.defaultparam.speedx;
        }
        $(This.name).css({"top":This.imgTop,"left":This.imgLeft});
    },this.defaultparam.time);
}

// 弹窗
function pop(setting){
    this.defaultparam = {
        "html" : '',
        "events" : {"":function(){},"":function(){},"":function(){},"":function(){}}
    };
    this.pop_num = "window_"+parseInt(Math.random()*100000);
    extend(this.defaultparam,setting);
}

pop.prototype.popup = function(){
    $("body").append("<div id="+this.pop_num+" style='z-index:999999'></div>");
    $("body").append('<div id="mask"></div>');
    $("#"+this.pop_num).append(this.defaultparam.html);
    $("#"+this.pop_num).css("position","fixed");
    center("#"+this.pop_num);
    var This = this;
    for(var key in this.defaultparam.events){
        !function(key){
            $(key).click(function(){
                This.defaultparam.events[key]();
            });
        }(key)
    }
}

pop.prototype.popdown = function(){
    $("#"+this.pop_num).remove();
    $("#mask").remove();
}
// 弹窗示例
// $(function(){
// 	$(".btn").click(function(){
// 		var flag = new pop({
// 			"html":'<div class="popup"><div class="close"></div><div class="close1"></div></div>',
// 			"events":{
// 				".close":function(){
// 					console.log(1);
// 					flag.popdown();
// 				},
// 				".close1":function(){
// 					flag.popdown();
// 					console.log(2);
// 				}
// 			}
// 		});
// 		flag.popup();
// 	});
// })

function IEVersion() {
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器
    var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器
    var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
    if(isIE) {
        var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
        reIE.test(userAgent);
        var fIEVersion = parseFloat(RegExp["$1"]);
        if(fIEVersion == 7) {
            return 7;
        } else if(fIEVersion == 8) {
            return 8;
        } else if(fIEVersion == 9) {
            return 9;
        } else if(fIEVersion == 10) {
            return 10;
        } else {
            return 6;//IE版本<=7
        }
    } else if(isEdge) {
        return 'edge';//edge
    } else if(isIE11) {
        return 11; //IE11
    }else{
        return -1;//不是ie浏览器
    }
}
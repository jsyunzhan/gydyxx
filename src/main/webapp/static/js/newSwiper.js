

	// 属性扩展
	function extend(obj1,obj2){
	    for(var attr in obj2){
	        obj1[attr] = obj2[attr];
	    }
	}

	function newSwiper(setting){
		this.clickNum = 0;
        this.clearObj = "";
		this.defaultparam = {
	        "name":"",
	        "width":0,
	        "speed":0,
			"speedLong":0,
	        "time":0
	    };
		extend(this.defaultparam,setting);
	}

	newSwiper.prototype.prev = function(){
		var This = this;
		This.clickNum++;
		var defaultparam = this.defaultparam;
		if(This.clickNum == 1){
			// 一次位移中持续变化移动距离
			var num = 0;
			// 当前位置的margin-left值
			var ML = parseInt($(defaultparam.name+" .banner-wrapper").css("margin-left"));
			// 滑块可滑动的最大限度值
			var maxML = -1*defaultparam.width;
			if (ML<maxML) {
				var timer = setInterval(function(){
					if(num<defaultparam.width){
						num += defaultparam.speedLong;
						var newML = ML+num;
						$(defaultparam.name+" .banner-wrapper").css("margin-left",newML+"px");
					}else{
						This.clickNum = 0;
						$(defaultparam.name+" .pagetion span").removeClass("bannerChoosen");
						var nthNum = Math.abs(parseInt($(defaultparam.name+" .banner-wrapper").css("margin-left"))/defaultparam.width);
						$(defaultparam.name+" .pagetion span:nth-child("+nthNum+")").addClass("bannerChoosen");
						clearInterval(timer);
					}
				},this.defaultparam.speed)
			}else{
				var timer = setInterval(function(){
					if(num<defaultparam.width){
						num += defaultparam.speedLong;
						var newML = ML+num;
						$(defaultparam.name+" .banner-wrapper").css("margin-left",newML+"px");
					}else{
						$(defaultparam.name+" .banner-wrapper").css("margin-left",-1*defaultparam.width*($(defaultparam.name+" .banner-solid").length-2)+"px");
						This.clickNum = 0;
						$(defaultparam.name+" .pagetion span").removeClass("bannerChoosen");
						var nthNum = Math.abs(parseInt($(defaultparam.name+" .banner-wrapper").css("margin-left"))/defaultparam.width);
						$(defaultparam.name+" .pagetion span:nth-child("+nthNum+")").addClass("bannerChoosen");
						clearInterval(timer);
					}
				},this.defaultparam.speed)
			}
		}
	};

	// 下一页
	newSwiper.prototype.next = function(){
		var This = this;
		This.clickNum++;
		var defaultparam = this.defaultparam;
		if(This.clickNum == 1){
			// 一次位移中持续变化移动距离
			var num = 0;
			// 当前位置的margin-left值
			var ML = parseInt($(defaultparam.name+" .banner-wrapper").css("margin-left"));
			// 滑块可滑动的最大限度值
			var maxML = -1*defaultparam.width*($(defaultparam.name+" .banner-solid").length-2);
			if (ML>maxML) {
				var timer = setInterval(function(){
					if(num>-1*defaultparam.width){
						num -= defaultparam.speedLong;
						var newML = ML+num;
						$(defaultparam.name+" .banner-wrapper").css("margin-left",newML+"px");
					}else{
						This.clickNum = 0;
						$(defaultparam.name+" .pagetion span").removeClass("bannerChoosen");
						var nthNum = Math.abs(parseInt($(defaultparam.name+" .banner-wrapper").css("margin-left"))/defaultparam.width);
						$(defaultparam.name+" .pagetion span:nth-child("+nthNum+")").addClass("bannerChoosen");
						clearInterval(timer);
					}
				},this.defaultparam.speed)
			}else{
				var timer = setInterval(function(){
					if(num>-1*defaultparam.width){
						num -= defaultparam.speedLong;
						var newML = ML+num;
						$(defaultparam.name+" .banner-wrapper").css("margin-left",newML+"px");
					}else{
						$(defaultparam.name+" .banner-wrapper").css("margin-left",-1*defaultparam.width+"px");
						This.clickNum = 0;
						$(defaultparam.name+" .pagetion span").removeClass("bannerChoosen");
						var nthNum = Math.abs(parseInt($(defaultparam.name+" .banner-wrapper").css("margin-left"))/defaultparam.width);
						$(defaultparam.name+" .pagetion span:nth-child("+nthNum+")").addClass("bannerChoosen");
						clearInterval(timer);
					}
				},this.defaultparam.speed)
			}
		}
	};

	newSwiper.prototype.autoplay = function(){
		var This = this;
		this.clearObj = setInterval(function(){
			This.next();
		},this.defaultparam.time);
	}

    newSwiper.prototype.clearAutoplay = function(){
        clearInterval(this.clearObj);
    }


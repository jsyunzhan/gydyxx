var w_height = $(window).height();

function addTab(title, url){
    if ($('#content').tabs('exists', title)){
        $('#content').tabs('select', title);
    } else {
        var content = '<iframe scrolling="auto" frameborder="0"  src="'+ path + url+'" style="width:100%;height:100%;"></iframe>';
        $('#content').tabs('add',{
            title:title,
            content:content,
            closable:true
        });
    }
    $("iframe").height(w_height-110);
}

$(function(){


    $("#editPassWord").click(function () {

        $('#editPassWordForm').form('disableValidation').form('reset');
        $editPassWordWin.window('open');
    });

    /*************修改密码*******************/


    $('#confirmPassword').textbox({

        validType: 'passwordMatch["#password"]'
    });

    var $editPassWordForm = $('#editPassWordForm').form({
        novalidate: true
    });

    var $editPassWordWin = $('#editPassWordWin').window({
        title: "修改", closed: true, modal: true, height: 150,
        width: 360, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editPassWordWinFooter',
        onClose: function () {
            $('#editPassWordForm').form('disableValidation').form('reset');
        }
    });

    $('#editPassWordWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editPassWordForm').form('enableValidation').form('validate')) {
                return;
            }

            var password = $editPassWordForm.serializeObject(),
                url = path + "/system/editpassword";


            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(password),
                success:function (r) {
                    $editPassWordWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#editPassWordWinCloseBtn').linkbutton({
        onClick: function () {
            $editPassWordWin.window('close');
        }
    });


    $.ajax({
        url: path + '/security/resources/'+roleId,
        type: 'GET',
        dataType: "json",
        timeout: 360000,
        cache: false,
        success: function (resouces) {
            var _html = "";
            var  num = 0;
            for(var i=0;i<resouces.length;i++){
                if(!resouces[i].parentId){
                    _html += '<div class="modular">';
                    _html += '<div class="title_first" data-target="#modular_child'+num+'" data-toggle="collapse" id="'+resouces[i].id+'">';
                    _html += '<div class="icon_title"><img src="'+path+resouces[i].imageUrl+'"></div>'+resouces[i].resourceName;
                    _html += '<div class="arrow white_right"><img src="'+path+'/static/images/white_right.png"></div>';
                    _html += '<div class="arrow white_down none"><img src="'+path+'/static/images/white_down.png"></div></div>';
                    _html += '<ul id="modular_child'+num+'" class="collapse">';
                    num++;
                }else{
                    if(resouces[i+1]&&resouces[i+1].parentId) {
                        _html += '<li onclick="addTab('+'\''+resouces[i].resourceName+'\''+','+'\''+resouces[i].resourceUrl+'\''+')">'+resouces[i].resourceName+'</li>';
                    }else{
                        _html += '<li onclick="addTab('+'\''+resouces[i].resourceName+'\''+','+'\''+resouces[i].resourceUrl+'\''+')">'+resouces[i].resourceName+'</li></ul></div>';
                    }
                }
            }
            $(".sidebar").append(_html);
            modular_click();
        }
    });

    function modular_click(){
        $(".title_first").click(function () {
            if(IEVersion() == 8){
                var con = $(this).attr("aria-expanded");
                if(con == "true"){
                    $(this).find(".white_down").addClass("none");
                    $(this).find(".white_down").removeClass("inlineBlock");
                    $(this).find(".white_right").addClass("inlineBlock");
                    $(this).find(".white_right").removeClass("none");
                }else {
                    $(this).find(".white_down").removeClass("none");
                    $(this).find(".white_down").addClass("inlineBlock");
                    $(this).find(".white_right").removeClass("inlineBlock");
                    $(this).find(".white_right").addClass("none");
                }
            }else{
                var con = $(this).attr("aria-expanded");
                if(con == "true"){
                    $(this).find(".arrow").removeClass("rotate90");
                    $(this).find(".arrow").addClass("rotate0");
                }else{
                    $(this).find(".arrow").removeClass("rotate0");
                    $(this).find(".arrow").addClass("rotate90");
                }
            }
        })
    }

    function personal(){
        $(".personal").mouseover(function(){
            $(".personal_ul").show();
            $(".personal").mouseout(function (){
                $(".personal_ul").hide();
            })
        });
        $(".personal_ul li").click(function () {
            $(".personal_ul").hide();
        })
    }
    personal();

    $(".sidebar").css("max-height",w_height-70);
    $(".easyui-tabs").css("height",w_height-70);


});
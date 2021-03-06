$(function () {

    var selectedWindow, reqObj = {};
    var $windowGrid = $('#windowGrid').datagrid({
        url: path+ '/home/windowmanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'windowTitle', title: "飘窗标题", width: 150, sortable: true,
                align: 'left'
            },
            {
                field: 'windowUrl', title: "飘窗链接地址", width: 400, sortable: true,
                align: 'left'
            }
            , {
                field: 'statueId', title: "是否开启", width: 150, sortable: true,
                align: 'left',formatter:result
            }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addWindowWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedWindow) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editWindowForm.form('load', selectedWindow);

                        $(document).ready(function(){
                            var ue = UE.getEditor('containerEdit');
                            ue.ready(function() {//编辑器初始化完成再赋值
                                ue.setContent(selectedWindow.windowDetails);  //赋值给UEditor
                            });
                        });



                        if (selectedWindow.picturePath){
                            var data = {picturePath:selectedWindow.picturePath};
                            var url = path + "/home/picture/show";
                            $.ajax({
                                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(data),
                                success:function (r) {

                                    var pictureDiv = $('#editPicture');
                                    pictureDiv.empty();

                                    for (var i=0;i<r.length;i++){
                                        var picture = '<div style="float:left;width: 20%;margin: 0 2.5%;border: 1px solid #ccc; box-sizing:border-box;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;margin-bottom: 10px;">'
                                        picture += '<img src="data:image/gif;base64,' + r[i] + '" style="height: 100px;">'
                                        // picture += '<p style="text-align: center;-webkit-margin-before: 0em;-webkit-margin-after: 0em;line-height: 30px;">'+i+'</p></div>';
                                        pictureDiv.append(picture);
                                    }

                                }
                            });
                        }


                        $editWindowWin.window('open');
                    }

                }
            },
            {
                text: "删除", iconCls: 'icon-remove',
                handler: function () {
                    removeHandle();
                }
            },
            {
                text: "开启", iconCls: 'icon-remove',
                handler: function () {
                    open();
                }
            },
            {
                text: "关闭", iconCls: 'icon-remove',
                handler: function () {
                    close();
                }
            }
        ],
        onBeforeLoad: function (param) {
            getPage(param);
            $.extend(param, reqObj);
        },
        onSelect: function (index, row) {
            selectedWindow = row;

        },
        onLoadSuccess: function () {
            selectedWindow = $windowGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/


    var $addWindowForm = $('#addWindowForm').form({
        novalidate: true
    });

    var $addWindowWin = $('#addWindowWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addWindowWinFooter',
        onClose: function () {
            $('#pictureWindowForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#addWindowForm').form('disableValidation').form('reset');
        }
    });

    $('#addWindowWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addWindowForm').form('enableValidation').form('validate')) {
                return;
            }

            var windowData = $addWindowForm.serializeObject(),
                url = path + "/home/windowmanpage/add";

            if (!$("input[name=file]").val()){
                showErrorMessage("请选择图片！");
                return
            }

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/新闻中心",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureWindowForm')[0]),
                success:function (r) {
                    windowData.picturePath = r;
                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(windowData),
                        success:function (r) {
                            $windowGrid.datagrid('reload');
                            $addWindowWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });




        }
    });

    $('#addWindowWinCloseBtn').linkbutton({
        onClick: function () {
            $addWindowWin.window('close');
        }
    });

    /*************修改*******************/


    var $editWindowForm = $('#editWindowForm').form({
        novalidate: true
    });

    var $editWindowWin = $('#editWindowWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editWindowWinFooter',
        onClose: function () {
            $('#pictureWindowForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#editWindowForm').form('disableValidation').form('reset');
        }
    });

    $('#editWindowWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editWindowForm').form('enableValidation').form('validate')) {
                return;
            }

            var windowData = $editWindowForm.serializeObject(),
                url = path + "/home/windowmanpage/edit";
            windowData.id = selectedWindow.id;

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/飘窗",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureWindowForm')[0]),
                success:function (r) {
                    windowData.picturePath = r;

                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(windowData),
                        success:function (r) {
                            $windowGrid.datagrid('reload');
                            $editWindowWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });



        }
    });

    $('#editWindowWinCloseBtn').linkbutton({
        onClick: function () {
            $editWindowWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedWindow) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除窗口：<span style='color: red;'>{0}</span>？", selectedWindow.windowTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/windowmanpage/delete/"+selectedWindow.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $windowGrid.datagrid('reload');
                }
            })
        })
    }

    /************开启*************/

    function open() {
        if (!selectedWindow) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }

        if (1 == selectedWindow.statueId){
            showWarningMessage("该条目处于开启状态，请重新选择！");
            return
        }

        if (selectedWindow.statueCount > 1){
            showWarningMessage("飘窗不能超过二个，请先取消其他飘窗！");
            return
        }

        var msg = String.format("您确定要开启：<span style='color: red;'>{0}</span>？", selectedWindow.windowTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/windowmanpage/open/"+selectedWindow.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $windowGrid.datagrid('reload');
                }
            })
        })
    }

    /************关闭*************/

    function close() {
        if (!selectedWindow) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }

        if (0 == selectedWindow.statueId){
            showWarningMessage("该条目处于禁用状态，请重新选择！");
            return
        }

        var msg = String.format("您确定要关闭：<span style='color: red;'>{0}</span>？", selectedWindow.windowTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/windowmanpage/close/"+selectedWindow.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $windowGrid.datagrid('reload');
                }
            })
        })
    }

    /***************************图片上传*********************************************/
    $('#pictureWindowUploadBtnAdd,#pictureWindowUploadBtnEdit').linkbutton({
        onClick: function () {
            $pictureWindowWin.window('open');
        }
    });


    $('#pictureWindowWinCloseBtn').linkbutton({
        onClick: function () {
            $pictureWindowWin.window('close');
        }
    });

    var $pictureWindowWin = $('#pictureWindowWin').window({
        title: "图片上传",
        closed: true,
        modal: true,
        height: 155,
        width: 400,
        iconCls: 'icon-add',
        collapsible: false,
        minimizable: false,
        footer: '#pictureWindowWinFooter',
        onClose: function () {

        }
    });

    $('#pictureWindowWinSubmitBtn').linkbutton({
        onClick: function () {
            var $pictureWindowForm = $('#pictureWindowForm');

            $.ajax({
                url: path + "/home/noticemanpage/pictureDetail",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                data : new FormData($pictureWindowForm[0]),
                success:function (r) {
                    var pictureDiv = $('#addPicture,#editPicture');
                    pictureDiv.empty();

                    for (var i=0;i<r.length;i++){
                        var picture = '<div style="float:left;width: 20%;margin: 0 2.5%;border: 1px solid #ccc; box-sizing:border-box;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;margin-bottom: 10px;">'
                        picture += '<img src="data:image/gif;base64,' + r[i] + '" style="height: 100px;">'
                        // picture += '<p style="text-align: center;-webkit-margin-before: 0em;-webkit-margin-after: 0em;line-height: 30px;">'+i+'</p></div>';
                        pictureDiv.append(picture);
                    }
                    $pictureWindowWin.window('close');
                }
            });

        }
    });

    function result(value, row, index) {

        if (0 == value ) {
            return "<span style='color:red;'>" + '否' + "</span>";
        } else {
            return "<span style='color:blue;'>" + '是' + "</span>";
        }
    }

});
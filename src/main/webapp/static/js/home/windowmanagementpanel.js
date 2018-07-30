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
                                        var picture = '<img src="data:image/gif;base64,' + r[i] + '" style="width:100%;height:100%">';
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

    var reportAdd = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

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
            reportAdd.setContent("");
        }
    });

    $('#addWindowWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addWindowForm').form('enableValidation').form('validate')) {
                return;
            }

            var windowData = $addWindowForm.serializeObject(),
                url = path + "/home/windowmanpage/add";

            if (!windowData.windowDetails){
                showErrorMessage("正文不可为空！");
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

    var reportEdit = UE.getEditor('containerEdit', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

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
            reportEdit.setContent("");
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

            if (!windowData.windowDetails){
                showErrorMessage("正文不可为空！");
                return
            }

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
                        var picture = '<img src="data:image/gif;base64,' + r[i] + '" style="width:100%;height:100%">';
                        pictureDiv.append(picture);
                    }
                    $pictureWindowWin.window('close');
                }
            });

        }
    });

});
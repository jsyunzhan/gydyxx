$(function () {

    var selectedYi, reqObj = {};
    var $yiGrid = $('#yiGrid').datagrid({
        url: path+ '/home/yimanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'yiTitle', title: "致用邑标题", width: 150, sortable: true,
                align: 'left'
            }
            // ,
            // {
            //     field: 'yiDetails', title: "致用邑内容", width: 400, sortable: true,
            //     align: 'left'
            // }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addYiWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedYi) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editYiForm.form('load', selectedYi);

                        $(document).ready(function(){
                            var ue = UE.getEditor('containerEdit');
                            ue.ready(function() {//编辑器初始化完成再赋值
                                ue.setContent(selectedYi.yiDetails);  //赋值给UEditor
                            });
                        });

                        var data = {picturePath:selectedYi.picturePath};

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

                        $editYiWin.window('open');
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
            selectedYi = row;

        },
        onLoadSuccess: function () {
            selectedYi = $yiGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var reportAdd = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $addYiForm = $('#addYiForm').form({
        novalidate: true
    });

    var $addYiWin = $('#addYiWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addYiWinFooter',
        onClose: function () {
            $('#pictureYiForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#addYiForm').form('disableValidation').form('reset');
            reportAdd.setContext("")
        }
    });

    $('#addYiWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addYiForm').form('enableValidation').form('validate')) {
                return;
            }

            var yiData = $addYiForm.serializeObject(),
                url = path + "/home/yimanpage/add";

            if (!yiData.yiDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            if (!$("input[name=file]").val()){
                showErrorMessage("请选择图片！");
                return
            }

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/致用邑",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureYiForm')[0]),
                success:function (r) {
                    yiData.picturePath = r;
                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(yiData),
                        success:function (r) {
                            $yiGrid.datagrid('reload');
                            $addYiWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });




        }
    });

    $('#addYiWinCloseBtn').linkbutton({
        onClick: function () {
            $addYiWin.window('close');
        }
    });

    /*************修改*******************/

    var reportEdit = UE.getEditor('containerEdit', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $editYiForm = $('#editYiForm').form({
        novalidate: true
    });

    var $editYiWin = $('#editYiWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editYiWinFooter',
        onClose: function () {
            $('#pictureYiForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#editYiForm').form('disableValidation').form('reset');
            reportEdit.setContext("")
        }
    });

    $('#editYiWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editYiForm').form('enableValidation').form('validate')) {
                return;
            }

            var yiData = $editYiForm.serializeObject(),
                url = path + "/home/yimanpage/edit";
            yiData.id = selectedYi.id;

            if (!yiData.yiDetails){
                showErrorMessage("正文不可为空！");
                return
            }


            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/致用邑",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureYiForm')[0]),
                success:function (r) {
                    yiData.picturePath = r;

                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(yiData),
                        success:function (r) {
                            $yiGrid.datagrid('reload');
                            $editYiWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });



        }
    });

    $('#editYiWinCloseBtn').linkbutton({
        onClick: function () {
            $editYiWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedYi) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除致用邑：<span style='color: red;'>{0}</span>？", selectedYi.yiTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/yimanpage/delete/"+selectedYi.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $yiGrid.datagrid('reload');
                }
            })
        })
    }

    /***************************图片上传*********************************************/
    $('#pictureYiUploadBtnAdd,#pictureYiUploadBtnEdit').linkbutton({
        onClick: function () {
            $pictureYiWin.window('open');
        }
    });

    $('#pictureYiWinCloseBtn').linkbutton({
        onClick: function () {
            $pictureYiWin.window('close');
        }
    });

    var $pictureYiWin = $('#pictureYiWin').window({
        title: "图片上传",
        closed: true,
        modal: true,
        height: 155,
        width: 400,
        iconCls: 'icon-add',
        collapsible: false,
        minimizable: false,
        footer: '#pictureYiWinFooter',
        onClose: function () {

        }
    });

    $('#pictureYiWinSubmitBtn').linkbutton({
        onClick: function () {
            var $pictureYiForm = $('#pictureYiForm');

            $.ajax({
                url: path + "/home/noticemanpage/pictureDetail",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                data : new FormData($pictureYiForm[0]),
                success:function (r) {
                    var pictureDiv = $('#addPicture,#editPicture');
                    pictureDiv.empty();

                    for (var i=0;i<r.length;i++){
                        var picture = '<img src="data:image/gif;base64,' + r[i] + '" style="width:100%;height:100%">';
                        pictureDiv.append(picture);
                    }
                    $pictureYiWin.window('close');
                }
            });

        }
    });

});
$(function () {

    var selectedSpeech, reqObj = {};
    var $speechGrid = $('#speechGrid').datagrid({
        url: path+ '/home/speechmanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'speechTitle', title: "国旗下讲话标题", width: 150, sortable: true,
                align: 'left'
            },
            {
                field: 'speechDetails', title: "国旗下讲话内容", width: 400, sortable: true,
                align: 'left'
            }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addSpeechWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedSpeech) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editSpeechForm.form('load', selectedSpeech);

                        var data = {picturePath:selectedSpeech.picturePath};

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

                        $editSpeechWin.window('open');
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
            selectedSpeech = row;

        },
        onLoadSuccess: function () {
            selectedSpeech = $speechGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var $addSpeechForm = $('#addSpeechForm').form({
        novalidate: true
    });

    var $addSpeechWin = $('#addSpeechWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addSpeechWinFooter',
        onClose: function () {
            $('#pictureSpeechForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#addSpeechForm').form('disableValidation').form('reset');
        }
    });

    $('#addSpeechWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addSpeechForm').form('enableValidation').form('validate')) {
                return;
            }

            var speechData = $addSpeechForm.serializeObject(),
                url = path + "/home/speechmanpage/add";

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/国旗下讲话",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureSpeechForm')[0]),
                success:function (r) {
                    speechData.picturePath = r;
                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(speechData),
                        success:function (r) {
                            $speechGrid.datagrid('reload');
                            $addSpeechWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });




        }
    });

    $('#addSpeechWinCloseBtn').linkbutton({
        onClick: function () {
            $addSpeechWin.window('close');
        }
    });

    /*************修改*******************/

    var $editSpeechForm = $('#editSpeechForm').form({
        novalidate: true
    });

    var $editSpeechWin = $('#editSpeechWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editSpeechWinFooter',
        onClose: function () {
            $('#pictureSpeechForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#editSpeechForm').form('disableValidation').form('reset');
        }
    });

    $('#editSpeechWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editSpeechForm').form('enableValidation').form('validate')) {
                return;
            }

            var speechData = $editSpeechForm.serializeObject(),
                url = path + "/home/speechmanpage/edit";
            speechData.id = selectedSpeech.id;


            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/国旗下讲话",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureSpeechForm')[0]),
                success:function (r) {
                    speechData.picturePath = r;

                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(speechData),
                        success:function (r) {
                            $speechGrid.datagrid('reload');
                            $editSpeechWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });



        }
    });

    $('#editSpeechWinCloseBtn').linkbutton({
        onClick: function () {
            $editSpeechWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedSpeech) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除国旗下讲话：<span style='color: red;'>{0}</span>？", selectedSpeech.speechTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/speechmanpage/delete/"+selectedSpeech.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $speechGrid.datagrid('reload');
                }
            })
        })
    }

    /***************************图片上传*********************************************/
    $('#pictureSpeechUploadBtnAdd,#pictureSpeechUploadBtnEdit').linkbutton({
        onClick: function () {
            $pictureSpeechWin.window('open');
        }
    });

    $('#pictureSpeechWinCloseBtn').linkbutton({
        onClick: function () {
            $pictureSpeechWin.window('close');
        }
    });

    var $pictureSpeechWin = $('#pictureSpeechWin').window({
        title: "图片上传",
        closed: true,
        modal: true,
        height: 155,
        width: 400,
        iconCls: 'icon-add',
        collapsible: false,
        minimizable: false,
        footer: '#pictureSpeechWinFooter',
        onClose: function () {

        }
    });

    $('#pictureSpeechWinSubmitBtn').linkbutton({
        onClick: function () {
            var $pictureSpeechForm = $('#pictureSpeechForm');

            $.ajax({
                url: path + "/home/noticemanpage/pictureDetail",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                data : new FormData($pictureSpeechForm[0]),
                success:function (r) {
                    var pictureDiv = $('#addPicture,#editPicture');
                    pictureDiv.empty();

                    for (var i=0;i<r.length;i++){
                        var picture = '<img src="data:image/gif;base64,' + r[i] + '" style="width:100%;height:100%">';
                        pictureDiv.append(picture);
                    }
                    $pictureSpeechWin.window('close');
                }
            });

        }
    });

});
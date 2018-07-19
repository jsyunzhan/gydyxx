$(function () {

    var selectedNotice, reqObj = {};
    var $noticeGrid = $('#noticeGrid').datagrid({
        url: path+ '/home/noticemanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'noticeTitle', title: "公告标题", width: 150, sortable: true,
                align: 'left'
            },
            {
                field: 'noticeDetails', title: "公告内容", width: 400, sortable: true,
                align: 'left'
            }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addNoticeWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedNotice) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editNoticeForm.form('load', selectedNotice);

                        var url = path + "/home/picture/show";
                        $.ajax({
                            url:url,type:"POST",contentType: "application/json",data:JSON.stringify(selectedNotice),
                            success:function (r) {

                                var pictureDiv = $('#editPicture');
                                pictureDiv.empty();

                                for (var i=0;i<r.length;i++){
                                    var picture = '<img src="data:image/gif;base64,' + r[i] + '" style="width:100%;height:100%">';
                                    pictureDiv.append(picture);
                                }

                            }
                        });


                        $editNoticeWin.window('open');
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
            selectedNotice = row;

        },
        onLoadSuccess: function () {
            selectedNotice = $noticeGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/
    var reportEdit = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $addNoticeForm = $('#addNoticeForm').form({
        novalidate: true
    });

    var $addNoticeWin = $('#addNoticeWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addNoticeWinFooter',
        onClose: function () {
            $('#pictureNoticeForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#addNoticeForm').form('disableValidation').form('reset');
            // var ue = UE.getEditor('containerAdd');//初始化对象
            reportEdit.setContent('');
        }
    });

    $('#addNoticeWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addNoticeForm').form('enableValidation').form('validate')) {
                return;
            }

            var noticeData = $addNoticeForm.serializeObject(),
                url = path + "/home/noticemanpage/add";

            console.log(noticeData);

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/通知公告",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureNoticeForm')[0]),
                success:function (r) {
                    noticeData.picturePath = r;

                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(noticeData),
                        success:function (r) {
                            $noticeGrid.datagrid('reload');
                            $addNoticeWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });




        }
    });

    $('#addNoticeWinCloseBtn').linkbutton({
        onClick: function () {
            $addNoticeWin.window('close');
        }
    });



    /*************修改*******************/

    var $editNoticeForm = $('#editNoticeForm').form({
        novalidate: true
    });

    var $editNoticeWin = $('#editNoticeWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#editNoticeWinFooter',
        onClose: function () {
            $('#pictureNoticeForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#editNoticeForm').form('disableValidation').form('reset');
        }
    });

    $('#editNoticeWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editNoticeForm').form('enableValidation').form('validate')) {
                return;
            }

            var noticeData = $editNoticeForm.serializeObject(),
                url = path + "/home/noticemanpage/edit";
            noticeData.id = selectedNotice.id;

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/通知公告",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureNoticeForm')[0]),
                success:function (r) {
                    noticeData.picturePath = r;
                    console.log(r);
                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(noticeData),
                        success:function (r) {
                            $noticeGrid.datagrid('reload');
                            $editNoticeWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });

        }
    });

    $('#editNoticeWinCloseBtn').linkbutton({
        onClick: function () {
            $editNoticeWin.window('close');
        }
    });


    /************删除*************/

    function removeHandle() {
        if (!selectedNotice) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除公告：<span style='color: red;'>{0}</span>？", selectedNotice.noticeTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/noticemanpage/delete/"+selectedNotice.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $noticeGrid.datagrid('reload');
                }
            })
        })
    }

    /***************************图片上传*********************************************/
    $('#pictureNoticeUploadBtnAdd,#pictureNoticeUploadBtnEdit').linkbutton({
        onClick: function () {
            $pictureNoticeWin.window('open');
        }
    });

    $('#pictureNoticeWinCloseBtn').linkbutton({
        onClick: function () {
            $pictureNoticeWin.window('close');
        }
    });

    var $pictureNoticeWin = $('#pictureNoticeWin').window({
        title: "图片上传",
        closed: true,
        modal: true,
        height: 155,
        width: 400,
        iconCls: 'icon-add',
        collapsible: false,
        minimizable: false,
        footer: '#pictureNoticeWinFooter',
        onClose: function () {

        }
    });

    $('#pictureNoticeWinSubmitBtn').linkbutton({
        onClick: function () {
            var $pictureNoticeForm = $('#pictureNoticeForm');

            $.ajax({
                url: path + "/home/noticemanpage/pictureDetail",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                data : new FormData($pictureNoticeForm[0]),
                success:function (r) {
                    var pictureDiv = $('#addPicture,#editPicture');
                    pictureDiv.empty();

                    for (var i=0;i<r.length;i++){
                        var picture = '<img src="data:image/gif;base64,' + r[i] + '" style="width:100%;height:100%">';
                        pictureDiv.append(picture);
                    }
                    $pictureNoticeWin.window('close');
                }
            });

        }
    });


});
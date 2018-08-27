$(function () {

    var selectedHistory, reqObj = {};
    var $historyGrid = $('#historyGrid').datagrid({
        url: path+ '/home/historymanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'historyTitle', title: "校史天地标题", width: 150, sortable: true,
                align: 'left'
            }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addHistoryWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedHistory) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editHistoryForm.form('load', selectedHistory);

                        $(document).ready(function(){
                            var ue = UE.getEditor('containerEdit');
                            ue.ready(function() {//编辑器初始化完成再赋值
                                ue.setContent(selectedHistory.historyDetails);  //赋值给UEditor
                            });
                        });



                        if (selectedHistory.picturePath){
                            var data = {picturePath:selectedHistory.picturePath};
                            var url = path + "/home/picture/show";
                            $.ajax({
                                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(data),
                                success:function (r) {

                                    var pictureDiv = $('#editPicture');
                                    pictureDiv.empty();

                                    for (var i=0;i<r.length;i++){
                                        var picture = '<div style="float:left;width: 20%;margin: 0 2.5%;border: 1px solid #ccc; box-sizing:border-box;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;margin-bottom: 10px;">'
                                        picture += '<img src="data:image/gif;base64,' + r[i] + '" style="height: 100px;">'
                                        picture += '<p style="text-align: center;-webkit-margin-before: 0em;-webkit-margin-after: 0em;line-height: 30px;">'+(i+1)+'</p></div>';
                                        pictureDiv.append(picture);
                                    }

                                }
                            });
                        }


                        $editHistoryWin.window('open');
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
            selectedHistory = row;

        },
        onLoadSuccess: function () {
            selectedHistory = $historyGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var reportAdd = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $addHistoryForm = $('#addHistoryForm').form({
        novalidate: true
    });

    var $addHistoryWin = $('#addHistoryWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addHistoryWinFooter',
        onClose: function () {
            $('#pictureHistoryForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#addHistoryForm').form('disableValidation').form('reset');
            reportAdd.setContent("");
        }
    });

    $('#addHistoryWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addHistoryForm').form('enableValidation').form('validate')) {
                return;
            }

            var historyData = $addHistoryForm.serializeObject(),
                url = path + "/home/historymanpage/add";

            if (!historyData.historyDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/校史天地",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureHistoryForm')[0]),
                success:function (r) {
                    historyData.picturePath = r;
                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(historyData),
                        success:function (r) {
                            $historyGrid.datagrid('reload');
                            $addHistoryWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });




        }
    });

    $('#addHistoryWinCloseBtn').linkbutton({
        onClick: function () {
            $addHistoryWin.window('close');
        }
    });

    /*************修改*******************/

    var reportEdit = UE.getEditor('containerEdit', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $editHistoryForm = $('#editHistoryForm').form({
        novalidate: true
    });

    var $editHistoryWin = $('#editHistoryWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#editHistoryWinFooter',
        onClose: function () {
            $('#pictureHistoryForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#editHistoryForm').form('disableValidation').form('reset');
            reportEdit.setContent("");
        }
    });

    $('#editHistoryWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editHistoryForm').form('enableValidation').form('validate')) {
                return;
            }

            var historyData = $editHistoryForm.serializeObject(),
                url = path + "/home/historymanpage/edit";
            historyData.id = selectedHistory.id;

            if (!historyData.historyDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/校史天地",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureHistoryForm')[0]),
                success:function (r) {
                    historyData.picturePath = r;

                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(historyData),
                        success:function (r) {
                            $historyGrid.datagrid('reload');
                            $editHistoryWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });



        }
    });

    $('#editHistoryWinCloseBtn').linkbutton({
        onClick: function () {
            $editHistoryWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedHistory) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除校史天地：<span style='color: red;'>{0}</span>？", selectedHistory.historyTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/historymanpage/delete/"+selectedHistory.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $historyGrid.datagrid('reload');
                }
            })
        })
    }

    /***************************图片上传*********************************************/
    $('#pictureHistoryUploadBtnAdd,#pictureNewsUploadBtnEdit').linkbutton({
        onClick: function () {
            $pictureHistoryWin.window('open');
        }
    });

    $('#pictureHistoryWinCloseBtn').linkbutton({
        onClick: function () {
            $pictureHistoryWin.window('close');
        }
    });

    var $pictureHistoryWin = $('#pictureHistoryWin').window({
        title: "图片上传",
        closed: true,
        modal: true,
        height: 155,
        width: 400,
        iconCls: 'icon-add',
        collapsible: false,
        minimizable: false,
        footer: '#pictureHistoryWinFooter',
        onClose: function () {

        }
    });

    $('#pictureHistoryWinSubmitBtn').linkbutton({
        onClick: function () {
            var $pictureHistoryForm = $('#pictureHistoryForm');

            $.ajax({
                url: path + "/home/noticemanpage/pictureDetail",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                data : new FormData($pictureHistoryForm[0]),
                success:function (r) {
                    var pictureDiv = $('#addPicture,#editPicture');
                    pictureDiv.empty();

                    for (var i=0;i<r.length;i++){
                        var picture = '<div style="float:left;width: 20%;margin: 0 2.5%;border: 1px solid #ccc; box-sizing:border-box;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;margin-bottom: 10px;">'
                        picture += '<img src="data:image/gif;base64,' + r[i] + '" style="height: 100px;">'
                        picture += '<p style="text-align: center;-webkit-margin-before: 0em;-webkit-margin-after: 0em;line-height: 30px;">'+(i+1)+'</p></div>';
                        pictureDiv.append(picture);
                    }
                    $pictureHistoryWin.window('close');
                }
            });

        }
    });

});
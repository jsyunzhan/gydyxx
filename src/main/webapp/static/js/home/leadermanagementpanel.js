$(function () {

    var selectedLeader, reqObj = {};
    var $leaderGrid = $('#leaderGrid').datagrid({
        url: path+ '/home/leadermanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'leaderTitle', title: "领导简介标题", width: 150, sortable: true,
                align: 'left'
            }
            // , {
            //     field: 'leaderDetails', title: "领导简介内容", width: 400, sortable: true,
            //     align: 'left',hidden:true
            // }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addLeaderWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedLeader) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editLeaderWin.form('load', selectedLeader);

                        $(document).ready(function(){
                            var ue = UE.getEditor('containerEdit');
                            ue.ready(function() {//编辑器初始化完成再赋值
                                ue.setContent(selectedLeader.leaderDetails);  //赋值给UEditor
                            });
                        });

                        if (selectedLeader.picturePath){
                            var data = {picturePath:selectedLeader.picturePath};

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



                        $editLeaderWin.window('open');
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
            selectedLeader = row;

        },
        onLoadSuccess: function () {
            selectedLeader = $leaderGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var reportAdd = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $addLeaderForm = $('#addLeaderForm').form({
        novalidate: true
    });

    var $addLeaderWin = $('#addLeaderWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addLeaderWinFooter',
        onClose: function () {
            $('#pictureLeaderForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#addLeaderForm').form('disableValidation').form('reset');
            reportAdd.setContent('');
        }
    });

    $('#addLeaderWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addMessageForm').form('enableValidation').form('validate')) {
                return;
            }
            if (!$("input[name=file]").val()){
                showErrorMessage("请选择图片！");
                return
            }

            var leaderData = $addLeaderForm.serializeObject(),
                url = path + "/home/leadermanpage/add";

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/领导简介",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureLeaderForm')[0]),
                success:function (r) {
                    leaderData.picturePath = r;
                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(leaderData),
                        success:function (r) {
                            $leaderGrid.datagrid('reload');
                            $addLeaderWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });




        }
    });

    $('#addLeaderWinCloseBtn').linkbutton({
        onClick: function () {
            $addLeaderWin.window('close');
        }
    });

    /*************修改*******************/

    var reportEdit = UE.getEditor('containerEdit', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $editLeaderForm = $('#editLeaderForm').form({
        novalidate: true
    });

    var $editLeaderWin = $('#editLeaderWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editLeaderWinFooter',
        onClose: function () {
            $('#pictureLeaderForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#editLeaderForm').form('disableValidation').form('reset');
            reportEdit.setContent('');
        }
    });

    $('#editLeaderWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editLeaderForm').form('enableValidation').form('validate')) {
                return;
            }
            if (!$("input[name=file]").val()){
                showErrorMessage("请选择图片！");
                return
            }

            var leaderData = $editLeaderForm.serializeObject(),
                url = path + "/home/leadermanpage/edit";
            leaderData.id = selectedLeader.id;


            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/领导简介",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureLeaderForm')[0]),
                success:function (r) {
                    leaderData.picturePath = r;

                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(leaderData),
                        success:function (r) {
                            $leaderGrid.datagrid('reload');
                            $editLeaderWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });



        }
    });

    $('#editLeaderWinCloseBtn').linkbutton({
        onClick: function () {
            $editLeaderWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedLeader) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除领导简介：<span style='color: red;'>{0}</span>？", selectedLeader.leaderTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/leadermanpage/delete/"+selectedLeader.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $leaderGrid.datagrid('reload');
                }
            })
        })
    }

    /***************************图片上传*********************************************/
    $('#pictureLeaderUploadBtnAdd,#pictureLeaderUploadBtnEdit').linkbutton({
        onClick: function () {
            $pictureLeaderWin.window('open');
        }
    });

    $('#pictureLeaderWinCloseBtn').linkbutton({
        onClick: function () {
            $pictureLeaderWin.window('close');
        }
    });

    var $pictureLeaderWin = $('#pictureLeaderWin').window({
        title: "图片上传",
        closed: true,
        modal: true,
        height: 155,
        width: 400,
        iconCls: 'icon-add',
        collapsible: false,
        minimizable: false,
        footer: '#pictureLeaderWinFooter',
        onClose: function () {

        }
    });

    $('#pictureLeaderWinSubmitBtn').linkbutton({
        onClick: function () {
            var $pictureLeaderForm = $('#pictureLeaderForm');

            $.ajax({
                url: path + "/home/noticemanpage/pictureDetail",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                data : new FormData($pictureLeaderForm[0]),
                success:function (r) {
                    var pictureDiv = $('#addPicture,#editPicture');
                    pictureDiv.empty();

                    for (var i=0;i<r.length;i++){
                        var picture = '<img src="data:image/gif;base64,' + r[i] + '" style="width:100%;height:100%">';
                        pictureDiv.append(picture);
                    }
                    $pictureLeaderWin.window('close');
                }
            });

        }
    });

});
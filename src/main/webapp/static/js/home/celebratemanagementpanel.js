$(function () {

    var selectedCelebrate, reqObj = {};
    var $celebrateGrid = $('#celebrateGrid').datagrid({
        url: path+ '/home/celebratemanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'celebrateTitle', title: "校园节庆标题", width: 150, sortable: true,
                align: 'left'
            }
            // ,
            // {
            //     field: 'celebrateDetails', title: "校园节庆内容", width: 400, sortable: true,
            //     align: 'left'
            // }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addCelebrateWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedCelebrate) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editCelebrateForm.form('load', selectedCelebrate);

                        $(document).ready(function(){
                            var ue = UE.getEditor('containerEdit');
                            ue.ready(function() {//编辑器初始化完成再赋值
                                ue.setContent(selectedCelebrate.celebrateDetails);  //赋值给UEditor
                            });
                        });

                        var data = {picturePath:selectedCelebrate.picturePath};

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

                        $editCelebrateWin.window('open');
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
            selectedCelebrate = row;

        },
        onLoadSuccess: function () {
            selectedCelebrate = $celebrateGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var reportAdd = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $addCelebrateForm = $('#addCelebrateForm').form({
        novalidate: true
    });

    var $addCelebrateWin = $('#addCelebrateWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addCelebrateWinFooter',
        onClose: function () {
            $('#pictureCelebrateForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#addCelebrateForm').form('disableValidation').form('reset');
            reportAdd.setContent("")
        }
    });

    $('#addCelebrateWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addCelebrateForm').form('enableValidation').form('validate')) {
                return;
            }

            var celebrateData = $addCelebrateForm.serializeObject(),
                url = path + "/home/celebratemanpage/add";

            if (!celebrateData.celebrateDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            if (!$("input[name=file]").val()){
                showErrorMessage("请选择图片！");
                return
            }

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/校园节庆",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureCelebrateForm')[0]),
                success:function (r) {
                    celebrateData.picturePath = r;
                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(celebrateData),
                        success:function (r) {
                            $celebrateGrid.datagrid('reload');
                            $addCelebrateWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });




        }
    });

    $('#addCelebrateWinCloseBtn').linkbutton({
        onClick: function () {
            $addCelebrateWin.window('close');
        }
    });

    /*************修改*******************/
    var reportEdit = UE.getEditor('containerEdit', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $editCelebrateForm = $('#editCelebrateForm').form({
        novalidate: true
    });

    var $editCelebrateWin = $('#editCelebrateWin').window({
        title: "修过", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editCelebrateWinFooter',
        onClose: function () {
            $('#pictureCelebrateForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#editCelebrateForm').form('disableValidation').form('reset');
            reportEdit.setContent("")
        }
    });

    $('#editCelebrateWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editCelebrateForm').form('enableValidation').form('validate')) {
                return;
            }

            var celebrateData = $editCelebrateForm.serializeObject(),
                url = path + "/home/celebratemanpage/edit";
            celebrateData.id = selectedCelebrate.id;

            if (!celebrateData.celebrateDetails){
                showErrorMessage("正文不可为空！");
                return
            }


            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/校园节庆",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureCelebrateForm')[0]),
                success:function (r) {
                    celebrateData.picturePath = r;

                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(celebrateData),
                        success:function (r) {
                            $celebrateGrid.datagrid('reload');
                            $editCelebrateWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });



        }
    });

    $('#editCelebrateWinCloseBtn').linkbutton({
        onClick: function () {
            $editCelebrateWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedCelebrate) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除校园节庆：<span style='color: red;'>{0}</span>？", selectedCelebrate.celebrateTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/celebratemanpage/delete/"+selectedCelebrate.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $celebrateGrid.datagrid('reload');
                }
            })
        })
    }

    /***************************图片上传*********************************************/
    $('#pictureCelebrateUploadBtnAdd,#pictureCelebrateUploadBtnEdit').linkbutton({
        onClick: function () {
            $pictureCelebrateWin.window('open');
        }
    });

    $('#pictureCelebrateWinCloseBtn').linkbutton({
        onClick: function () {
            $pictureCelebrateWin.window('close');
        }
    });

    var $pictureCelebrateWin = $('#pictureCelebrateWin').window({
        title: "图片上传",
        closed: true,
        modal: true,
        height: 155,
        width: 400,
        iconCls: 'icon-add',
        collapsible: false,
        minimizable: false,
        footer: '#pictureCelebrateWinFooter',
        onClose: function () {

        }
    });

    $('#pictureCelebrateWinSubmitBtn').linkbutton({
        onClick: function () {
            var $pictureCelebrateForm = $('#pictureCelebrateForm');

            $.ajax({
                url: path + "/home/noticemanpage/pictureDetail",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                data : new FormData($pictureCelebrateForm[0]),
                success:function (r) {
                    var pictureDiv = $('#addPicture,#editPicture');
                    pictureDiv.empty();

                    for (var i=0;i<r.length;i++){
                        var picture = '<img src="data:image/gif;base64,' + r[i] + '" style="width:100%;height:100%">';
                        pictureDiv.append(picture);
                    }
                    $pictureCelebrateWin.window('close');
                }
            });

        }
    });

});
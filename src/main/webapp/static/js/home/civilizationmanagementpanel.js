$(function () {

    var selectedCivilization, reqObj = {};
    var $civilizationGrid = $('#civilizationGrid').datagrid({
        url: path+ '/home/civilizationmanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'civilizationTitle', title: "文明创建标题", width: 150, sortable: true,
                align: 'left'
            }
            // ,
            // {
            //     field: 'civilizationDetails', title: "文明创建内容", width: 400, sortable: true,
            //     align: 'left'
            // }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addCiviliztionWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedCivilization) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editCivilizationForm.form('load', selectedCivilization);

                        $(document).ready(function(){
                            var ue = UE.getEditor('containerEdit');
                            ue.ready(function() {//编辑器初始化完成再赋值
                                ue.setContent(selectedCivilization.civilizationDetails);  //赋值给UEditor
                            });
                        });

                        var data = {picturePath:selectedCivilization.picturePath};

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

                        $editCivilizationWin.window('open');
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
            selectedCivilization = row;

        },
        onLoadSuccess: function () {
            selectedCivilization = $civilizationGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var reportAdd = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $addCiviliztionForm = $('#addCiviliztionForm').form({
        novalidate: true
    });

    var $addCiviliztionWin = $('#addCiviliztionWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addCiviliztionWinFooter',
        onClose: function () {
            $('#pictureCiviliztionForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#addCiviliztionForm').form('disableValidation').form('reset');
            reportAdd.setContent("")
        }
    });

    $('#addCiviliztionWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addCiviliztionForm').form('enableValidation').form('validate')) {
                return;
            }

            var civilizationData = $addCiviliztionForm.serializeObject(),
                url = path + "/home/civilizationmanpage/add";

            if (!civilizationData.civilizationDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/文明创建",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureCiviliztionForm')[0]),
                success:function (r) {
                    civilizationData.picturePath = r;
                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(civilizationData),
                        success:function (r) {
                            $civilizationGrid.datagrid('reload');
                            $addCiviliztionWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });


        }
    });

    $('#addCiviliztionWinCloseBtn').linkbutton({
        onClick: function () {
            $addCiviliztionWin.window('close');
        }
    });

    /*************修改*******************/

    var reportEdit = UE.getEditor('containerEdit', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $editCivilizationForm = $('#editCivilizationForm').form({
        novalidate: true
    });

    var $editCivilizationWin = $('#editCivilizationWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editCivilizationWinFooter',
        onClose: function () {
            $('#pictureCiviliztionForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#editCivilizationForm').form('disableValidation').form('reset');
            reportEdit.setContent("")
        }
    });

    $('#editCivilizationWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editCivilizationForm').form('enableValidation').form('validate')) {
                return;
            }

            var editCivilizationData = $editCivilizationForm.serializeObject(),
                url = path + "/home/civilizationmanpage/edit";
            editCivilizationData.id = selectedCivilization.id;

            if (!editCivilizationData.civilizationDetails){
                showErrorMessage("正文不可为空！");
                return
            }


            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/文明创建",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureCiviliztionForm')[0]),
                success:function (r) {
                    editCivilizationData.picturePath = r;

                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(editCivilizationData),
                        success:function (r) {
                            $civilizationGrid.datagrid('reload');
                            $editCivilizationWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });



        }
    });

    $('#editCivilizationWinCloseBtn').linkbutton({
        onClick: function () {
            $editCivilizationWin.window('close');
        }
    });

    function removeHandle() {
        if (!selectedCivilization) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除文明创建概况：<span style='color: red;'>{0}</span>？", selectedCivilization.civilizationTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/civilizationmanpage/delete/"+selectedCivilization.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $civilizationGrid.datagrid('reload');
                }
            })
        })
    }

    /***************************图片上传*********************************************/
    $('#pictureCiviliztionUploadBtnAdd,#pictureCivilizationUploadBtnEdit').linkbutton({
        onClick: function () {
            $pictureCiviliztionWin.window('open');
        }
    });

    $('#pictureCiviliztionWinCloseBtn').linkbutton({
        onClick: function () {
            $pictureCiviliztionWin.window('close');
        }
    });

    var $pictureCiviliztionWin = $('#pictureCiviliztionWin').window({
        title: "图片上传",
        closed: true,
        modal: true,
        height: 155,
        width: 400,
        iconCls: 'icon-add',
        collapsible: false,
        minimizable: false,
        footer: '#pictureCiviliztionWinFooter',
        onClose: function () {

        }
    });

    $('#pictureCiviliztionWinSubmitBtn').linkbutton({
        onClick: function () {
            var $pictureCiviliztionForm = $('#pictureCiviliztionForm');

            $.ajax({
                url: path + "/home/noticemanpage/pictureDetail",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                data : new FormData($pictureCiviliztionForm[0]),
                success:function (r) {
                    var pictureDiv = $('#addPicture,#editPicture');
                    pictureDiv.empty();

                    for (var i=0;i<r.length;i++){
                        var picture = '<div style="float:left;width: 20%;margin: 0 2.5%;border: 1px solid #ccc; box-sizing:border-box;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;margin-bottom: 10px;">'
                        picture += '<img src="data:image/gif;base64,' + r[i] + '" style="height: 100px;">'
                        picture += '<p style="text-align: center;-webkit-margin-before: 0em;-webkit-margin-after: 0em;line-height: 30px;">'+(i+1)+'</p></div>';
                        pictureDiv.append(picture);
                    }
                    $pictureCiviliztionWin.window('close');
                }
            });

        }
    });

});
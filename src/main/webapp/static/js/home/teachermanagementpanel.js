$(function () {

    var selectedTeacher, reqObj = {};
    var $teacherGrid = $('#teacherGrid').datagrid({
        url: path+ '/home/teachermanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'teacherTitle', title: "名师风采标题", width: 150, sortable: true,
                align: 'left'
            }
            // ,
            // {
            //     field: 'teacherDetails', title: "名师风采内容", width: 400, sortable: true,
            //     align: 'left'
            // }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addTeacherWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedTeacher) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editTeacherForm.form('load', selectedTeacher);

                        $(document).ready(function(){
                            var ue = UE.getEditor('containerEdit');
                            ue.ready(function() {//编辑器初始化完成再赋值
                                ue.setContent(selectedTeacher.teacherDetails);  //赋值给UEditor
                            });
                        });

                        var data = {picturePath:selectedTeacher.picturePath};

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

                        $editTeacherWin.window('open');
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
            selectedTeacher = row;

        },
        onLoadSuccess: function () {
            selectedTeacher = $teacherGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var reportAdd = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $addTeacherForm = $('#addTeacherForm').form({
        novalidate: true
    });

    var $addTeacherWin = $('#addTeacherWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addTeacherWinFooter',
        onClose: function () {
            $('#pictureTeacherForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#addTeacherForm').form('disableValidation').form('reset');
            reportAdd.setContent("")
        }
    });

    $('#addTeacherWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addTeacherForm').form('enableValidation').form('validate')) {
                return;
            }

            var teacherData = $addTeacherForm.serializeObject(),
                url = path + "/home/teachermanpage/add";

            if (!$("input[name=file]").val()){
                showErrorMessage("请选择图片！");
                return
            }

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/名师风采",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureTeacherForm')[0]),
                success:function (r) {
                    teacherData.picturePath = r;
                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(teacherData),
                        success:function (r) {
                            $teacherGrid.datagrid('reload');
                            $addTeacherWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });




        }
    });

    $('#addTeacherWinCloseBtn').linkbutton({
        onClick: function () {
            $addTeacherWin.window('close');
        }
    });

    /*************修改*******************/

    var reportEdit = UE.getEditor('containerEdit', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $editTeacherForm = $('#editTeacherForm').form({
        novalidate: true
    });

    var $editTeacherWin = $('#editTeacherWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editTeacherWinFooter',
        onClose: function () {
            $('#pictureTeacherForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#editTeacherForm').form('disableValidation').form('reset');
            reportEdit.setContent("")
        }
    });

    $('#editTeacherWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editTeacherForm').form('enableValidation').form('validate')) {
                return;
            }

            var teacherData = $editTeacherForm.serializeObject(),
                url = path + "/home/teachermanpage/edit";
            teacherData.id = selectedTeacher.id;

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/名师风采",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureTeacherForm')[0]),
                success:function (r) {
                    teacherData.picturePath = r;

                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(teacherData),
                        success:function (r) {
                            $teacherGrid.datagrid('reload');
                            $editTeacherWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });



        }
    });

    $('#editTeacherWinCloseBtn').linkbutton({
        onClick: function () {
            $editTeacherWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedTeacher) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除名师风采：<span style='color: red;'>{0}</span>？", selectedTeacher.teacherTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/teachermanpage/delete/"+selectedTeacher.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $teacherGrid.datagrid('reload');
                }
            })
        })
    }

    /***************************图片上传*********************************************/
    $('#pictureTeacherUploadBtnAdd,#pictureTeacherUploadBtnEdit').linkbutton({
        onClick: function () {
            $pictureTeacherWin.window('open');
        }
    });

    $('#pictureTeacherWinCloseBtn').linkbutton({
        onClick: function () {
            $pictureTeacherWin.window('close');
        }
    });

    var $pictureTeacherWin = $('#pictureTeacherWin').window({
        title: "图片上传",
        closed: true,
        modal: true,
        height: 155,
        width: 400,
        iconCls: 'icon-add',
        collapsible: false,
        minimizable: false,
        footer: '#pictureTeacherWinFooter',
        onClose: function () {

        }
    });

    $('#pictureTeacherWinSubmitBtn').linkbutton({
        onClick: function () {
            var $pictureTeacherForm = $('#pictureTeacherForm');

            $.ajax({
                url: path + "/home/noticemanpage/pictureDetail",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                data : new FormData($pictureTeacherForm[0]),
                success:function (r) {
                    var pictureDiv = $('#addPicture,#editPicture');
                    pictureDiv.empty();

                    for (var i=0;i<r.length;i++){
                        var picture = '<div style="float:left;width: 20%;margin: 0 2.5%;border: 1px solid #ccc; box-sizing:border-box;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;margin-bottom: 10px;">'
                        picture += '<img src="data:image/gif;base64,' + r[i] + '" style="height: 100px;">'
                        picture += '<p style="text-align: center;-webkit-margin-before: 0em;-webkit-margin-after: 0em;line-height: 30px;">'+(i+1)+'</p></div>';
                        pictureDiv.append(picture);
                    }
                    $pictureTeacherWin.window('close');
                }
            });

        }
    });
});
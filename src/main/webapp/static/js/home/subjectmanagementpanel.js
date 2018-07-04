$(function () {

    var selectedSubject, reqObj = {};
    var $subjectGrid = $('#subjectGrid').datagrid({
        url: path+ '/home/subjectmanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'subjectTitle', title: "课题研究标题", width: 150, sortable: true,
                align: 'left'
            },
            {
                field: 'subjectDetails', title: "课题研究内容", width: 400, sortable: true,
                align: 'left'
            }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addSubjectWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedSubject) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editSubjectForm.form('load', selectedSubject);

                        var data = {picturePath:selectedSubject.picturePath};

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

                        $editSubjectWin.window('open');
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
            selectedSubject = row;

        },
        onLoadSuccess: function () {
            selectedSubject = $subjectGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var $addSubjectForm = $('#addSubjectForm').form({
        novalidate: true
    });

    var $addSubjectWin = $('#addSubjectWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addSubjectWinFooter',
        onClose: function () {
            $('#pictureSubjectForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#addSubjectForm').form('disableValidation').form('reset');
        }
    });

    $('#addSubjectWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addSubjectForm').form('enableValidation').form('validate')) {
                return;
            }

            var subjectData = $addSubjectForm.serializeObject(),
                url = path + "/home/subjectmanpage/add";

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/课题研究",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureSubjectForm')[0]),
                success:function (r) {
                    subjectData.picturePath = r;
                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(subjectData),
                        success:function (r) {
                            $subjectGrid.datagrid('reload');
                            $addSubjectWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });




        }
    });

    $('#addSubjectWinCloseBtn').linkbutton({
        onClick: function () {
            $addSubjectWin.window('close');
        }
    });

    /*************修改*******************/

    var $editSubjectForm = $('#editSubjectForm').form({
        novalidate: true
    });

    var $editSubjectWin = $('#editSubjectWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editSubjectWinFooter',
        onClose: function () {
            $('#pictureSubjectForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#editSubjectForm').form('disableValidation').form('reset');
        }
    });

    $('#editSubjectWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editSubjectForm').form('enableValidation').form('validate')) {
                return;
            }

            var subjectData = $editSubjectForm.serializeObject(),
                url = path + "/home/subjectmanpage/edit";
            subjectData.id = selectedSubject.id;


            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/名师风采",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureSubjectForm')[0]),
                success:function (r) {
                    subjectData.picturePath = r;

                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(subjectData),
                        success:function (r) {
                            $subjectGrid.datagrid('reload');
                            $editSubjectWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });



        }
    });

    $('#editSubjectWinCloseBtn').linkbutton({
        onClick: function () {
            $editSubjectWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedSubject) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除课题研究：<span style='color: red;'>{0}</span>？", selectedSubject.subjectTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/subjectmanpage/delete/"+selectedSubject.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $subjectGrid.datagrid('reload');
                }
            })
        })
    }

    /***************************图片上传*********************************************/
    $('#pictureSubjectUploadBtnAdd,#pictureSubjectUploadBtnEdit').linkbutton({
        onClick: function () {
            $pictureSubjectWin.window('open');
        }
    });

    $('#pictureSubjectWinCloseBtn').linkbutton({
        onClick: function () {
            $pictureSubjectWin.window('close');
        }
    });

    var $pictureSubjectWin = $('#pictureSubjectWin').window({
        title: "图片上传",
        closed: true,
        modal: true,
        height: 155,
        width: 400,
        iconCls: 'icon-add',
        collapsible: false,
        minimizable: false,
        footer: '#pictureSubjectWinFooter',
        onClose: function () {

        }
    });

    $('#pictureSubjectWinSubmitBtn').linkbutton({
        onClick: function () {
            var $pictureSubjectForm = $('#pictureSubjectForm');

            $.ajax({
                url: path + "/home/noticemanpage/pictureDetail",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                data : new FormData($pictureSubjectForm[0]),
                success:function (r) {
                    var pictureDiv = $('#addPicture,#editPicture');
                    pictureDiv.empty();

                    for (var i=0;i<r.length;i++){
                        var picture = '<img src="data:image/gif;base64,' + r[i] + '" style="width:100%;height:100%">';
                        pictureDiv.append(picture);
                    }
                    $pictureSubjectWin.window('close');
                }
            });

        }
    });

});

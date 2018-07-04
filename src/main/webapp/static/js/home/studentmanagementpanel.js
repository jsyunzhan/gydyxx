$(function () {

    var selectedStudent, reqObj = {};
    var $studentGrid = $('#studentGrid').datagrid({
        url: path+ '/home/studentmanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'studentTitle', title: "学子风采标题", width: 150, sortable: true,
                align: 'left'
            },
            {
                field: 'studentDetails', title: "学子风采内容", width: 400, sortable: true,
                align: 'left'
            }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addStudentWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedStudent) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editStudentForm.form('load', selectedStudent);

                        var url = path + "/home/picture/show";

                        var data = {picturePath:selectedStudent.picturePath};
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


                        $editStudentWin.window('open');
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
            selectedStudent = row;

        },
        onLoadSuccess: function () {
            selectedStudent = $studentGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var $addStudentForm = $('#addStudentForm').form({
        novalidate: true
    });

    var $addStudentWin = $('#addStudentWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addStudentWinFooter',
        onClose: function () {
            $('#pictureStudentForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#addStudentForm').form('disableValidation').form('reset');
        }
    });

    $('#addStudentWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addStudentForm').form('enableValidation').form('validate')) {
                return;
            }

            var studentData = $addStudentForm.serializeObject(),
                url = path + "/home/studentmanpage/add";

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/学子风采",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureStudentForm')[0]),
                success:function (r) {
                    studentData.picturePath = r;
                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(studentData),
                        success:function (r) {
                            $studentGrid.datagrid('reload');
                            $addStudentWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });




        }
    });

    $('#addStudentWinCloseBtn').linkbutton({
        onClick: function () {
            $addStudentWin.window('close');
        }
    });

    /*************修改*******************/

    var $editStudentForm = $('#editStudentForm').form({
        novalidate: true
    });

    var $editStudentWin = $('#editStudentWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editStudentWinFooter',
        onClose: function () {
            $('#pictureStudentForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#editStudentForm').form('disableValidation').form('reset');
        }
    });

    $('#editStudentWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editStudentForm').form('enableValidation').form('validate')) {
                return;
            }

            var studetData = $editStudentForm.serializeObject(),
                url = path + "/home/studentmanpage/edit";
            studetData.id = selectedStudent.id;


            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/学子风采",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureStudentForm')[0]),
                success:function (r) {
                    studetData.picturePath = r;

                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(studetData),
                        success:function (r) {
                            $studentGrid.datagrid('reload');
                            $editStudentWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });



        }
    });

    $('#editStudentWinCloseBtn').linkbutton({
        onClick: function () {
            $editStudentWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedStudent) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除学子风采：<span style='color: red;'>{0}</span>？", selectedStudent.studentTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/studentmanpage/delete/"+selectedStudent.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $studentGrid.datagrid('reload');
                }
            })
        })
    }

    /***************************图片上传*********************************************/
    $('#pictureStudentUploadBtnAdd,#pictureStudentUploadBtnEdit').linkbutton({
        onClick: function () {
            $pictureStudentWin.window('open');
        }
    });

    $('#pictureStudentWinCloseBtn').linkbutton({
        onClick: function () {
            $pictureStudentWin.window('close');
        }
    });

    var $pictureStudentWin = $('#pictureStudentWin').window({
        title: "图片上传",
        closed: true,
        modal: true,
        height: 155,
        width: 400,
        iconCls: 'icon-add',
        collapsible: false,
        minimizable: false,
        footer: '#pictureStudentWinFooter',
        onClose: function () {

        }
    });

    $('#pictureStudentWinSubmitBtn').linkbutton({
        onClick: function () {
            var $pictureStudentForm = $('#pictureStudentForm');

            $.ajax({
                url: path + "/home/noticemanpage/pictureDetail",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                data : new FormData($pictureStudentForm[0]),
                success:function (r) {
                    var pictureDiv = $('#addPicture,#editPicture');
                    pictureDiv.empty();

                    for (var i=0;i<r.length;i++){
                        var picture = '<img src="data:image/gif;base64,' + r[i] + '" style="width:100%;height:100%">';
                        pictureDiv.append(picture);
                    }
                    $pictureStudentWin.window('close');
                }
            });

        }
    });

});
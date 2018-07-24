$(function () {

    var selectedClassroom, reqObj = {};
    var $classroomGrid = $('#classroomGrid').datagrid({
        url: path+ '/home/classroommanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'classroomTitle', title: "致用课堂标题", width: 150, sortable: true,
                align: 'left'
            }
            // ,
            // {
            //     field: 'classroomDetails', title: "致用课堂内容", width: 400, sortable: true,
            //     align: 'left'
            // }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addClassroomWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedClassroom) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editClassroomForm.form('load', selectedClassroom);

                        $(document).ready(function(){
                            var ue = UE.getEditor('containerEdit');
                            ue.ready(function() {//编辑器初始化完成再赋值
                                ue.setContent(selectedClassroom.classroomDetails);  //赋值给UEditor
                            });
                        });

                        $editClassroomWin.window('open');
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
            selectedClassroom = row;
        },
        onLoadSuccess: function () {
            selectedClassroom = $classroomGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var reportAdd = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $addClassroomForm = $('#addClassroomForm').form({
        novalidate: true
    });

    var $addClassroomWin = $('#addClassroomWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addClassroomWinFooter',
        onClose: function () {
            $('#addClassroomForm').form('disableValidation').form('reset');
            reportAdd.setContent("")
        }
    });

    $('#addClassroomWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addClassroomForm').form('enableValidation').form('validate')) {
                return;
            }

            var classroomData = $addClassroomForm.serializeObject(),
                url = path + "/home/classroommanpage/add";

            if (!classroomData.classroomDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(classroomData),
                success:function (r) {
                    $classroomGrid.datagrid('reload');
                    $addClassroomWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#addClassroomWinCloseBtn').linkbutton({
        onClick: function () {
            $addClassroomWin.window('close');
        }
    });

    /*************修改*******************/

    var reportEdit = UE.getEditor('containerEdit', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $editClassroomForm = $('#editClassroomForm').form({
        novalidate: true
    });

    var $editClassroomWin = $('#editClassroomWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editLawWinFooter',
        onClose: function () {
            reportEdit.setContent("")
            $('#editClassroomForm').form('disableValidation').form('reset');
        }
    });

    $('#editClassroomWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editClassroomForm').form('enableValidation').form('validate')) {
                return;
            }

            var classroomData = $editClassroomForm.serializeObject(),
                url = path + "/home/classroommanpage/edit";
            classroomData.id = selectedClassroom.id;

            if (!classroomData.classroomDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(classroomData),
                success:function (r) {
                    $classroomGrid.datagrid('reload');
                    $editClassroomWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#editClassroomWinCloseBtn').linkbutton({
        onClick: function () {
            $editClassroomWin.window('close');
        }
    });

    function removeHandle() {
        if (!selectedClassroom) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除致用课堂：<span style='color: red;'>{0}</span>？", selectedClassroom.classroomTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/classroommanpage/delete/"+selectedClassroom.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $classroomGrid.datagrid('reload');
                }
            })
        })
    }

});
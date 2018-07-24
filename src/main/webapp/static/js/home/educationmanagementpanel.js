$(function () {

    var selectedEducation, reqObj = {};
    var $educationGrid = $('#educationGrid').datagrid({
        url: path+ '/home/educationmanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'educationTitle', title: "教育科研标题", width: 150, sortable: true,
                align: 'left'
            }
            // ,
            // {
            //     field: 'educationDetails', title: "教育科研内容", width: 400, sortable: true,
            //     align: 'left'
            // }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addEducationWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedEducation) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editEducationForm.form('load', selectedEducation);

                        $(document).ready(function(){
                            var ue = UE.getEditor('containerEdit');
                            ue.ready(function() {//编辑器初始化完成再赋值
                                ue.setContent(selectedEducation.educationDetails);  //赋值给UEditor
                            });
                        });

                        $editEducationWin.window('open');
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
            selectedEducation = row;

        },
        onLoadSuccess: function () {
            selectedEducation = $educationGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var reportAdd = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $addEducationForm = $('#addEducationForm').form({
        novalidate: true
    });

    var $addEducationWin = $('#addEducationWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addEducationWinFooter',
        onClose: function () {
            $('#addEducationForm').form('disableValidation').form('reset');
            reportAdd.setContent("")
        }
    });

    $('#addEducationWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addEducationForm').form('enableValidation').form('validate')) {
                return;
            }

            var educationData = $addEducationForm.serializeObject(),
                url = path + "/home/educationmanpage/add";

            if (!educationData.educationDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(educationData),
                success:function (r) {
                    $educationGrid.datagrid('reload');
                    $addEducationWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#addEducationWinCloseBtn').linkbutton({
        onClick: function () {
            $addEducationWin.window('close');
        }
    });

    /*************修改*******************/

    var reportEdit = UE.getEditor('containerEdit', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $editEducationForm = $('#editEducationForm').form({
        novalidate: true
    });

    var $editEducationWin = $('#editEducationWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editEducationWinFooter',
        onClose: function () {
            $('#editEducationForm').form('disableValidation').form('reset');
            reportEdit.setContent("")
        }
    });

    $('#editEducationWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editEducationForm').form('enableValidation').form('validate')) {
                return;
            }

            var educationData = $editEducationForm.serializeObject(),
                url = path + "/home/educationmanpage/edit";
            educationData.id = selectedEducation.id;

            if (!educationData.educationDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(educationData),
                success:function (r) {
                    $educationGrid.datagrid('reload');
                    $editEducationWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#editEducationWinCloseBtn').linkbutton({
        onClick: function () {
            $editEducationWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedEducation) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除教育科研：<span style='color: red;'>{0}</span>？", selectedEducation.educationTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/educationmanpage/delete/"+selectedEducation.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $educationGrid.datagrid('reload');
                }
            })
        })
    }

});
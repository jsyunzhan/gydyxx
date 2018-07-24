$(function () {

    var selectedTraining, reqObj = {};
    var $trainingGrid = $('#trainingGrid').datagrid({
        url: path+ '/home/trainingmanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'trainingTitle', title: "校本培训标题", width: 150, sortable: true,
                align: 'left'
            }
            // ,
            // {
            //     field: 'trainingDetails', title: "校本培训内容", width: 400, sortable: true,
            //     align: 'left'
            // }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addTrainingWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedTraining) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editTrainingForm.form('load', selectedTraining);
                        $(document).ready(function(){
                            var ue = UE.getEditor('containerEdit');
                            ue.ready(function() {//编辑器初始化完成再赋值
                                ue.setContent(selectedTraining.trainingDetails);  //赋值给UEditor
                            });
                        });
                        $editTrainingWin.window('open');
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
            selectedTraining = row;

        },
        onLoadSuccess: function () {
            selectedTraining = $trainingGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var reportAdd = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $addTrainingForm = $('#addTrainingForm').form({
        novalidate: true
    });

    var $addTrainingWin = $('#addTrainingWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addTrainingWinFooter',
        onClose: function () {
            $('#addTrainingForm').form('disableValidation').form('reset');
            reportAdd.setContent("")
        }
    });

    $('#addTrainingWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addTrainingForm').form('enableValidation').form('validate')) {
                return;
            }

            var trainingData = $addTrainingForm.serializeObject(),
                url = path + "/home/trainingmanpage/add";

            if (!trainingData.trainingDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(trainingData),
                success:function (r) {
                    $trainingGrid.datagrid('reload');
                    $addTrainingWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#addTrainingWinCloseBtn').linkbutton({
        onClick: function () {
            $addTrainingWin.window('close');
        }
    });

    /*************修改*******************/

    var reportEdit = UE.getEditor('containerEdit', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $editTrainingForm = $('#editTrainingForm').form({
        novalidate: true
    });

    var $editTrainingWin = $('#editTrainingWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editTrainingWinFooter',
        onClose: function () {
            reportEdit.setContent("")
            $('#editTrainingForm').form('disableValidation').form('reset');
        }
    });

    $('#editTrainingWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editTrainingForm').form('enableValidation').form('validate')) {
                return;
            }

            var trainingData = $editTrainingForm.serializeObject(),
                url = path + "/home/trainingmanpage/edit";
            trainingData.id = selectedTraining.id;

            if (!trainingData.trainingDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(trainingData),
                success:function (r) {
                    $trainingGrid.datagrid('reload');
                    $editTrainingWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#editTrainingWinCloseBtn').linkbutton({
        onClick: function () {
            $editTrainingWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedTraining) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除校本培训：<span style='color: red;'>{0}</span>？", selectedTraining.trainingTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/trainingmanpage/delete/"+selectedTraining.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $trainingGrid.datagrid('reload');
                }
            })
        })
    }

});
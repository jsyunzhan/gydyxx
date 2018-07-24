$(function () {

    var selectedFar, reqObj = {};
    var $farGrid = $('#farGrid').datagrid({
        url: path+ '/home/farmanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'farTitle', title: "致用工作室标题", width: 150, sortable: true,
                align: 'left'
            }
            // ,
            // {
            //     field: 'farDetails', title: "致用工作室内容", width: 400, sortable: true,
            //     align: 'left'
            // }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addFarWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedFar) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editFarForm.form('load', selectedFar);

                        $(document).ready(function(){
                            var ue = UE.getEditor('containerEdit');
                            ue.ready(function() {//编辑器初始化完成再赋值
                                ue.setContent(selectedFar.farDetails);  //赋值给UEditor
                            });
                        });

                        $editFarWin.window('open');
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
            selectedFar = row;
        },
        onLoadSuccess: function () {
            selectedFar = $farGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var reportAdd = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $addFarForm = $('#addFarForm').form({
        novalidate: true
    });

    var $addFarWin = $('#addFarWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addFarWinFooter',
        onClose: function () {
            $('#addFarForm').form('disableValidation').form('reset');
            reportAdd.setContent("")
        }
    });

    $('#addFarWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addFarForm').form('enableValidation').form('validate')) {
                return;
            }

            var farData = $addFarForm.serializeObject(),
                url = path + "/home/farmanpage/add";

            if (!farData.farDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(farData),
                success:function (r) {
                    $farGrid.datagrid('reload');
                    $addFarWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#addFarWinCloseBtn').linkbutton({
        onClick: function () {
            $addFarWin.window('close');
        }
    });

    /*************修改*******************/
    var reportEdit = UE.getEditor('containerEdit', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $editFarForm = $('#editFarForm').form({
        novalidate: true
    });

    var $editFarWin = $('#editFarWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editFarWinFooter',
        onClose: function () {
            reportEdit.setContent("")
            $('#editFarForm').form('disableValidation').form('reset');
        }
    });

    $('#editFarWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editFarForm').form('enableValidation').form('validate')) {
                return;
            }

            var farData = $editFarForm.serializeObject(),
                url = path + "/home/farmanpage/edit";
            farData.id = selectedFar.id;

            if (!farData.farDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(farData),
                success:function (r) {
                    $farGrid.datagrid('reload');
                    $editFarWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#editFarWinCloseBtn').linkbutton({
        onClick: function () {
            $editFarWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedFar) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }

        var msg = String.format("您确定要删除致远工作室：<span style='color: red;'>{0}</span>？", selectedFar.farTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/farmanpage/delete/"+selectedFar.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $farGrid.datagrid('reload');
                }
            })
        })
    }

});
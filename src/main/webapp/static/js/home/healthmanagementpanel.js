$(function () {

    var selectedHealth, reqObj = {};
    var $healthGrid = $('#healthGrid').datagrid({
        url: path+ '/home/healthmanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'healthTitle', title: "健康教育标题", width: 150, sortable: true,
                align: 'left'
            }
            // ,
            // {
            //     field: 'healthDetails', title: "健康教育内容", width: 400, sortable: true,
            //     align: 'left'
            // }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addHealthWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedHealth) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editHealthForm.form('load', selectedHealth);

                        $(document).ready(function(){
                            var ue = UE.getEditor('containerEdit');
                            ue.ready(function() {//编辑器初始化完成再赋值
                                ue.setContent(selectedHealth.healthDetails);  //赋值给UEditor
                            });
                        });

                        $editHealthWin.window('open');
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
            selectedHealth = row;
        },
        onLoadSuccess: function () {
            selectedHealth = $healthGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/
    var reportAdd = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $addHealthForm = $('#addHealthForm').form({
        novalidate: true
    });

    var $addHealthWin = $('#addHealthWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addHealthWinFooter',
        onClose: function () {
            $('#addHealthForm').form('disableValidation').form('reset');
            reportAdd.setContext("")
        }
    });

    $('#addHealthWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addHealthForm').form('enableValidation').form('validate')) {
                return;
            }

            var healthData = $addHealthForm.serializeObject(),
                url = path + "/home/healthmanpage/add";

            if (!healthData.healthDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(healthData),
                success:function (r) {
                    $healthGrid.datagrid('reload');
                    $addHealthWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#addLawWinCloseBtn').linkbutton({
        onClick: function () {
            $addHealthWin.window('close');
        }
    });

    /*************修改*******************/
    var reportEdit = UE.getEditor('containerEdit', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $editHealthForm = $('#editHealthForm').form({
        novalidate: true
    });

    var $editHealthWin = $('#editHealthWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editHealthWinFooter',
        onClose: function () {
            reportEdit.setContext("")
            $('#editHealthForm').form('disableValidation').form('reset');
        }
    });

    $('#editHealthWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editHealthForm').form('enableValidation').form('validate')) {
                return;
            }

            var healthData = $editHealthForm.serializeObject(),
                url = path + "/home/healthmanpage/edit";
            healthData.id = selectedHealth.id;

            if (!healthData.healthDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(healthData),
                success:function (r) {
                    $healthGrid.datagrid('reload');
                    $editHealthWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#editHealthWinCloseBtn').linkbutton({
        onClick: function () {
            $editHealthWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedHealth) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除健康教育：<span style='color: red;'>{0}</span>？", selectedHealth.healthTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/healthmanpage/delete/"+selectedHealth.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $healthGrid.datagrid('reload');
                }
            })
        })
    }

});
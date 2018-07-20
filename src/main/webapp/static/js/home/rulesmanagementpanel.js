$(function () {

    var selectedRules, reqObj = {};
    var $rulesGrid = $('#rulesGrid').datagrid({
        url: path+ '/home/rulesmanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'rulesTitle', title: "规章制度标题", width: 150, sortable: true,
                align: 'left'
            }
            // ,
            // {
            //     field: 'rulesDetails', title: "规章制度内容", width: 400, sortable: true,
            //     align: 'left'
            // }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addRuleslWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {
                    debugger

                    if (!selectedRules) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editRulesForm.form('load', selectedRules);
                        debugger
                        var str = selectedRules.rulesDetails;

                        var ue = UE.getEditor('containerEdit');

                        ue.setContent(str);  //赋值给UEditor
                        // $(document).ready(function(){
                        //     var ue = UE.getEditor('containerEdit');
                        //     ue.ready(function() {//编辑器初始化完成再赋值
                        //         ue.setContent(selectedRules.rulesDetails);  //赋值给UEditor
                        //     });
                        // });

                        $editRulesWin.window('open');
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
            selectedRules = row;

        },
        onLoadSuccess: function () {
            selectedRules = $rulesGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/
    var reportAdd = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $addRulesForm = $('#addRulesForm').form({
        novalidate: true
    });

    var $addRuleslWin = $('#addRuleslWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addRulesWinFooter',
        onClose: function () {
            $('#addRulesForm').form('disableValidation').form('reset');
            reportAdd.setContent("")
        }
    });

    $('#addRulesWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addWorksForm').form('enableValidation').form('validate')) {
                return;
            }
            debugger
            var rulesData = $addRulesForm.serializeObject(),
                url = path + "/home/rulesmanpage/add";

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(rulesData),
                success:function (r) {
                    $rulesGrid.datagrid('reload');
                    $addRuleslWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#addRulesWinCloseBtn').linkbutton({
        onClick: function () {
            $addRuleslWin.window('close');
        }
    });

    /*************修改*******************/
    var reportEdit = UE.getEditor('containerEdit', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $editRulesForm = $('#editRulesForm').form({
        novalidate: true
    });

    var $editRulesWin = $('#editRulesWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#editRulesWinFooter',
        onClose: function () {
            $('#editRulesForm').form('disableValidation').form('reset');
            reportEdit.setContent("")
        }
    });

    $('#editRulesWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editRulesForm').form('enableValidation').form('validate')) {
                return;
            }

            var rulesData = $editRulesForm.serializeObject(),
                url = path + "/home/rulesmanpage/edit";
            rulesData.id = selectedRules.id;

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(rulesData),
                success:function (r) {
                    $rulesGrid.datagrid('reload');
                    $editRulesWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#editRulesWinCloseBtn').linkbutton({
        onClick: function () {
            $editRulesWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedRules) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除规章制度：<span style='color: red;'>{0}</span>？", selectedRules.rulesTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/rulesmanpage/delete/"+selectedRules.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $rulesGrid.datagrid('reload');
                }
            })
        })
    }

});
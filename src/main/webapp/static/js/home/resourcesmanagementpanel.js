$(function () {

    var selectedResources, reqObj = {};
    var $resourcesGrid = $('#resourcesGrid').datagrid({
        url: path+ '/home/resourcesmanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'resourcesTitle', title: "家校资源标题", width: 150, sortable: true,
                align: 'left'
            }
            // ,
            // {
            //     field: 'resourcesDetails', title: "家校资源内容", width: 400, sortable: true,
            //     align: 'left'
            // }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addResourcesWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedResources) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editResourcesForm.form('load', selectedResources);

                        $(document).ready(function(){
                            var ue = UE.getEditor('containerEdit');
                            ue.ready(function() {//编辑器初始化完成再赋值
                                ue.setContent(selectedResources.resourcesDetails);  //赋值给UEditor
                            });
                        });

                        $editResourcesWin.window('open');
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
            selectedResources = row;
        },
        onLoadSuccess: function () {
            selectedResources = $resourcesGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var reportAdd = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $addResourcesForm = $('#addResourcesForm').form({
        novalidate: true
    });

    var $addResourcesWin = $('#addResourcesWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addResourcesWinFooter',
        onClose: function () {
            $('#addResourcesForm').form('disableValidation').form('reset');
            reportAdd.setContext("")
        }
    });

    $('#addResourcesWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addResourcesForm').form('enableValidation').form('validate')) {
                return;
            }

            var resourcesData = $addResourcesForm.serializeObject(),
                url = path + "/home/resourcesmanpage/add";

            if (!resourcesData.resourcesDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(resourcesData),
                success:function (r) {
                    $resourcesGrid.datagrid('reload');
                    $addResourcesWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#addResourcesWinCloseBtn').linkbutton({
        onClick: function () {
            $addResourcesWin.window('close');
        }
    });

    /*************修改*******************/

    var reportEdit = UE.getEditor('containerEdit', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $editResourcesForm = $('#editResourcesForm').form({
        novalidate: true
    });

    var $editResourcesWin = $('#editResourcesWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editResourcesWinFooter',
        onClose: function () {
            reportEdit.setContext("")
            $('#editResourcesForm').form('disableValidation').form('reset');
        }
    });

    $('#editResourcesWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editResourcesForm').form('enableValidation').form('validate')) {
                return;
            }
            var resourcesData = $editResourcesForm.serializeObject(),
                url = path + "/home/resourcesmanpage/edit";
            resourcesData.id = selectedResources.id;

            if (!resourcesData.resourcesDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(resourcesData),
                success:function (r) {
                    $resourcesGrid.datagrid('reload');
                    $editResourcesWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#editResourcesWinCloseBtn').linkbutton({
        onClick: function () {
            $editResourcesWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedResources) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除家校资源：<span style='color: red;'>{0}</span>？", selectedResources.resourcesTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/resourcesmanpage/delete/"+selectedResources.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $resourcesGrid.datagrid('reload');
                }
            })
        })
    }

});
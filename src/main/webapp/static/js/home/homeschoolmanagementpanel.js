$(function () {

    var selectedHomeschool, reqObj = {};
    var $homeschoolGrid = $('#homeschoolGrid').datagrid({
        url: path+ '/home/homeschoolmanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'homeschoolTitle', title: "家校心桥标题", width: 150, sortable: true,
                align: 'left'
            },
            {
                field: 'homeschoolDetails', title: "家校心桥内容", width: 400, sortable: true,
                align: 'left'
            }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addHomeschoolWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedHomeschool) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editHomeschoolForm.form('load', selectedHomeschool);

                        $editHomeschoolWin.window('open');
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
            selectedHomeschool = row;
        },
        onLoadSuccess: function () {
            selectedHomeschool = $homeschoolGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var $addHomeschoolForm = $('#addHomeschoolForm').form({
        novalidate: true
    });

    var $addHomeschoolWin = $('#addHomeschoolWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addHomeschoolWinFooter',
        onClose: function () {
            $('#addHomeschoolForm').form('disableValidation').form('reset');
        }
    });

    $('#addHomeschoolWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addHomeschoolForm').form('enableValidation').form('validate')) {
                return;
            }

            var homeschoolData = $addHomeschoolForm.serializeObject(),
                url = path + "/home/homeschoolmanpage/add";

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(homeschoolData),
                success:function (r) {
                    $homeschoolGrid.datagrid('reload');
                    $addHomeschoolWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#addHomeschoolWinCloseBtn').linkbutton({
        onClick: function () {
            $addHomeschoolWin.window('close');
        }
    });

    /*************修改*******************/

    var $editHomeschoolForm = $('#editHomeschoolForm').form({
        novalidate: true
    });

    var $editHomeschoolWin = $('#editHomeschoolWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editHomeschoolWinFooter',
        onClose: function () {

            $('#editHomeschoolForm').form('disableValidation').form('reset');
        }
    });

    $('#editHomeschoolWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editHomeschoolForm').form('enableValidation').form('validate')) {
                return;
            }

            var homeschoolData = $editHomeschoolForm.serializeObject(),
                url = path + "/home/homeschoolmanpage/edit";
            homeschoolData.id = selectedHomeschool.id;

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(homeschoolData),
                success:function (r) {
                    $homeschoolGrid.datagrid('reload');
                    $editHomeschoolWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#editHomeschoolWinCloseBtn').linkbutton({
        onClick: function () {
            $editHomeschoolWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedHomeschool) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除家校心桥：<span style='color: red;'>{0}</span>？", selectedHomeschool.homeschoolTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/homeschoolmanpage/delete/"+selectedHomeschool.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $homeschoolGrid.datagrid('reload');
                }
            })
        })
    }

});
$(function () {

    var selectedParty, reqObj = {};
    var $partyGrid = $('#partyGrid').datagrid({
        url: path+ '/home/partymanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'partyTitle', title: "党建工会标题", width: 150, sortable: true,
                align: 'left'
            },
            {
                field: 'partyDetails', title: "党建工会内容", width: 400, sortable: true,
                align: 'left'
            }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addPartyWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedParty) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editPartyForm.form('load', selectedParty);

                        $editPartyWin.window('open');
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
            selectedParty = row;

        },
        onLoadSuccess: function () {
            selectedParty = $partyGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var $addPartyForm = $('#addPartyForm').form({
        novalidate: true
    });

    var $addPartyWin = $('#addPartyWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addPartyWinFooter',
        onClose: function () {
            $('#addPartyForm').form('disableValidation').form('reset');
        }
    });

    $('#addPartyWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addPartyForm').form('enableValidation').form('validate')) {
                return;
            }

            var partyData = $addPartyForm.serializeObject(),
                url = path + "/home/partymanpage/add";

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(partyData),
                success:function (r) {
                    $partyGrid.datagrid('reload');
                    $addPartyWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#addPartyWinCloseBtn').linkbutton({
        onClick: function () {
            $addPartyWin.window('close');
        }
    });

    /*************修改*******************/

    var $editPartyForm = $('#editPartyForm').form({
        novalidate: true
    });

    var $editPartyWin = $('#editPartyWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editPartyWinFooter',
        onClose: function () {

            $('#editPartyForm').form('disableValidation').form('reset');
        }
    });

    $('#editPartyWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editPartyForm').form('enableValidation').form('validate')) {
                return;
            }

            var partyData = $editPartyForm.serializeObject(),
                url = path + "/home/partymanpage/edit";
            partyData.id = selectedParty.id;

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(partyData),
                success:function (r) {
                    $partyGrid.datagrid('reload');
                    $editPartyWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#editPartyWinCloseBtn').linkbutton({
        onClick: function () {
            $editPartyWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedParty) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除党建公告：<span style='color: red;'>{0}</span>？", selectedParty.partyTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/partymanpage/delete/"+selectedParty.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $partyGrid.datagrid('reload');
                }
            })
        })
    }
});
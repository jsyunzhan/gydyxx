$(function () {

    var wagesEntities;
    var selectedWages, reqObj = {};
    var $wagesGrid = $('#wagesGrid').datagrid({
        url: path+ '/wages/wagesmanpage/mainlist', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'wagesName', title: "工资名称 ", width: 150, sortable: true,
                align: 'left'
            },
            {
                field: 'wagesDate', title: "发放日期", width: 150, sortable: true,
                align: 'left',formatter:formatDate
            }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {

                    $addWagesWin.window('open');
                }
            },

            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedCelebrate) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editCelebrateForm.form('load', selectedCelebrate);

                        $editCelebrateWin.window('open');
                    }

                }
            },
            {
                text: "删除", iconCls: 'icon-remove',
                handler: function () {
                    removeHandle();
                }
            },{
                text: "查看详情", iconCls: 'icon-remove',
                handler: function () {

                    if (!selectedWages) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $.ajax({
                            url: path + '/wages/wagesmanpage/details/'+selectedWages.id,
                            type: 'get',
                            dataTye: 'json',
                            success: function (data) {
                                $detailsWagesWin.window('open');
                                console.log(data)
                            }
                        });
                    }
                }
            }
        ],
        onBeforeLoad: function (param) {
            getPage(param);
            $.extend(param, reqObj);
        },
        onSelect: function (index, row) {
            selectedWages = row;

        },
        onLoadSuccess: function () {
            selectedWages = $wagesGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var $addWagesForm = $('#addWagesForm').form({
        novalidate: true
    });

    var $addWagesWin = $('#addWagesWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addWagesWinFooter',
        onClose: function () {
            $('#addWagesForm').form('disableValidation').form('reset');
        }
    });

    $('#addWagesWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addWagesForm').form('enableValidation').form('validate')) {
                return;
            }
            if (!wagesEntities){
                return
            }


            var wagesData = $addWagesForm.serializeObject(),
                url = path + "/wages/wagesmanpage/add";

            var data = {wagesMainEntity:wagesData,wagesEntities:wagesEntities};



            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(data),
                success:function (r) {
                    $wagesGrid.datagrid('reload');
                    $addWagesWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#addWagesWinCloseBtn').linkbutton({
        onClick: function () {
            $addWagesWin.window('close');
        }
    });

    /********************************查看详情窗口*********************************************/

    var $detailsWagesWin = $('#detailsWagesWin').window({
        title: "查看详情", closed: true, modal: true, height: 600,
        width: 900, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#detailsWinFooter',
        onClose: function () {

        }
    });



    $('#detailsWinCloseBtn').linkbutton({
        onClick: function () {
            $detailsWagesWin.window('close');
        }
    });

    /***************************表格上传*********************************************/
    $('#wagesExcelBtn').linkbutton({
        onClick: function () {
            $wagesExcelWin.window('open');
        }
    });

    $('#wagesExcelWinCloseBtn').linkbutton({
        onClick: function () {
            $wagesExcelWin.window('close');
        }
    });

    var $wagesExcelWin = $('#wagesExcelWin').window({
        title: "表格上传",
        closed: true,
        modal: true,
        height: 155,
        width: 400,
        iconCls: 'icon-add',
        collapsible: false,
        minimizable: false,
        footer: '#wagesExcelWinFooter',
        onClose: function () {

        }
    });

    $('#wagesExcelWinSubmitBtn').linkbutton({
        onClick: function () {
            var $wagesExcelForm = $('#wagesExcelForm');

            $.ajax({
                url: path + "/wages/wagesmanpage/import",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                data : new FormData($wagesExcelForm[0]),
                success:function (r) {
                    if (r.success){
                        $wagesExcelWin.window('close');
                        wagesEntities = r.wagesEntityList;
                    }else {
                        showErrorMessage(r.reason)
                    }
                }
            });

        }
    });

});
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

                    if (!selectedWages) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {

                        selectedWages.wagesDate = formatDate(selectedWages.wagesDate);
                        $editWagesForm.form('load', selectedWages);
                        $editWagesWin.window('open');
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
                                console.log(data);
                                var data01 = data[0].wagesdetails.split(";");
                                var _html = '';
                                _html += '<div class="pay"><div class="payNum"><p title="姓名">姓名</p></div>';
                                for(var j=0;j<data01.length-1;j++){
                                    var data02 = data01[j].split(":");
                                    _html += '<div class="payNum"><p title="'+data02[0]+'">'+data02[0]+'</p></div>';
                                }
                                for(var i=0;i<data.length;i++){
                                    _html += '<div class="pay"><div class="payNum"><p>'+data[i].accountName+'</p></div>'
                                    for(var j=0;j<data01.length-1;j++){
                                        var data02 = data01[j].split(":");
                                        _html += '<div class="payNum"><p>'+data02[1]+'</p></div>';
                                    }
                                    _html += '</div>';
                                }
                                _html += '</div>';
                                $("#detailsWagesWin").append(_html);
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
                showWarningMessage("请上传Excel表格！");
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

    /*************修改*******************/

    var $editWagesForm = $('#editWagesForm').form({
        novalidate: true
    });

    var $editWagesWin = $('#editWagesWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editWagesWinFooter',
        onClose: function () {
            $('#editWagesForm').form('disableValidation').form('reset');
        }
    });

    $('#editWagesWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editWagesForm').form('enableValidation').form('validate')) {
                return;
            }

            var wagesData = $editWagesForm.serializeObject(),
                url = path + "/wages/wagesmanpage/edit";
            wagesData.id = selectedWages.id;
            var data = {wagesMainEntity:wagesData,wagesEntities:wagesEntities};

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(data),
                success:function (r) {
                    $wagesGrid.datagrid('reload');
                    $editWagesWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#editWagesWinCloseBtn').linkbutton({
        onClick: function () {
            $editWagesWin.window('close');
        }
    });

    /********************************查看详情窗口*********************************************/

    var $detailsWagesWin = $('#detailsWagesWin').window({
        title: "查看详情", closed: true, modal: true, height: 600,
        width: 900, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#detailsWinFooter',
        onClose: function () {
            $(".pay").remove();
        }
    });



    $('#detailsWinCloseBtn').linkbutton({
        onClick: function () {
            $detailsWagesWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedWages) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除工资：<span style='color: red;'>{0}</span>？", selectedWages.wagesName);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/wages/wagesmanpage/delete/"+selectedWages.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $wagesGrid.datagrid('reload');
                }
            })
        })
    }

    /***************************表格上传*********************************************/
    $('#wagesExcelBtn,#wagesExcelBtnEdit').linkbutton({
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
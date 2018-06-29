$(function () {

    var selectedNews, reqObj = {};
    var $newsGrid = $('#newsGrid').datagrid({
        url: path+ '/home/newsmanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'newsTitle', title: "新闻标题", width: 150, sortable: true,
                align: 'left'
            },
            {
                field: 'newsDetails', title: "新闻内容", width: 400, sortable: true,
                align: 'left'
            }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addNewsWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedNews) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editNewsForm.form('load', selectedNews);
                        $editNewsWin.window('open');
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
            selectedNews = row;

        },
        onLoadSuccess: function () {
            selectedNews = $newsGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var $addNewsForm = $('#addNewsForm').form({
        novalidate: true
    });

    var $addNewsWin = $('#addNewsWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addNewsWinFooter',
        onClose: function () {
            $('#addNewsForm').form('disableValidation').form('reset');
        }
    });

    $('#addNewsWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addMessageForm').form('enableValidation').form('validate')) {
                return;
            }

            var newsData = $addNewsForm.serializeObject(),
                url = path + "/home/newsmanpage/add";


            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(newsData),
                success:function (r) {
                    $newsGrid.datagrid('reload');
                    $addNewsWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#addNewsWinCloseBtn').linkbutton({
        onClick: function () {
            $addNewsWin.window('close');
        }
    });

    /*************修改*******************/

    var $editNewsForm = $('#editNewsForm').form({
        novalidate: true
    });

    var $editNewsWin = $('#editNewsWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#editNewsWinFooter',
        onClose: function () {
            $('#editNewsForm').form('disableValidation').form('reset');
        }
    });

    $('#editNewsWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editNewsForm').form('enableValidation').form('validate')) {
                return;
            }

            var newsData = $editNewsForm.serializeObject(),
                url = path + "/home/newsmanpage/edit";
            newsData.id = selectedNews.id;
            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(newsData),
                success:function (r) {
                    $newsGrid.datagrid('reload');
                    $editNewsWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#editNewsWinCloseBtn').linkbutton({
        onClick: function () {
            $editNewsWin.window('close');
        }
    });

});
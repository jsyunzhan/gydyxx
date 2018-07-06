$(function () {

    var selectedCivilization, reqObj = {};
    var $civilizationGrid = $('#civilizationGrid').datagrid({
        url: path+ '/home/civilizationmanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'civilizationTitle', title: "文明创建标题", width: 150, sortable: true,
                align: 'left'
            },
            {
                field: 'civilizationDetails', title: "文明创建内容", width: 400, sortable: true,
                align: 'left'
            }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addCiviliztionWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedNews) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editNewsForm.form('load', selectedNews);

                        var data = {picturePath:selectedNews.picturePath};

                        var url = path + "/home/picture/show";
                        $.ajax({
                            url:url,type:"POST",contentType: "application/json",data:JSON.stringify(data),
                            success:function (r) {

                                var pictureDiv = $('#editPicture');
                                pictureDiv.empty();

                                for (var i=0;i<r.length;i++){
                                    var picture = '<img src="data:image/gif;base64,' + r[i] + '" style="width:100%;height:100%">';
                                    pictureDiv.append(picture);
                                }

                            }
                        });

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
            selectedCivilization = row;

        },
        onLoadSuccess: function () {
            selectedCivilization = $civilizationGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var $addCiviliztionForm = $('#addCiviliztionForm').form({
        novalidate: true
    });

    var $addCiviliztionWin = $('#addCiviliztionWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addCiviliztionWinFooter',
        onClose: function () {
            $('#pictureCiviliztionForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#addCiviliztionForm').form('disableValidation').form('reset');
        }
    });

    $('#addCiviliztionWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addCiviliztionForm').form('enableValidation').form('validate')) {
                return;
            }

            var civilizationData = $addCiviliztionForm.serializeObject(),
                url = path + "/home/civilizationmanpage/add";

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/文明创建",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureCiviliztionForm')[0]),
                success:function (r) {
                    civilizationData.picturePath = r;
                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(civilizationData),
                        success:function (r) {
                            $civilizationGrid.datagrid('reload');
                            $addCiviliztionWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });


        }
    });

    $('#addCiviliztionWinCloseBtn').linkbutton({
        onClick: function () {
            $addCiviliztionWin.window('close');
        }
    });

    /***************************图片上传*********************************************/
    $('#pictureCiviliztionUploadBtnAdd,#pictureNewsUploadBtnEdit').linkbutton({
        onClick: function () {
            $pictureCiviliztionWin.window('open');
        }
    });

    $('#pictureCiviliztionWinCloseBtn').linkbutton({
        onClick: function () {
            $pictureCiviliztionWin.window('close');
        }
    });

    var $pictureCiviliztionWin = $('#pictureCiviliztionWin').window({
        title: "图片上传",
        closed: true,
        modal: true,
        height: 155,
        width: 400,
        iconCls: 'icon-add',
        collapsible: false,
        minimizable: false,
        footer: '#pictureCiviliztionWinFooter',
        onClose: function () {

        }
    });

    $('#pictureCiviliztionWinSubmitBtn').linkbutton({
        onClick: function () {
            var $pictureCiviliztionForm = $('#pictureCiviliztionForm');

            $.ajax({
                url: path + "/home/noticemanpage/pictureDetail",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                data : new FormData($pictureCiviliztionForm[0]),
                success:function (r) {
                    var pictureDiv = $('#addPicture,#editPicture');
                    pictureDiv.empty();

                    for (var i=0;i<r.length;i++){
                        var picture = '<img src="data:image/gif;base64,' + r[i] + '" style="width:100%;height:100%">';
                        pictureDiv.append(picture);
                    }
                    $pictureCiviliztionWin.window('close');
                }
            });

        }
    });

});
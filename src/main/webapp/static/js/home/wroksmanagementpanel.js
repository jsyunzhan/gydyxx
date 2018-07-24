$(function () {

    var selectedWorks, reqObj = {};
    var $worksGrid = $('#worksGrid').datagrid({
        url: path+ '/home/worksmanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'worksTitle', title: "作品标题", width: 150, sortable: true,
                align: 'left'
            }
            // ,
            // {
            //     field: 'worksDetails', title: "作品内容", width: 400, sortable: true,
            //     align: 'left'
            // }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addWorksWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedWorks) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editWorksForm.form('load', selectedWorks);

                        if (selectedWorks.worksDetails){
                            $(document).ready(function(){
                                var ue = UE.getEditor('containerEdit');
                                ue.ready(function() {//编辑器初始化完成再赋值
                                    ue.setContent(selectedWorks.worksDetails);  //赋值给UEditor
                                });
                            });
                        }

                        var data = {picturePath:selectedWorks.picturePath};

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

                        $editWorksWin.window('open');
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
            selectedWorks = row;

        },
        onLoadSuccess: function () {
            selectedWorks = $worksGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var reportAdd = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $addWorksForm = $('#addWorksForm').form({
        novalidate: true
    });

    var $addWorksWin = $('#addWorksWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addWorksWinFooter',
        onClose: function () {
            $('#pictureWorksForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#addWorksForm').form('disableValidation').form('reset');
            reportAdd.setContext("")
        }
    });

    $('#addWorksWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addWorksForm').form('enableValidation').form('validate')) {
                return;
            }

            if (!$("input[name=file]").val()){
                showErrorMessage("请选择图片！");
                return
            }

            var worksData = $addWorksForm.serializeObject(),
                url = path + "/home/worksmanpage/add";


            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/作品展示",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureWorksForm')[0]),
                success:function (r) {
                    worksData.picturePath = r;
                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(worksData),
                        success:function (r) {
                            $worksGrid.datagrid('reload');
                            $addWorksWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });




        }
    });

    $('#addWorksWinCloseBtn').linkbutton({
        onClick: function () {
            $addWorksWin.window('close');
        }
    });

    /*************修改*******************/
    var reportEdit = UE.getEditor('containerEdit', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $editWorksForm = $('#editWorksForm').form({
        novalidate: true
    });

    var $editWorksWin = $('#editWorksWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#editWorksWinFooter',
        onClose: function () {
            $('#pictureWorksForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#editWorksForm').form('disableValidation').form('reset');
            reportEdit.setContext("")
        }
    });

    $('#editWorksWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editWorksForm').form('enableValidation').form('validate')) {
                return;
            }

            var worksData = $editWorksForm.serializeObject(),
                url = path + "/home/worksmanpage/edit";
            worksData.id = selectedWorks.id;

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/作品展示",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureWorksForm')[0]),
                success:function (r) {
                    worksData.picturePath = r;
                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(worksData),
                        success:function (r) {
                            $worksGrid.datagrid('reload');
                            $editWorksWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });



        }
    });

    $('#editWorksWinCloseBtn').linkbutton({
        onClick: function () {
            $editWorksWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedWorks) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除作品：<span style='color: red;'>{0}</span>？", selectedWorks.worksTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/worksmanpage/delete/"+selectedWorks.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $worksGrid.datagrid('reload');
                }
            })
        })
    }

    /***************************图片上传*********************************************/
    $('#pictureWorksUploadBtnAdd,#pictureWorksUploadBtnEdit').linkbutton({
        onClick: function () {
            $pictureWorksWin.window('open');
        }
    });

    $('#pictureWorksWinCloseBtn').linkbutton({
        onClick: function () {
            $pictureWorksWin.window('close');
        }
    });

    var $pictureWorksWin = $('#pictureWorksWin').window({
        title: "图片上传",
        closed: true,
        modal: true,
        height: 155,
        width: 400,
        iconCls: 'icon-add',
        collapsible: false,
        minimizable: false,
        footer: '#pictureWorksWinFooter',
        onClose: function () {

        }
    });

    $('#pictureWorksWinSubmitBtn').linkbutton({
        onClick: function () {
            var $pictureWorksForm = $('#pictureWorksForm');

            $.ajax({
                url: path + "/home/noticemanpage/pictureDetail",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                data : new FormData($pictureWorksForm[0]),
                success:function (r) {
                    var pictureDiv = $('#addPicture,#editPicture');
                    pictureDiv.empty();

                    for (var i=0;i<r.length;i++){
                        var picture = '<img src="data:image/gif;base64,' + r[i] + '" style="width:100%;height:100%">';
                        pictureDiv.append(picture);
                    }
                    $pictureWorksWin.window('close');
                }
            });

        }
    });

});
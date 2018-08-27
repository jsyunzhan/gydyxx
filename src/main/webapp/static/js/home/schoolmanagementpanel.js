$(function () {

    var selectedSchool, reqObj = {};
    var $schoolGrid = $('#schoolGrid').datagrid({
        url: path+ '/home/schoolmanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'schoolTitle', title: "学校风采标题", width: 150, sortable: true,
                align: 'left'
            }
            // ,
            // {
            //     field: 'schoolDetails', title: "学校风采内容", width: 400, sortable: true,
            //     align: 'left'
            // }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addSchoolWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedSchool) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editSchoolForm.form('load', selectedSchool);

                        if (selectedSchool.schoolDetails){

                            $(document).ready(function(){
                                var ue = UE.getEditor('containerEdit');
                                ue.ready(function() {//编辑器初始化完成再赋值
                                    ue.setContent(selectedSchool.schoolDetails);  //赋值给UEditor
                                });
                            });

                        }



                        var data = {picturePath:selectedSchool.picturePath};

                        var url = path + "/home/picture/show";
                        $.ajax({
                            url:url,type:"POST",contentType: "application/json",data:JSON.stringify(data),
                            success:function (r) {

                                var pictureDiv = $('#editPicture');
                                pictureDiv.empty();

                                for (var i=0;i<r.length;i++){
                                    var picture = '<div style="float:left;width: 20%;margin: 0 2.5%;border: 1px solid #ccc; box-sizing:border-box;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;margin-bottom: 10px;">'
                                    picture += '<img src="data:image/gif;base64,' + r[i] + '" style="height: 100px;">'
                                    picture += '<p style="text-align: center;-webkit-margin-before: 0em;-webkit-margin-after: 0em;line-height: 30px;">'+(i+1)+'</p></div>';
                                    pictureDiv.append(picture);
                                }

                            }
                        });

                        $editSchoolWin.window('open');
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
            selectedSchool = row;

        },
        onLoadSuccess: function () {
            selectedSchool = $schoolGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var reportAdd = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $addSchoolForm = $('#addSchoolForm').form({
        novalidate: true
    });

    var $addSchoolWin = $('#addSchoolWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addSchoolWinFooter',
        onClose: function () {
            $('#pictureSchoolForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#addSchoolForm').form('disableValidation').form('reset');
            reportAdd.setContent("")
        }
    });

    $('#addSchoolWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addSchoolForm').form('enableValidation').form('validate')) {
                return;
            }

            if (!$("input[name=file]").val()){
                showErrorMessage("请选择图片！");
                return
            }

            var schoolData = $addSchoolForm.serializeObject(),
                url = path + "/home/schoolmanpage/add";

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/学校风采",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureSchoolForm')[0]),
                success:function (r) {
                    schoolData.picturePath = r;
                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(schoolData),
                        success:function (r) {
                            $schoolGrid.datagrid('reload');
                            $addSchoolWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });




        }
    });

    $('#addSchoolWinCloseBtn').linkbutton({
        onClick: function () {
            $addSchoolWin.window('close');
        }
    });

    /*************修改*******************/

    var reportEdit = UE.getEditor('containerEdit', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $editSchoolForm = $('#editSchoolForm').form({
        novalidate: true
    });

    var $editSchoolWin = $('#editSchoolWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editSchoolWinFooter',
        onClose: function () {
            $('#pictureSchoolForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#editSchoolForm').form('disableValidation').form('reset');
            reportEdit.setContent("")
        }
    });

    $('#editSchoolWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editSchoolForm').form('enableValidation').form('validate')) {
                return;
            }

            var schoolData = $editSchoolForm.serializeObject(),
                url = path + "/home/schoolmanpage/edit";
            schoolData.id = selectedSchool.id;


            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/学校风采",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureSchoolForm')[0]),
                success:function (r) {
                    schoolData.picturePath = r;

                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(schoolData),
                        success:function (r) {
                            $schoolGrid.datagrid('reload');
                            $editSchoolWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });



        }
    });

    $('#editSchoolWinCloseBtn').linkbutton({
        onClick: function () {
            $editSchoolWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedSchool) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除学校风采：<span style='color: red;'>{0}</span>？", selectedSchool.schoolTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/schoolmanpage/delete/"+selectedSchool.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $schoolGrid.datagrid('reload');
                }
            })
        })
    }

    /***************************图片上传*********************************************/
    $('#pictureSchoolUploadBtnAdd,#pictureSchoolUploadBtnEdit').linkbutton({
        onClick: function () {
            $pictureSchoolWin.window('open');
        }
    });

    $('#pictureSchoolWinCloseBtn').linkbutton({
        onClick: function () {
            $pictureSchoolWin.window('close');
        }
    });

    var $pictureSchoolWin = $('#pictureSchoolWin').window({
        title: "图片上传",
        closed: true,
        modal: true,
        height: 155,
        width: 400,
        iconCls: 'icon-add',
        collapsible: false,
        minimizable: false,
        footer: '#pictureSchoolWinFooter',
        onClose: function () {

        }
    });

    $('#pictureSchoolWinSubmitBtn').linkbutton({
        onClick: function () {
            var $pictureSchoolForm = $('#pictureSchoolForm');

            $.ajax({
                url: path + "/home/noticemanpage/pictureDetail",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                data : new FormData($pictureSchoolForm[0]),
                success:function (r) {
                    var pictureDiv = $('#addPicture,#editPicture');
                    pictureDiv.empty();

                    for (var i=0;i<r.length;i++){
                        var picture = '<div style="float:left;width: 20%;margin: 0 2.5%;border: 1px solid #ccc; box-sizing:border-box;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;margin-bottom: 10px;">'
                        picture += '<img src="data:image/gif;base64,' + r[i] + '" style="height: 100px;">'
                        picture += '<p style="text-align: center;-webkit-margin-before: 0em;-webkit-margin-after: 0em;line-height: 30px;">'+(i+1)+'</p></div>';
                        pictureDiv.append(picture);
                    }
                    $pictureSchoolWin.window('close');
                }
            });

        }
    });

});
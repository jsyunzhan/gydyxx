$(function () {

    var selectedCourse, reqObj = {};
    var $courseGrid = $('#courseGrid').datagrid({
        url: path+ '/home/coursemanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'courseTitle', title: "班本课程标题", width: 150, sortable: true,
                align: 'left'
            }
            // ,
            // {
            //     field: 'courseDetails', title: "班本课程内容", width: 400, sortable: true,
            //     align: 'left'
            // }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addCourseWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedCourse) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editCourseForm.form('load', selectedCourse);

                        $(document).ready(function(){
                            var ue = UE.getEditor('containerEdit');
                            ue.ready(function() {//编辑器初始化完成再赋值
                                ue.setContent(selectedCourse.courseDetails);  //赋值给UEditor
                            });
                        });

                        var data = {picturePath:selectedCourse.picturePath};

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

                        $editCourseWin.window('open');
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
            selectedCourse = row;

        },
        onLoadSuccess: function () {
            selectedCourse = $courseGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/
    var reportAdd = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $addCourseForm = $('#addCourseForm').form({
        novalidate: true
    });

    var $addCourseWin = $('#addCourseWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addCourseWinFooter',
        onClose: function () {
            $('#pictureCourseForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#addCourseForm').form('disableValidation').form('reset');
            reportAdd.setContent("")
        }
    });

    $('#addCourseWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addCourseForm').form('enableValidation').form('validate')) {
                return;
            }

            var newsData = $addCourseForm.serializeObject(),
                url = path + "/home/coursemanpage/add";

            if (!newsData.courseDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            if (!$("input[name=file]").val()){
                showErrorMessage("请选择图片！");
                return
            }

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/班本课程",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureCourseForm')[0]),
                success:function (r) {
                    newsData.picturePath = r;
                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(newsData),
                        success:function (r) {
                            $courseGrid.datagrid('reload');
                            $addCourseWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });




        }
    });

    $('#addCourseWinCloseBtn').linkbutton({
        onClick: function () {
            $addCourseWin.window('close');
        }
    });

    /*************修改*******************/
    var reportEdit = UE.getEditor('containerEdit', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $editCourseForm = $('#editCourseForm').form({
        novalidate: true
    });

    var $editCourseWin = $('#editCourseWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editCourseWinFooter',
        onClose: function () {
            $('#pictureCourseForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#editCourseForm').form('disableValidation').form('reset');
            reportEdit.setContent("")
        }
    });

    $('#editCourseWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editCourseForm').form('enableValidation').form('validate')) {
                return;
            }

            var courseData = $editCourseForm.serializeObject(),
                url = path + "/home/coursemanpage/edit";
            courseData.id = selectedCourse.id;

            if (!courseData.courseDetails){
                showErrorMessage("正文不可为空！");
                return
            }


            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/班本课程",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureCourseForm')[0]),
                success:function (r) {
                    courseData.picturePath = r;

                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(courseData),
                        success:function (r) {
                            $courseGrid.datagrid('reload');
                            $editCourseWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });



        }
    });

    $('#editCourseWinCloseBtn').linkbutton({
        onClick: function () {
            $editCourseWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedCourse) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除班本课程：<span style='color: red;'>{0}</span>？", selectedCourse.courseTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/coursemanpage/delete/"+selectedCourse.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $courseGrid.datagrid('reload');
                }
            })
        })
    }

    /***************************图片上传*********************************************/
    $('#pictureCourseUploadBtnAdd,#pictureCourseUploadBtnEdit').linkbutton({
        onClick: function () {
            $pictureCourseWin.window('open');
        }
    });

    $('#pictureCourseWinCloseBtn').linkbutton({
        onClick: function () {
            $pictureCourseWin.window('close');
        }
    });

    var $pictureCourseWin = $('#pictureCourseWin').window({
        title: "图片上传",
        closed: true,
        modal: true,
        height: 155,
        width: 400,
        iconCls: 'icon-add',
        collapsible: false,
        minimizable: false,
        footer: '#pictureCourseWinFooter',
        onClose: function () {

        }
    });

    $('#pictureCourseWinSubmitBtn').linkbutton({
        onClick: function () {
            var $pictureCourseForm = $('#pictureCourseForm');

            $.ajax({
                url: path + "/home/noticemanpage/pictureDetail",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                data : new FormData($pictureCourseForm[0]),
                success:function (r) {
                    var pictureDiv = $('#addPicture,#editPicture');
                    pictureDiv.empty();

                    for (var i=0;i<r.length;i++){
                        var picture = '<img src="data:image/gif;base64,' + r[i] + '" style="width:100%;height:100%">';
                        pictureDiv.append(picture);
                    }
                    $pictureCourseWin.window('close');
                }
            });

        }
    });

});
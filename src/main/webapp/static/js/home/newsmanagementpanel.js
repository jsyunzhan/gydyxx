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
                field: 'mainFlag', title: "是否主标题", width: 150, sortable: true,
                align: 'left',formatter:result
            }, {
                field: 'changeFlag', title: "是否轮播图", width: 150, sortable: true,
                align: 'left',formatter:result
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

                        $(document).ready(function(){
                            var ue = UE.getEditor('containerEdit');
                            ue.ready(function() {//编辑器初始化完成再赋值
                                ue.setContent(selectedNews.newsDetails);  //赋值给UEditor
                            });
                        });



                        if (selectedNews.picturePath){
                            var data = {picturePath:selectedNews.picturePath};
                            var url = path + "/home/picture/show";
                            $.ajax({
                                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(data),
                                success:function (r) {

                                    var pictureDiv = $('#editPicture');
                                    pictureDiv.empty();

                                    for (var i=0;i<r.length;i++){
                                        var picture = '<div style="float:left;width: 20%;margin: 0 2.5%;border: 1px solid #ccc; box-sizing:border-box;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;margin-bottom: 10px;">'
                                        picture += '<img src="data:image/gif;base64,' + r[i] + '" style="height: 100px;">'
                                        picture += '<p style="text-align: center;-webkit-margin-before: 0em;-webkit-margin-after: 0em;line-height: 30px;">'+i+'</p></div>';
                                        pictureDiv.append(picture);
                                    }

                                }
                            });
                        }


                        $editNewsWin.window('open');
                    }

                }
            },
            {
                text: "删除", iconCls: 'icon-remove',
                handler: function () {
                    removeHandle();
                }
            },{
                text: "设为主标题", iconCls: 'icon-remove',
                handler: function () {
                    setMain();
                }
            },
            {
                text: "取消主标题", iconCls: 'icon-remove',
                handler: function () {
                    cancelMain();
                }
            },
            {
                text: "设为轮播图", iconCls: 'icon-remove',
                handler: function () {
                    setChange();
                }
            },
            {
                text: "取消轮播图", iconCls: 'icon-remove',
                handler: function () {
                    cancelChange();
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

    var reportAdd = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $addNewsForm = $('#addNewsForm').form({
        novalidate: true
    });

    var $addNewsWin = $('#addNewsWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addNewsWinFooter',
        onClose: function () {
            $('#pictureNewsForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#addNewsForm').form('disableValidation').form('reset');
            reportAdd.setContent("");
        }
    });

    $('#addNewsWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addNewsForm').form('enableValidation').form('validate')) {
                return;
            }

            var newsData = $addNewsForm.serializeObject(),
                url = path + "/home/newsmanpage/add";

            if (!newsData.newsDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/新闻中心",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureNewsForm')[0]),
                success:function (r) {
                    newsData.picturePath = r;
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




        }
    });

    $('#addNewsWinCloseBtn').linkbutton({
        onClick: function () {
            $addNewsWin.window('close');
        }
    });

    /*************修改*******************/

    var reportEdit = UE.getEditor('containerEdit', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $editNewsForm = $('#editNewsForm').form({
        novalidate: true
    });

    var $editNewsWin = $('#editNewsWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#editNewsWinFooter',
        onClose: function () {
            $('#pictureNewsForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#editNewsForm').form('disableValidation').form('reset');
            reportEdit.setContent("");
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

            if (!newsData.newsDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/新闻中心",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureNewsForm')[0]),
                success:function (r) {
                    newsData.picturePath = r;

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



        }
    });

    $('#editNewsWinCloseBtn').linkbutton({
        onClick: function () {
            $editNewsWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedNews) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除新闻：<span style='color: red;'>{0}</span>？", selectedNews.newsTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/newsmanpage/delete/"+selectedNews.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $newsGrid.datagrid('reload');
                }
            })
        })
    }

    /************设为主标题*************/

    function setMain() {

        if (!selectedNews) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }

        if (1 == selectedNews.mainFlag){
            showWarningMessage("该新闻已是主标题，请重新选择！");
            return
        }

        var msg = String.format("您确定要设置主标题新闻：<span style='color: red;'>{0}</span>？", selectedNews.newsTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/newsmanpage/setMain/"+selectedNews.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $newsGrid.datagrid('reload');
                }
            })
        })
    }

    /************取消主标题*************/

    function cancelMain() {

        if (!selectedNews) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }

        if (0 == selectedNews.mainFlag){
            showWarningMessage("该新闻不是主标题，请重新选择！");
            return
        }

        var msg = String.format("您确定要取消主标题新闻：<span style='color: red;'>{0}</span>？", selectedNews.newsTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/newsmanpage/cancelMain/"+selectedNews.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $newsGrid.datagrid('reload');
                }
            })
        })
    }

    /************设为轮播图*************/

    function setChange() {

        if (!selectedNews) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }

        if (!selectedNews.picturePath){
            showWarningMessage("该新闻不包含图片，请重新选择！");
            return
        }

        if (1 == selectedNews.changeFlag){
            showWarningMessage("该新闻已是轮播图，请重新选择！");
            return
        }

        if (selectedNews.changeCount > 2){
            showWarningMessage("轮播图不能超过三个，请先取消其他新闻！");
            return
        }

        var msg = String.format("您确定要设置轮播图新闻：<span style='color: red;'>{0}</span>？", selectedNews.newsTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/newsmanpage/setChange/"+selectedNews.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $newsGrid.datagrid('reload');
                }
            })
        })
    }

    /************取消轮播图*************/

    function cancelChange() {
        if (!selectedNews) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }

        if (0 == selectedNews.changeFlag){
            showWarningMessage("该新闻不是轮播图，请重新选择！");
            return
        }
        var msg = String.format("您确定要取消轮播图新闻：<span style='color: red;'>{0}</span>？", selectedNews.newsTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/newsmanpage/cancelChange/"+selectedNews.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $newsGrid.datagrid('reload');
                }
            })
        })
    }


    /***************************图片上传*********************************************/
    $('#pictureNewsUploadBtnAdd,#pictureNewsUploadBtnEdit').linkbutton({
        onClick: function () {
            $pictureNewsWin.window('open');
        }
    });

    $('#pictureNewsWinCloseBtn').linkbutton({
        onClick: function () {
            $pictureNewsWin.window('close');
        }
    });

    var $pictureNewsWin = $('#pictureNewsWin').window({
        title: "图片上传",
        closed: true,
        modal: true,
        height: 155,
        width: 400,
        iconCls: 'icon-add',
        collapsible: false,
        minimizable: false,
        footer: '#pictureNewsWinFooter',
        onClose: function () {

        }
    });

    $('#pictureNewsWinSubmitBtn').linkbutton({
        onClick: function () {
            var $pictureNewsForm = $('#pictureNewsForm');

            $.ajax({
                url: path + "/home/noticemanpage/pictureDetail",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                data : new FormData($pictureNewsForm[0]),
                success:function (r) {
                    var pictureDiv = $('#addPicture,#editPicture');
                    pictureDiv.empty();

                    for (var i=0;i<r.length;i++){
                        var picture = '<div style="float:left;width: 20%;margin: 0 2.5%;border: 1px solid #ccc; box-sizing:border-box;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;margin-bottom: 10px;">'
                        picture += '<img src="data:image/gif;base64,' + r[i] + '" style="height: 100px;">'
                        picture += '<p style="text-align: center;-webkit-margin-before: 0em;-webkit-margin-after: 0em;line-height: 30px;">'+i+'</p></div>';
                        pictureDiv.append(picture);
                    }
                    $pictureNewsWin.window('close');
                }
            });

        }
    });

    function result(value, row, index) {

        if (0 == value ) {
            return "<span style='color:red;'>" + '否' + "</span>";
        } else {
            return "<span style='color:blue;'>" + '是' + "</span>";
        }
    }

});
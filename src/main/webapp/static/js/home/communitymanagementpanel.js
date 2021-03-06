$(function () {

    var selectedCommunity, reqObj = {};
    var $communityGrid = $('#communityGrid').datagrid({
        url: path+ '/home/communitymanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'communityTitle', title: "精品社团标题", width: 150, sortable: true,
                align: 'left'
            }
            // ,
            // {
            //     field: 'communityDetails', title: "精品社团内容", width: 400, sortable: true,
            //     align: 'left'
            // }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addCommunityWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedCommunity) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editCommunityForm.form('load', selectedCommunity);

                        $(document).ready(function(){
                            var ue = UE.getEditor('containerEdit');
                            ue.ready(function() {//编辑器初始化完成再赋值
                                ue.setContent(selectedCommunity.communityDetails);  //赋值给UEditor
                            });
                        });

                        var data = {picturePath:selectedCommunity.picturePath};

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

                        $editCommunityWin.window('open');
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
            selectedCommunity = row;

        },
        onLoadSuccess: function () {
            selectedCommunity = $communityGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/
    var reportAdd = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $addCommunityForm = $('#addCommunityForm').form({
        novalidate: true
    });

    var $addCommunityWin = $('#addCommunityWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addCommunityWinFooter',
        onClose: function () {
            $('#pictureCommunityForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#addCommunityForm').form('disableValidation').form('reset');
            reportAdd.setContent("")
        }
    });

    $('#addCommunityWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addCommunityForm').form('enableValidation').form('validate')) {
                return;
            }

            var communityData = $addCommunityForm.serializeObject(),
                url = path + "/home/communitymanpage/add";

            if (!communityData.communityDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            if (!$("input[name=file]").val()){
                showErrorMessage("请选择图片！");
                return
            }

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/精品校园",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureCommunityForm')[0]),
                success:function (r) {
                    communityData.picturePath = r;
                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(communityData),
                        success:function (r) {
                            $communityGrid.datagrid('reload');
                            $addCommunityWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });




        }
    });

    $('#addCommunityWinCloseBtn').linkbutton({
        onClick: function () {
            $addCommunityWin.window('close');
        }
    });

    /*************修改*******************/
    var reportEdit = UE.getEditor('containerEdit', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $editCommunityForm = $('#editCommunityForm').form({
        novalidate: true
    });

    var $editCommunityWin = $('#editCommunityWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editCommunityWinFooter',
        onClose: function () {
            $('#pictureCommunityForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#editCommunityForm').form('disableValidation').form('reset');
            reportEdit.setContent()
        }
    });

    $('#editCommunityWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editCommunityForm').form('enableValidation').form('validate')) {
                return;
            }

            var communityData = $editCommunityForm.serializeObject(),
                url = path + "/home/communitymanpage/edit";
            communityData.id = selectedCommunity.id;

            if (!communityData.communityDetails){
                showErrorMessage("正文不可为空！");
                return
            }


            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/精品校园",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureCommunityForm')[0]),
                success:function (r) {
                    communityData.picturePath = r;

                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(communityData),
                        success:function (r) {
                            $communityGrid.datagrid('reload');
                            $editCommunityWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });



        }
    });

    $('#editCommunityWinCloseBtn').linkbutton({
        onClick: function () {
            $editCommunityWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedCommunity) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除精品校园：<span style='color: red;'>{0}</span>？", selectedCommunity.communityTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/communitymanpage/delete/"+selectedCommunity.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $communityGrid.datagrid('reload');
                }
            })
        })
    }

    /***************************图片上传*********************************************/
    $('#pictureCommunityUploadBtnAdd,#pictureCommunityUploadBtnEdit').linkbutton({
        onClick: function () {
            $pictureCommunityWin.window('open');
        }
    });

    $('#pictureCommunityWinCloseBtn').linkbutton({
        onClick: function () {
            $pictureCommunityWin.window('close');
        }
    });

    var $pictureCommunityWin = $('#pictureCommunityWin').window({
        title: "图片上传",
        closed: true,
        modal: true,
        height: 155,
        width: 400,
        iconCls: 'icon-add',
        collapsible: false,
        minimizable: false,
        footer: '#pictureCommunityWinFooter',
        onClose: function () {

        }
    });

    $('#pictureCommunityWinSubmitBtn').linkbutton({
        onClick: function () {
            var $pictureCommunityForm = $('#pictureCommunityForm');

            $.ajax({
                url: path + "/home/noticemanpage/pictureDetail",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                data : new FormData($pictureCommunityForm[0]),
                success:function (r) {
                    var pictureDiv = $('#addPicture,#editPicture');
                    pictureDiv.empty();

                    for (var i=0;i<r.length;i++){
                        var picture = '<div style="float:left;width: 20%;margin: 0 2.5%;border: 1px solid #ccc; box-sizing:border-box;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;margin-bottom: 10px;">'
                        picture += '<img src="data:image/gif;base64,' + r[i] + '" style="height: 100px;">'
                        picture += '<p style="text-align: center;-webkit-margin-before: 0em;-webkit-margin-after: 0em;line-height: 30px;">'+(i+1)+'</p></div>';
                        pictureDiv.append(picture);
                    }
                    $pictureCommunityWin.window('close');
                }
            });

        }
    });

});
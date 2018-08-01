$(function () {

    var selectedBanner, reqObj = {};
    var $bannerGrid = $('#bannerGrid').datagrid({
        url: path+ '/home/bannermanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'bannerTitle', title: "轮播图标题", width: 150, sortable: true,
                align: 'left'
            }
            , {
                field: 'statueId', title: "是否开启", width: 150, sortable: true,
                align: 'left',formatter:result
            }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addBannerWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedBanner) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editBannerForm.form('load', selectedBanner);

                        $(document).ready(function(){
                            var ue = UE.getEditor('containerEdit');
                            ue.ready(function() {//编辑器初始化完成再赋值
                                ue.setContent(selectedBanner.bannerDetails);  //赋值给UEditor
                            });
                        });

                        if (selectedBanner.picturePath){
                            var data = {picturePath:selectedBanner.picturePath};

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
                        }


                        $editBannerWin.window('open');
                    }

                }
            },
            {
                text: "删除", iconCls: 'icon-remove',
                handler: function () {
                    removeHandle();
                }
            },
            {
                text: "开启", iconCls: 'icon-remove',
                handler: function () {
                    open();
                }
            },
            {
                text: "暂停", iconCls: 'icon-remove',
                handler: function () {
                    close();
                }
            }
        ],
        onBeforeLoad: function (param) {
            getPage(param);
            $.extend(param, reqObj);
        },
        onSelect: function (index, row) {
            selectedBanner = row;

        },
        onLoadSuccess: function () {
            selectedBanner = $bannerGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/
    var reportAdd = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $addBannerForm = $('#addBannerForm').form({
        novalidate: true
    });

    var $addBannerWin = $('#addBannerWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addBannerWinFooter',
        onClose: function () {
            $('#pictureBannerForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#addBannerForm').form('disableValidation').form('reset');
            // var ue = UE.getEditor('containerAdd');//初始化对象
            reportAdd.setContent('');
        }
    });

    $('#addBannerWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addBannerForm').form('enableValidation').form('validate')) {
                return;
            }

            var bannerData = $addBannerForm.serializeObject(),
                url = path + "/home/bannermanpage/add";

            if (!bannerData.bannerDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            if (!$("input[name=file]").val()){
                showErrorMessage("请选择图片！");
                return
            }

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/轮播图",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureBannerForm')[0]),
                success:function (r) {
                    bannerData.picturePath = r;

                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(bannerData),
                        success:function (r) {
                            $bannerGrid.datagrid('reload');
                            $addBannerWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });




        }
    });

    $('#addBannerWinCloseBtn').linkbutton({
        onClick: function () {
            $addBannerWin.window('close');
        }
    });

    /*************修改*******************/

    var reportEdit = UE.getEditor('containerEdit', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $editBannerForm = $('#editBannerForm').form({
        novalidate: true
    });

    var $editBannerWin = $('#editBannerWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editBannerWinFooter',
        onClose: function () {
            $('#pictureBannerForm').form('reset');
            $('#addPicture,#editPicture').empty();
            $('#editBannerForm').form('disableValidation').form('reset');
            reportEdit.setContent('');
        }
    });

    $('#editBannerWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editBannerForm').form('enableValidation').form('validate')) {
                return;
            }

            var bannerData = $editBannerForm.serializeObject(),
                url = path + "/home/bannermanpage/edit";
            bannerData.id = selectedBanner.id;

            if (!bannerData.bannerDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            $.ajax({
                url: path + "/home/noticemanpage/pictureUpload/轮播图",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                async: true,
                data : new FormData($('#pictureBannerForm')[0]),
                success:function (r) {
                    bannerData.picturePath = r;
                    $.ajax({
                        url:url,type:"POST",contentType: "application/json",data:JSON.stringify(bannerData),
                        success:function (r) {
                            $bannerGrid.datagrid('reload');
                            $editBannerWin.window('close');
                            showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                        }
                    })
                }
            });

        }
    });

    $('#editBannerWinCloseBtn').linkbutton({
        onClick: function () {
            $editBannerWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedBanner) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除轮播图：<span style='color: red;'>{0}</span>？", selectedBanner.bannerTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/bannermanpage/delete/"+selectedBanner.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $bannerGrid.datagrid('reload');
                }
            })
        })
    }

    /************开启*************/

    function open() {
        if (!selectedBanner) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }

        if (1 == selectedBanner.statueId){
            showWarningMessage("该条目处于开启状态，请重新选择！");
            return
        }



        var msg = String.format("您确定要开启轮播图：<span style='color: red;'>{0}</span>？", selectedBanner.bannerTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/bannermanpage/open/"+selectedBanner.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $bannerGrid.datagrid('reload');
                }
            })
        })
    }

    /************关闭*************/

    function close() {
        if (!selectedBanner) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }

        if (0 == selectedBanner.statueId){
            showWarningMessage("该条目处于禁用状态，请重新选择！");
            return
        }

        var msg = String.format("您确定要关闭轮播图：<span style='color: red;'>{0}</span>？", selectedBanner.bannerTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/bannermanpage/close/"+selectedBanner.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $bannerGrid.datagrid('reload');
                }
            })
        })
    }

    /***************************图片上传*********************************************/
    $('#pictureBannerUploadBtnAdd,#pictureBannerUploadBtnEdit').linkbutton({
        onClick: function () {
            $pictureBannerWin.window('open');
        }
    });

    $('#pictureBannerWinCloseBtn').linkbutton({
        onClick: function () {
            $pictureBannerWin.window('close');
        }
    });

    var $pictureBannerWin = $('#pictureBannerWin').window({
        title: "图片上传",
        closed: true,
        modal: true,
        height: 155,
        width: 400,
        iconCls: 'icon-add',
        collapsible: false,
        minimizable: false,
        footer: '#pictureBannerWinFooter',
        onClose: function () {

        }
    });

    $('#pictureBannerWinSubmitBtn').linkbutton({
        onClick: function () {
            var $pictureBannerForm = $('#pictureBannerForm');

            $.ajax({
                url: path + "/home/noticemanpage/pictureDetail",
                type:'POST',
                cache: false,
                contentType: false,
                processData: false,
                data : new FormData($pictureBannerForm[0]),
                success:function (r) {
                    var pictureDiv = $('#addPicture,#editPicture');
                    pictureDiv.empty();

                    for (var i=0;i<r.length;i++){
                        var picture = '<img src="data:image/gif;base64,' + r[i] + '" style="width:100%;height:100%">';
                        pictureDiv.append(picture);
                    }
                    $pictureBannerWin.window('close');
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
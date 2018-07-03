$(function () {

    var selectedTeacher, reqObj = {};
    var $teacherGrid = $('#teacherGrid').datagrid({
        url: path+ '/home/teachermanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'teacherTitle', title: "名师风采标题", width: 150, sortable: true,
                align: 'left'
            },
            {
                field: 'teacherDetails', title: "名师风采内容", width: 400, sortable: true,
                align: 'left'
            }
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
            selectedTeacher = row;

        },
        onLoadSuccess: function () {
            selectedTeacher = $teacherGrid.datagrid('getSelected');
        }
    });


});
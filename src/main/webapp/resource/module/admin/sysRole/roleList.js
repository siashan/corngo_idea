var gridObj;

$(function () {
    gridObj = $.fn.bsgrid.init('searchTable', {
        url: inc.ctx + '/admin/sysRole/json',
        pageSizeSelect: true,
        stripeRows: true,
        displayBlankRows: false,
        //isProcessLockScreen:false,
        pageSize: 10,
        otherParames: $("#searchForm").serializeArray(),
        complete: inc.resizeFrame
    });

    $("#searchBtn").click(function () {
        gridObj.options.otherParames = $("#searchForm").serializeArray();
        gridObj.page(1);
    });
    $("#resetBtn").click(function () {
        $("#searchForm")[0].reset();
    });
});

/**
 * 新增角色
 */
function addRole() {
    var url = inc.ctx + '/admin/sysRole/toRoleAdd';
    window.location.href=url;
}

/**
 * 修改数据字典
 */
function updateRole() {
    if (inc.checkSelected(gridObj)) {
        var url = inc.ctx + '/admin/sysRole/toRoleUpdate/'+inc.getSelectedRowDate(gridObj, "id");
        window.location.href=url;
    }else{
        layer.alert("没有选中任何记录!");
    }
}


/**
 * 删除角色
 */
function delRole() {
    if (inc.checkSelected(gridObj)) {
        window.parent.layer.confirm('您确定要删除该记录吗？', {
            btn: ['确定', '算了'] //按钮
        }, function () {
            var url = inc.ctx + "/admin/sysRole/delRole/"+ inc.getSelectedRowDate(gridObj,"id");
            $.post(url, {}, function (data) {
                if(data.msg == '200'){
                    layer.msg(data.data,{icon:1});
                }else{
                    layer.msg(data.data,{icon:2});
                }
                gridObj.page(1);
            });
        }, function (index) {
            layer.closeAll();
        });
    }else{
        layer.alert("没有选中任何记录!");
    }
}



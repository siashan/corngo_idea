var gridObj;

$(function () {
    gridObj = $.fn.bsgrid.init('searchTable', {
        url: inc.ctx + '/admin/sysDict/json',
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
 * 新增数据字典
 */
function addDict() {
    $.post(inc.ctx + '/admin/sysDict/toDictAdd', {}, function (str) {
        window.parent.layer.open({
            title: '新增数据字典',
            type: 1,
            offset: 'auto',
            shift: 2,
            area: '550px',
            content: str
        })
    });
}

/**
 * 修改数据字典
 */
function updateDict() {
    if (inc.checkSelected(gridObj)) {
        $.post(inc.ctx + '/admin/sysDict/toDictUpdate/' + inc.getSelectedRowDate(gridObj, "id"),{} , function (str) {
            window.parent.layer.open({
                title: '修改数据字典',
                type: 1,
                offset: 'auto',
                shift: 2,
                area: '550px',
                content: str
            })
        });
    }else{
        layer.alert("没有选中任何记录!");
    }
}


/**
 * 删除数据字典
 */
function delDict() {
    if (inc.checkSelected(gridObj)) {
        window.parent.layer.confirm('您确定要删除该记录吗？', {
            btn: ['确定', '算了'] //按钮
        }, function () {
            var url = inc.ctx + "/admin/sysDict/delDict/"+ inc.getSelectedRowDate(gridObj,"id");
            $.post(url, {}, function (data) {
                if(data.code == '200'){
                    layer.msg(data.msg,{icon:1});
                }else{
                    layer.msg(data.msg,{icon:2});
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



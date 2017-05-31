var gridObj;

$(function () {
    gridObj = $.fn.bsgrid.init('searchTable', {
        url: inc.ctx + '/corn/goods/json',
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
 * 新增商品
 */
function addGoods(){
    var url = inc.ctx + "/corn/goods/toAdd";
    window.location.href = url;
}

/**
 * 修改
 */
function editGoods() {
    if (inc.checkSelected(gridObj)) {
        var url = inc.ctx + '/corn/goods/toEdit/' + inc.getSelectedRowDate(gridObj, "id");
        window.location.href = url;
    }else{
        window.parent.layer.alert("没有选中任何记录!");
    }
}


/**
 * 删除
 */
function delGoods() {
    if (inc.checkSelected(gridObj)) {
        window.parent.layer.confirm('您确定要删除该记录吗？', {
            btn: ['确定', '算了'] //按钮
        }, function () {
            var url = inc.ctx + "/corn/goods/delGoods/"+ inc.getSelectedRowDate(gridObj,"id");
            $.post(url, {}, function (data) {
                if(data.code == '200'){
                    window.parent.layer.msg(data.msg,{icon:1});
                }else{
                    window.parent.layer.msg(data.msg,{icon:2});
                }
                gridObj.page(1);
            });
        }, function (index) {
            window.parent.layer.closeAll();
        });
    }else{
        window.parent.layer.alert("没有选中任何记录!");
    }
}

function getStatus(record, rowIndex, colIndex, options){
    var my = gridObj.getRecordIndexValue(record, 'status');
    return eval('goodsStatus.d_' + my);
}



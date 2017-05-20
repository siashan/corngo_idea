var gridObj;

$(function () {
    gridObj = $.fn.bsgrid.init('searchTable', {
        url: inc.ctx + '/admin/sysUser/json',
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
 * 新增用户
 */
function addUser() {
    var url = inc.ctx + '/admin/sysUser/toAdd';
    window.location.href = url;
}

    /**
     * 更新用户
     */
    function updateUser() {
        if (inc.checkSelected(gridObj)) {
            var url = inc.ctx + '/admin/sysUser/toUpdate/'+inc.getSelectedRowDate(gridObj, "id");
            window.location.href = url;
        } else {
        	layer.alert("没有选中任何记录");
        }
    }

    /**
     * 删除用户
     */
    function delUser() {
    	 if (inc.checkSelected(gridObj)) {
    		 layer.confirm('您确定要删除该记录吗？', {
    			 btn: ['确定', '算了'] //按钮
    		 }, function () {
    			 var url = inc.ctx + "/admin/sysUser/delUser/"+ inc.getSelectedRowDate(gridObj, "id");
    			 $.post(url, {}, function (data) {
    				 if(data.code == '200'){
    					 layer.msg(data.msg,{icon:1});
    				 }else{
    					 layer.msg(data.msg,{icon:2});
    				 }
    				 window.location.href = inc.ctx + "/admin/sysUser";
    			 });
    		 }, function (index) {
    			 layer.closeAll();
    		 });
        } else {
        	layer.alert("没有选中任何记录");
        }
    }


function getStatus(record, rowIndex, colIndex, options) {
    var my = gridObj.getRecordIndexValue(record, 'status');
    return eval('userStatus.d_' + my);
}
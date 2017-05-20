
    var selected = -1;
    $(function () {
        $('#dt').treegrid();
        $('#dt1').treegrid();
        var rows = $("#tb tr");
        $("#tb tr td").click(function () {
            var row = $(this).parent("tr");
            if (row.hasClass("selected")) {
                row.removeClass("selected");
                selected = -1;
            } else {
                rows.removeClass("selected");
                row.addClass("selected");
                selected = row.data("id");
            }
        });
    });


/**
 * 新增组织机构
 */
function addDept() {
    if (selected == -1) {
        layer.alert("没有选中任何记录");
    } else {
        $.post(inc.ctx + '/admin/sysDept/toDeptAdd', {parentId:selected}, function (str) {
            layer.open({
                title: '新增组织机构',
                type: 1,
                offset: 'auto',
                shift: 2,
                area: '550px',
                content: str
            })
        });
    }
}

    /**
     * 更新组织机构
     */
    function updateDept() {
        if (selected == -1) {
            layer.alert("没有选中任何记录");
        } else {
            $.post(inc.ctx + '/admin/sysDept/toDeptUpdate/'+selected, function (str) {
                layer.open({
                    title: '修改菜单',
                    type: 1,
                    offset: 'auto',
                    shift: 2,
                    area: '550px',
                    content: str
                })
            });
        }
    }

    /**
     * 删除菜单
     */
    function delDept() {
        if (selected == -1) {
            layer.alert("没有选中任何记录");
        } else {
            layer.confirm('您确定要删除该记录吗？', {
                btn: ['确定', '算了'] //按钮
            }, function () {
                var url = inc.ctx + "/admin/sysDept/delDept/"+ selected;
                $.post(url, {}, function (data) {
                    if(data.code == '200'){
                        layer.msg(data.data,{icon:1});
                    }else{
                        layer.msg(data.data,{icon:2});
                    }
                    window.location.href = inc.ctx + "/admin/sysDept?leftMenu=24";
                });
            }, function (index) {
                layer.closeAll();
            });
        }
    }

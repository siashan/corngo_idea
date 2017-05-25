
    var selected = -1;
    $(function () {
        $('#dt').treegrid({initialState:"collapsed"});
        $('#dt1').treegrid({initialState:"collapsed"});
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
 * 新增菜单
 */
function addMenu() {
    if (selected == -1) {
        window.parent.layer.alert("没有选中任何记录");
    } else {
        $.post(inc.ctx + '/admin/sysMenu/toMenuAdd', {parentId:selected}, function (str) {
            window.parent.layer.open({
                title: '新增菜单',
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
     * 更新菜单
     */
    function updateMenu() {
        if (selected == -1) {
            window.parent.layer.alert("没有选中任何记录");
        } else {
            $.post(inc.ctx + '/admin/sysMenu/toMenuUpdate/'+selected, function (str) {
                window.parent.layer.open({
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
    function delMenu() {
        if (selected == -1) {
            window.parent.layer.alert("没有选中任何记录");
        } else {
            window.parent.layer.confirm('您确定要删除该记录吗？', {
                btn: ['确定', '算了'] //按钮
            }, function () {
                var url = inc.ctx + "/admin/sysMenu/delMenu/"+ selected;
                $.post(url, {}, function (data) {
                    if(data.code == '200'){
                        window.parent.layer.msg(data.msg,{icon:1});
                    }else{
                        window.parent.layer.msg(data.msg,{icon:2});
                    }
                    window.location.href = inc.ctx + "/admin/sysMenu?leftMenu=21";
                });
            }, function (index) {
                window.parent.layer.closeAll();
            });
        }
    }

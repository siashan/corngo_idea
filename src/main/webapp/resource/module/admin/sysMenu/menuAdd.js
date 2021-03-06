$(document).ready(function () {
    //表单校验通过一-提交表单
    $("#form1").validator({
        valid: function (form) {
            var me = this;
            var index;
            // 提交表单之前，hold住表单，防止重复提交
            me.holdSubmit(function () {
                index = window.parent.layer.load(1, {
                    shade: [0.1, '#fff'] //0.1透明度的白色背景
                });
            });
            $.ajax({
                url: inc.ctx + "/admin/sysMenu/saveMenu",
                data: $("#form1").serialize(),
                dataType: "json",
                type: "post",
                async: false,
                success: function (data) {
                    if (data.code == '200') {
                        me.holdSubmit(false);
                        window.parent.layer.close(index);
                        window.parent.layer.closeAll();
                        window.parent.layer.alert('保存成功', {icon: 1}, function (lIndex) {
                            var aFrame = inc.getActiveFrame();
                            aFrame.src = inc.ctx+"/admin/sysMenu";
                            window.parent.layer.closeAll();
                        });
                    }
                },
                error: function () {
                    window.parent.layer.alert('保存失败');
                }
            });
        }
    });

});

function mySubmit() {
    $("#form1").trigger("validate");
}

$(document).ready(function () {
    //表单校验通过一-提交表单
    $("#form1").validator({
        valid: function (form) {
        	console.info(121);
            var me = this;
            var index;
            console.info(111);
            // 提交表单之前，hold住表单，防止重复提交
            me.holdSubmit(function () {
                index = layer.load(1, {
                    shade: [0.1, '#fff'] //0.1透明度的白色背景
                });
            });
            $.ajax({
                url: inc.ctx + "/admin/sysUser/saveUser",
                data: $("#form1").serialize(),
                dataType: "json",
                type: "post",
                async: false,
                success: function (data) {
                    if (data.code == '200') {
                        me.holdSubmit(false);
                        layer.close(index);
                        layer.closeAll();
                        layer.alert('保存成功', {icon: 1}, function (lIndex) {
                            window.location.href = inc.ctx + "/admin/sysUser";
                        });
                    }
                },
                error: function () {
                    layer.alert('保存失败');
                }
            });
        }
    });

});

function mySubmit() {
    $("#form1").trigger("validate");
}

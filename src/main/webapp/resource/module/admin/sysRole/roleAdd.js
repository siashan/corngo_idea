var tree;
var setting = {
    check: {
        enable: true,
        chkboxType : { "Y" : "ps", "N" : "ps" }
    },
    data: {
        simpleData: {
            enable: true
        }
    }
};

$(document).ready(function(){
    inc.resizeFrame;

    tree =  $.fn.zTree.init($("#treeDemo"), setting, eval(treeData));
    tree.expandAll(true);

    //表单校验通过一-提交表单
    $("#form1").validator({
        valid: function (form) {
            var me = this;
            var index ;
            // 提交表单之前，hold住表单，防止重复提交
            me.holdSubmit(function () {
                index = layer.load(1, {
                    shade: [0.1,'#fff'] //0.1透明度的白色背景
                });
            });
            $.ajax({
                url: inc.ctx + "/admin/sysRole/saveRole",
                data: $("#form1").serialize(),
                dataType: "json",
                type: "post",
                async:false,
                success: function (data) {
                    if (data.code == '200') {
                        me.holdSubmit(false);
                        layer.close(index);
                        layer.alert('保存成功',{icon: 1},function(lIndex){
                            window.location.href = inc.ctx + "/admin/sysRole";
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

function mySubmit(){
    var checkedNodes = tree.getCheckedNodes(true);
    if(checkedNodes.length == 0){
        layer.alert("需要至少一个资源");
        return false;
    }
    var p = "";
    for(var i = 0; i < checkedNodes.length; i++){
        var tmp = checkedNodes[i];
        p+=tmp.id;
        if(i < checkedNodes.length -1){
            p+=",";
        }
    }
    $("#menus").val(p);
    $("#form1").trigger("validate");
}
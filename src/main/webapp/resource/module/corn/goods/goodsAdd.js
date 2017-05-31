$(document).ready(function () {

    inc.initUpload($("#imgUpLoad"),'form_up','upfile','upfile','',function(data){
        $('#licenseId').val(data.url);
        $('#licenseId_image').attr('src',inc.preview+data.url);
    });


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
                url: inc.ctx + "/corn/goods/save",
                data: $("#form1").serialize(),
                dataType: "json",
                type: "post",
                async: false,
                success: function (data) {
                    if (data.code == '200') {
                        me.holdSubmit(false);
                        window.parent. layer.close(index);
                        window.parent.layer.closeAll();
                        window.parent.layer.alert('保存成功', {icon: 1}, function (lIndex) {
                            var aFrame = inc.getActiveFrame();
                           aFrame.src = inc.ctx+"/corn/goods";
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



function  change(){
    var imgLoading = window.parent.layer.load('图片上传中...');
    var upurl =inc.ctx + inc.fs + "?v=" + Math.random();
    $("#form_up").attr("action", upurl);
    $.ajaxFileUpload({
        url: upurl,
        type: 'post',
        secureuri: false, //一般设置为false
        fileElementId: 'upfile', // 上传文件的id、name属性名
        dataType: 'json', //返回值类型，一般设置为json、application/json
        success: function(data, status){
            if(data.state == 'SUCCESS'){
                $("#image").attr('src', inc.ctx + "/fs/service/showImg?imgUrl="+data.url);
                $("#imgUrl").val(data.url);
                window.parent.layer.close(imgLoading);
            }
        },
        error: function(data, status, e){
            alert(e);
        }
    });
};

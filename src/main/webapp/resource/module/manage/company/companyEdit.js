$(document).ready(function () {
    //表单校验通过一-提交表单
    $("#form1").validator({
        valid: function (form) {
            var me = this;
            var index;
            // 提交表单之前，hold住表单，防止重复提交
            me.holdSubmit(function () {
                index = layer.load(1, {
                    shade: [0.1, '#fff'] //0.1透明度的白色背景
                });
            });
            $.ajax({
                url: inc.ctx + "/manage/company/saveCompany",
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
                            window.location.href = inc.ctx + "/manage/company";
                        });
                    }
                },
                error: function () {
                    layer.alert('保存失败');
                }
            });
        }
    });
    //获取省份
    $.ajax({
        url: inc.ctx + "/manage/company/cities",
        data: {'parentCode':"0"},
        dataType: "json",
        type: "post",
        async: false,
        success: function (data) {
            $('#provinceCode').empty();
            for(var i in data){
            	$('#provinceCode').append("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
            }
        },
        error: function () {
            layer.alert('保存失败');
        }
    });
    //获取城市
    var provinceCode = $('#provinceCode');
    $.ajax({
        url: inc.ctx + "/manage/company/cities",
        data: {'parentCode':provinceCode.val()},
        dataType: "json",
        type: "post",
        async: false,
        success: function (data) {
            $('#cityCode').empty();
            for(var i in data){
            	$('#cityCode').append("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
            }
        },
        error: function () {
            layer.alert('保存失败');
        }
    });
    
    //获取县区
    var cityCode = $('#cityCode');
    $.ajax({
        url: inc.ctx + "/manage/company/cities",
        data: {'parentCode':cityCode.val()},
        dataType: "json",
        type: "post",
        async: false,
        success: function (data) {
            $('#countyCode').empty();
            for(var i in data){
            	$('#countyCode').append("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
            }
        },
        error: function () {
            layer.alert('保存失败');
        }
    });
});

function mySubmit() {
    $("#form1").trigger("validate");
}
//获取对应省份下城市
function getCities(){
	var provinceCode = $('#provinceCode');
    $.ajax({
        url: inc.ctx + "/manage/company/cities",
        data: {'parentCode':provinceCode.val()},
        dataType: "json",
        type: "post",
        async: false,
        success: function (data) {
            $('#cityCode').empty();
            for(var i in data){
            	$('#cityCode').append("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
            }
        },
        error: function () {
            layer.alert('保存失败');
        }
    });
    getCounties();
}
//获取对应城市下区县
function getCounties(){
	var cityCode = $('#cityCode');
    $.ajax({
        url: inc.ctx + "/manage/company/cities",
        data: {'parentCode':cityCode.val()},
        dataType: "json",
        type: "post",
        async: false,
        success: function (data) {
            $('#countyCode').empty();
            for(var i in data){
            	$('#countyCode').append("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
            }
        },
        error: function () {
            layer.alert('保存失败');
        }
    });
}

$(function () {
    $.messager.model = {
        ok:{text:'确定'},
        cancel:{text:'取消'}
    }
})

//注意代码不能写在上面的function()  var handlerMessage作用域问题
// 抽取-提供给每个页面处理ajax 请求
var handlerMessage = function (data) {
    if (data.succes){
        window.location.reload();
    }else {
        $.messager.popup(data.msg);
    }
}
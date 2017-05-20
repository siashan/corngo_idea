/**
 * 检查是否有grid行被选中
 * @param gridObj
 */
inc.checkSelected = function (gridObj) {
    return gridObj.getSelectedRow().length > 0;
};
/**
 * 获取bsgrid选中行的对应key值
 * @param gridObj
 * @param key
 */
inc.getSelectedRowDate = function(gridObj,key){
    var row = gridObj.getRowRecord(gridObj.getSelectedRow());
    if (key) {
        return gridObj.getRecordIndexValue(row, key);
    } else {
        return row;
    }
}

/**
 * 子页面大小变动之后调整父页面iframe高度
 */
inc.resizeFrame = function () {
    //if (top.location != location) {
    var ifm =  window.parent.document.getElementsByTagName("iframe");
    for (var i = 0; i < ifm.length; i++){
        if (ifm[i].style.display != 'none') {
            //$(ifm[i]).height(document.body.scrollHeight);
            //alert(document.body.scrollHeight);
            //var e = $(window.parent.document.getElementById('page-heading'));
            //e.height(document.body.scrollHeight + 30);
            setIframeHeight(ifm[i]);
            break;
        }
    }
}

function setIframeHeight (iframe) {
    if (iframe) {
        var iframewin = iframe.contentWindow || iframe.contentDocument.parentWindow
        if (iframewin.document.body) {
            iframe.height = iframewin.document.documentElement.scrollHeight || iframewin.document.body.scrollHeight

        }

    }

}


inc.getActiveFrame = function(){
    var ifm =  window.parent.document.getElementsByTagName("iframe");
    for (var i = 0; i < ifm.length; i++){
        if (ifm[i].style.display != 'none') {
           return ifm[i];
        }
    }
}
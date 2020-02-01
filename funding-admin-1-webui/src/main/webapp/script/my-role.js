//打开删除确认模态框
function showRemoveConfirmModel() {
    //1. 将模态框显示出来
    $('#confirmModal').modal("show");

    //2. 获取 roleIdList 获取roleList
    var roleList = getRoleListByRoleIdArray(window.roleIdList);

    //清空 #confirmModalTableBody
    $("#confirmModalTableBody").empty();

    //3 . 填充 #confirmModalTableBody
    for (var i = 0; i < roleList.length; i++) {
        var role = roleList[i];
        var id = role.id;
        var name = role.name;
        var trHtml = "<tr><td>" + id +  "</td><td>" + name + "</td></tr>";
        $("#confirmModalTableBody").append(trHtml);
    }
}

function sb() {
    layer.msg("大家好才是真的好")
}

//给服务器发送请求，获取分页数据，也就是PageInfo,并再页面上显示分页效果(分页主体，页码导航条)
function showPage() {
    //获取分页数据:PageInfo
    var pageInfo = getPageInfo();
    if (pageInfo == null){
        layer.msg("求登陆后再操作");
        //如果没有得到 pageInfo 就直接结束了 该方法的执行,终止了后面的操作
        return;
    }

    //页面上表格中显示分页tBody的主题数据
    generateTableBody(pageInfo);
    //在页面上的表格中tfoot显示分页的页码导航条
    generateTableNavigator(pageInfo);

}

function getPageInfo() {
    //以同步的ajax请求的方式来获取
    var ajaxResult = $.ajax({
        "url":"role/search/by/keyword.json",
        "type":"post",
        "data":{
            "pageNum":(window.pageNum == undefined) ? 1:window.pageNum,
            "pageSize":(window.pageSize == undefined) ? 5:window.pageSize,
            "keyword":(window.keyword == undefined) ? "": window.keyword
        },
        "dataType":"json",
        "async":false//为了保证当前getPageInfo函数能够在发送ajax请求之后拿到值并且显示，所以设置为同步请求
    });
    console.log(ajaxResult);  // ajaxResult 是json格式的响应体，有三个属性： data,message,result(状态)
    var resultEntity = ajaxResult.responseJSON;
    var resultStatus = resultEntity.result;
    if (resultStatus == "SUCCESS") {
        var pageInfo = resultEntity.data;
        window.total = pageInfo.pages;
        return pageInfo
    }
    if (resultStatus == "FIELD"){
        layer.msg(resultEntity.message)
    }
    return null
}
//初始化全局变量
function initGlobleVariable() {
    window.pageNum=1;
    window.pageSize=5;
    window.keyword="";//一开始的时候keyword是没有的
}

//使用pageInfo在tbody显示分页数据
function generateTableBody(pageInfo) {
    $("#roleTablebody").empty()

    //获取数据的集合
    var list = pageInfo.list
    //判断list是否有效
    if (list == null || list.length == 0) {
        $("#roleTablebody").append("<tr><td colspan='4' style='text-align: center'>没有查询到数据</td></tr>>")
        return
    }

    for (var i = 0; i < list.length; i++) {
        var role = list[i];
        var checkBtn = "<button type='button' class='btn btn-success btn-xs checkBtn' roleId ='"+ role.id +  "'  ><i class='glyphicon glyphicon-check'></i></button>";
        var pencilBtn ="<button type='button' roleId ='"+ role.id +  "' roleName ='"+ role.name +  "'  class='btn btn-primary btn-xs refreshBtn'><i class='glyphicon glyphicon-pencil'></i></button>";
        var removeBtn = "<button type=button' roleId ='"+ role.id +  "' class='btn btn-danger btn-xs removeBtn'><i class='glyphicon glyphicon-remove'></i></button>";
        var numberId = "<td>"+ (i+ 1) + "</td>";
        var checkBoxId = "<td><input roleId ='"+ role.id +  "' class = 'itemBox'" + " type='checkbox'/></td>";
        var roleNameId = "<td>" + role.name + "</td>";
        var btnTd = "<td> " + checkBtn + " " + pencilBtn + " " + removeBtn +  " </td>";
        var tr = "<tr>  " + numberId + checkBoxId + roleNameId + btnTd + "</tr>";
        //将前面拼接好的html追加到tbody里面
        $("#roleTablebody").append(tr)
    }

}
//使用pageInfo在tfoot显示分页导航条
function generateTableNavigator(pageInfo) {
    //导航条初始化函数
    initPagination(pageInfo);
}

//声明函数封装导航条初始化操作
function initPagination(pageInfo) {
    //pagination方法的第二个参数：声明变量存储分页导航条显示时的属性设置
    // $("#Pagination").pagination的作用就是显示分页导航条
    $("#Pagination").pagination(pageInfo.total, {
        num_edge_entries: 3,//边缘页数
        num_display_entries: 5, //主体页数
        callback: pageselectCallback,
        items_per_page: window.pageSize, //每页显示数据数量，就是pageSize
        current_page: (window.pageNum - 1), //当前页页码
        prev_text: "上一页",
        next_text: "下一页"
    });
}

function pageselectCallback(pageIndex, jq) {
    //将全局变量中的pageNum修改
    //pageIndex 是从0 开始的，PageNum是从 1 开始的
    window.pageNum = pageIndex + 1;
    //调用分页函数重新制定分页
    showPage();
    return false;
}

//根据 roleIdArray 查询roleList
function getRoleListByRoleIdArray(roleIdArray) {
    //1. 将 roleIdArray 转换为Json字符串
    var requestBody = JSON.stringify(roleIdArray);
    //2. 发送ajax请求
    var ajxaResult = $.ajax({
         "url":"role/get/list/by/id/list.json",
        "type":"post",
        "data":requestBody,
        "contentType":"application/json;charset=UTF-8",
        "dataType":"json",
        "async":false  // 同步发送ajax请求
    });
    //3.获取json对象类型的响应体
    var resultEntity = ajxaResult.responseJSON;
    //验证是否成功
    console.log("ajxaResult:" + ajxaResult);
    var result2 = resultEntity.result;
    if("SUCCESS" == result2){
        return resultEntity.data;
    }
    if("FAILED" == result2){
        layer.msg(resultEntity.message)
        return null;
    }
    return null;
}









function removeAdminUser(requestBody) {
    //发送ajax请求将adminArray给controller处理
    $.ajax({
        "url": "admin/batch/remove.json",
        "type": "post",
        "contentType": "application/json;charset=UTF-8",//contentType 设置你发送数据给服务器的格式
        "data": requestBody,    //请求报文体
        "dataType": "json",  //设置希望从服务器获取的数据的类型,把服务器返回的数据当作json格式来解析
        "success": function (response) {//服务器成功处理返回的请求。服务器响应的数据就传入了 response变量中
            if (response.result == "SUCCESS"){//执行成功跳转页面到分页
                window.location.href = "admin/query/for/search.html?pageNum=" + window.pageNum + "&keyword=" + window.keyword;
            }
            if (response.result == "FAILED"){
                alert(response)
                return
            }
        },
        "error": function (response) {//服务器失败处理返回的请求
            alert("请求出错了")
            return
        }
    })
}

//声明函数封装导航条初始化操作
function initPagination() {
        //pagination方法的第二个参数：声明变量存储分页导航条显示时的属性设置
        // $("#Pagination").pagination的作用就是显示分页导航条
        $("#Pagination").pagination(window.totalRecord, {
            num_edge_entries: 2,//边缘页数
            num_display_entries: 4, //主体页数
            callback: pageselectCallback,
            items_per_page: window.pageSize, //每页显示数据数量，就是pageSize
            current_page: (window.pageNum - 1), //当前页页码
            prev_text: "上一页",
            next_text: "下一页",
            ellipse_text: "..."
        });
}

function pageselectCallback(pageIndex, jq) {
    //将全局变量中的pageNum修改
    window.pageNum = pageIndex + 1;
    //pageIndex 是从0 开始的，PageNum是从 1 开始的
    //页面跳转。keyword是 输入条件框的输入值，请求参数中有，所以直接从请求参数中获取即可。通过自定义全局变量 keyword，绕开。
    window.location.href = "admin/query/for/search.html?pageNum=" + (pageIndex + 1) + "&keyword=" + window.keyword;
    return false;
}
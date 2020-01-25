<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="UTF-8">
<%--引入头部，样式等--%>
<%@ include file="include-head.jsp" %>
<link rel="stylesheet" href="css/pagination.css"/>
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<script type="text/javascript" src="script/my-role.js"></script>
<script>
    $(function () {
        //调用分页参数初始化
        initGlobleVariable();
        //执行分页操作
        showPage();

        $("#queryBtn").click(function () {
            var keyword = $.trim($("#keywordtInput").val());
            if (keyword == null || keyword == "") {//如果输入关键词无效
                layer.msg("请输入关键词");
                return;
            }
            window.keyword = keyword;
            window.pageNum=1;
            //查询重新分页
            showPage();

        });

        $("#sumaryBox").click(function () {
            //1. 获取当前checkBox被选中的状态
            var currentStatus = this.checked;
            //2. 设置checkBox的选中状态
            $(".itemBox").prop("checked",currentStatus);

        });

        //给批量删除绑定单击事件响应函数
        $("#batchRemoveBtn").click(function () {
            //获取被选中的itemBox
            var length = $(".itemBox:checked").length;
            //如果长度为零，则提醒选择itemBox
            if (length == 0){
                layer.msg("请至少选择一条删除数据");
                return;
            }
            window.roleIdList = new Array();
            //在全局作用域内创建roleIdArray
            $(".itemBox:checked").each(function () {
                var roleId = $(this).attr("roleId");
                window.roleIdList.push(roleId);
            });
            //调用函数弹出模态框
            showRemoveConfirmModel();
        });

        //给确认模态框中的ok绑定函数
        $("#confirmBtn").click(function () {
            var requestBody = JSON.stringify(window.roleIdList);
            $.ajax({
                "url":"role/batch/remove.json",
                "type":"post",
                "data":requestBody,
                "contentType":"application/json;charset=UTF-8",
                "dataType":"json",
                "success":function (response) {
                    var result = response.result;
                    if ("SUCCESS" == result) {
                        layer.msg("成功删除");
                        //重新调用分页的方法
                        showPage();
                    }
                    if ("FAILED" == result) {
                        layer.msg(response.message);
                    }
                    //关闭模态框
                    $('#confirmModal').modal("hide");
                },
                "error":function (response) {
                    layer.msg(response.message);
                }

            });
        });

        //给单条绑定删除事件
        $("#roleTablebody").on("click",".removeBtn",function () {
            var roleId = $(this).attr("roleId");
            window.roleIdList = new Array();
            window.roleIdList.push(roleId);
            //调用函数弹出模态框
            showRemoveConfirmModel();
        });

    })
</script>
<body>
<%--引入公共标题栏--%>
<%@ include file="include-nav.jsp" %>

<div class="container-fluid">
    <div class="row">

        <%--引入公共导航栏--%>
        <%@ include file="include-sidebar.jsp" %>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="keywordtInput" class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="queryBtn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button id="batchRemoveBtn" type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button id="addBtn" type="button" class="btn btn-primary" style="float:right;" ><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr >
                                <th width="30">#</th>
                                <th width="30"><input id="sumaryBox" type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="roleTablebody">

                            </tbody>
                            <tfoot>
                            <tr >
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"></div><!--这里显示分页-->
                                </td>
                            </tr>

                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<%@include file="include-modal-role-confirm.jsp" %>

</body>
</html>
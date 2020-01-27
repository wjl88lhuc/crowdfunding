<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="UTF-8">
<%--引入头部，样式等--%>
<%@ include file="include-head.jsp" %>
<script type="text/javascript" src="script/my-menu.js"></script>
<script type="text/javascript">
    $(function () {
        initWholeZtree();

        $("#menuAddBtn").click(function () {
            //收集表单填写的数据
            var name = $.trim($("#menuAddModal [name='name']").val());
            var url = $.trim($("#menuAddModal [name='url']").val());
            var icon = $.trim($("#menuAddModal [name='icon']:checked").val());

            if (name == null || name.length == 0){
                layer.msg("请填写菜单项名称");
                return;
            }

            if (url == null || url.length == 0){
                layer.msg("请填写菜单项地址");
                return;
            }

            if (icon == null || icon.length == 0){
                layer.msg("请选择图标");
                return;
            }

            //发送ajax请求给服务器
            $.ajax({
                "url":"menu/save.json",
                "type":"post",
                "dataType":"json",
                "data":{
                    "name":name,
                    "url":url,
                    "pid":window.menuId,//当前操作的节点是新节点的父节点
                    "icon":icon
                },
                "success":function (response) {
                    var result = response.result;
                    if (result == "SUCCESS") {
                        layer.msg("操作成功");
                        initWholeZtree();
                    }
                    if (result == "FAILED") {
                        layer.msg(response.message);
                    }
                },
                "error":function (response) {
                    layer.msg(response.message);
                }
            });

            //发送ajax请求之后关闭模态框
            $("#menuAddModal").modal("hide");


        });

        $("#menuEditBtn").click(function () {
            //收集表单填写的数据
            var name = $.trim($("#menuEditModal [name='name']").val());
            var url = $.trim($("#menuEditModal [name='url']").val());
            var icon = $.trim($("#menuEditModal [name='icon']:checked").val());

            if (name == null || name.length == 0){
                layer.msg("请填写菜单项名称");
                return;
            }

            if (url == null || url.length == 0){
                layer.msg("请填写菜单项地址");
                return;
            }

            if (icon == null || icon.length == 0){
                layer.msg("请选择图标");
                return;
            }

            //发送请求更新
            $.ajax({
                "url":"menu/update.json",
                "type":"post",
                "dataType":"json",
                "data":{
                    "id":window.menuId,
                    "name":name,
                    "url":url,
                    "icon":icon
                },
                "success":function (response) {
                    var result = response.result;
                    if (result == "SUCCESS") {
                        layer.msg("操作成功");
                        initWholeZtree();
                    }
                    if (result == "FAILED") {
                        layer.msg(response.message);
                    }
                },
                "error":function (response) {
                    layer.msg(response.message);
                }
            });

            //关闭模态框
            $("#menuEditModal").modal("hide");

        });

    });

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
                    <i class="glyphicon glyphicon-th-list"></i>权限菜单列表
                    <div style="float: right;cursor: pointer;" data-toggle="modal" data-target="#myModal">
                        <i class="glyphicon glyphicon-question-sign"></i>
                    </div>
                    <%--<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 权限菜单列表</h3>--%>
                </div>
                <div class="panel-body">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="include-modal-menu.jsp" %>

</body>
</html>
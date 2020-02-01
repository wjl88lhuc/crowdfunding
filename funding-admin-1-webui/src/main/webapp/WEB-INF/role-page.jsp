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

        //给新增添加事件
        $("#addBtn").click(function () {
            $("#addConfirmModal").modal("show");
        });

        //给新增的保存按钮绑定事件
        $("#addConfirmBtn").click(function () {
            var roleName = $.trim($("#roleNameInput").val());
            if (roleName == null || roleName == ""){
                layer.msg("请输入有效角色名称");
                return;
            }
            //发送请求
            $.ajax({
                "url":"role/save/role.json",
                "type":"post",
                "data":{
                    "roleName":roleName
                },
                "dataType":"json",
                "success":function (response) {
                    var result = response.result;
                    if (result == "SUCCESS"){
                        layer.msg("操作成功");
                        //重新分页
                        window.pageNum =window.total + 1;//前往最后一页
                        showPage();
                        console.log("window.pageNum: " + window.pageNum)
                    }
                    if (result == "FAILED") {
                        layer.msg(respons.message);
                    }
                    //不管是失败还是成功都要关闭模态框
                    $("#addConfirmModal").modal("hide");

                    //清理本次在文本框的输入值
                    $("#roleNameInput").val("");
                },
                "error":function (response) {
                    layer.msg(respons.message);
                }
            });
        });

        //给更新绑定更新事件
        $("#roleTablebody").on("click",".refreshBtn",function () {
            var roleId = $(this).attr("roleId");
            var roleName = $(this).attr("roleName");
            $("#refreshRoleNameInput").val(roleName);
            window.roleId = roleId;
            $("#refreshConfirmModal").modal("show");
        });

        //给更新的保存更新按钮绑定事件9
        $("#refreshConfirmBtn").click(function () {
            $.ajax({
                "url":"refresh/role/fresh.json",
                "type":"post",
                "data":{
                    "roleId":window.roleId,
                    "roleName":$("#refreshRoleNameInput").val()
                },
                "dataType":"json",
                "success":function (response) {
                    var result = response.result;
                    if (result == "SUCCESS") {
                        layer.msg("更新成功");
                        showPage();
                    }
                    if (result == "FAILED") {
                        layer.msg("更新失败")
                    }
                    $("#refreshConfirmModal").modal("hide");
                },
                "error":function (response) {
                    layer.msg("更新失败")
                    $("#refreshConfirmModal").modal("hide");
                }
            });
        });

        //checkBtn
        $("#roleTablebody").on("click",".checkBtn",function () {
            var setting = {
                data:{
                    simpleData:{
                        enable: true,  // 设置这个属性，zTree就使用我们传入的json格式的数据进行组装
                        pIdKey: "categoryId"
                    },
                    key:{
                        name:"title"
                    }
                },
                check:{
                    enable:true  //这个设置的作用就是在树形结构前面显示多选框
                }
            };

            //将角色id 存入全局变量
            var roleId = $(this).attr("roleId");
            window.roleId = roleId;

            //1. 显示模态框
            $("#roleAssingAuthModal").modal("show");
            //2. 获取json数据
            var  ajaxResult = $.ajax({
                "url":"assign/get/all/auth.json",
                "type":"post",
                "dataType":"json",
                "async":false
            });
            //3. 
            if (ajaxResult.responseJSON.result == "FAILED") {
                layer.msg(ajaxResult.responseJSON.message);
                return;
            }
            var zNodes = ajaxResult.responseJSON.data;

            //3. 初始化树形结构
            $.fn.zTree.init($("#assignRoleAuthDemo"),setting,zNodes);

            //4. 将树形结构展开
            var treeObj = $.fn.zTree.getZTreeObj("assignRoleAuthDemo");
            treeObj.expandAll(true);

            //5 查询以前分配过的authId
            ajaxResult = $.ajax({
                "url":"assinge/get/assinged/auth/id/list.json",
                "type":"post",
                "data":{
                    "roleId": $(this).attr("roleId"),
                    "random":Math.random() // 传入这个参数让浏览器不要缓存
                },
                "dataType":"json",
                "async":false
            });

            if (ajaxResult.responseJSON.result == "FAILED") {
                layer.msg(ajaxResult.responseJSON.message);
                return;
            }
            var authIdList = ajaxResult.responseJSON.data;

            for (var i = 0; i < authIdList.length; i++) {
                //遍历过程中获取每一个authId
                var authId = authIdList[i];
                //根据authId查询一个具体的树形节点
                // key : 表示查询节点的属性名
                //value: 表示查询节点的属性值，这里使用authId
                var  key = "id" ;
                var treeNode = $.fn.zTree.getZTreeObj("assignRoleAuthDemo").getNodeByParam(key,authId);
                //勾选找到的节点
                //treeNode ：当前要勾选的节点
                // true: 表示设置为勾选状态
                //false: 表示不联动
                $.fn.zTree.getZTreeObj("assignRoleAuthDemo").checkNode(treeNode,true,false);
            }
        });

        $("#roleAssingAuthBtn").click(function () {
            var authIdArray = new Array();

            //调用ZTree的方法获取已经被勾选的节点
            var checkedNodes = $.fn.zTree.getZTreeObj("assignRoleAuthDemo").getCheckedNodes();

            //遍历CheckedNodes
            for (var i = 0; i < checkedNodes.length; i++) {
                //获取具体的一个节点
                var node = checkedNodes[i];

                //获取当前节点的id属性
                var authId = node.id;

                //将authId 存入数组中
                authIdArray.push(authId);
            }

            var requestBody = {"roleIdList":[window.roleId],"authIdList":authIdArray};

            //发送请求
            var ajaxReulst = $.ajax({
                "url":"assgin/do/assgin.json",
                "type":"post",
                "data":JSON.stringify(requestBody),
                "contentType":"application/json;charset=UTF-8",
                "dataType":"json",
                "async":false
            });

            if (ajaxReulst.responseJSON.result == "SUCCESS") {
                layer.msg("操作成功");
            }
            if (ajaxReulst.responseJSON.result == "FAILED") {
                layer.msg(ajaxReulst.responseJSON.message);
            }

            //关闭模态框
            $("#roleAssingAuthModal").modal("hide");
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
<%@include file="include-modal-assign-auth.jsp"%>

</body>
</html>
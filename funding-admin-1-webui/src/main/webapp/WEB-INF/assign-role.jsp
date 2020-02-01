<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="UTF-8">
<%--引入头部，样式等--%>
<%@ include file="include-head.jsp" %>

<body>
<%--引入公共标题栏--%>
<%@ include file="include-nav.jsp" %>
<script type="text/javascript">
    $(function () {
        $("#rightBtn").click(function () {
            $("#leftSelect>option:selected").appendTo("#rightSelect");
        });
        $("#leftBtn").click(function () {
            $("#rightSelect>option:selected").appendTo("#leftSelect");
        });
        $("#submitBtn").click(function () {
            $("#rightSelect>option").prop("selected","selected")
        });
    })
</script>
<div class="container-fluid">
    <div class="row">

        <%--引入公共导航栏--%>
        <%@ include file="include-sidebar.jsp" %>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <!-- 各个页面具体内容 -->
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action="assign/role.html" method="post" role="form" class="form-inline">
                        <input type="hidden" name="adminId" value = ${param.adminId}>
                        <input type="hidden" name="pageNum" value = ${param.pageNum}>
                        <div class="form-group">
                            <label >未分配角色列表</label><br>
                            <select id="leftSelect" class="form-control" multiple size="10" style="width: 100px;overflow-y: auto;">
                                <c:forEach items="${requestScope.unAssignedRoleList}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-control">
                            <ul>
                                <li id="rightBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li><br>
                                <li id="leftBtn" class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top: 20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left: 40px">
                            <label >已分配角色列表</label><br>
                            <select id="rightSelect" name="roleIdList" class="form-control" multiple size="10" style="width: 100px;overflow-y: auto;">
                                <c:forEach items="${requestScope.assignedRoleList}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button id="submitBtn" type="submit" style="width: 200px;" class="btn btn-success btn-lg btn-block">分配</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>

<html lang="UTF-8">
<%--引入头部，样式等--%>
<%@ include file="include-head.jsp" %>

<body>
<%--引入公共标题栏--%>
<%@ include file="include-nav.jsp" %>

<div class="container-fluid">
    <div class="row">

        <%--引入公共导航栏--%>
        <%@ include file="include-sidebar.jsp" %>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">修改</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据</div>
                <div class="panel-body">
                    <form:form action="admin/update.html" method="post" modelAttribute="admin">
                        <input type="hidden" name="pageNum" value="${param.pageNum}"/>
                        <div class="form-group" hidden>
                            <label>id</label>
                            <form:input path="id" cssClass="form-control"></form:input>
                        </div>
                        <div class="form-group">
                            <label>登录账号</label>
                            <form:input path="loginAcct" cssClass="form-control"></form:input>
                        </div>
                        <div class="form-group">
                            <label>登录密码</label>
                            <form:input path="userPswd" cssClass="form-control"></form:input>
                        </div>
                        <div class="form-group">
                            <label>用户名称</label>
                            <form:input path="userName" cssClass="form-control"></form:input>
                        </div>
                        <div class="form-group">
                            <label>邮箱地址</label>
                            <form:input path="emai" cssClass="form-control"></form:input>
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>
                        <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i> 更新</button>
                        <button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
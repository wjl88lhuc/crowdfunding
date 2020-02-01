<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="UTF-8">
<%--引入头部，样式等--%>
<%@ include file="include-head.jsp" %>
<link rel="stylesheet" href="css/pagination.css"/>
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<script type="text/javascript" src="script/my-admin.js"></script>
<script>
    $(function () {
        layer.msg("hello layer....")

        //初始化全局变量
        window.totalRecord="${requestScope['PAGE-INFO'].total}" //声明变量存储总记录数
        window.pageSize="${requestScope['PAGE-INFO'].pageSize}"
        window.pageNum="${requestScope['PAGE-INFO'].pageNum}"
        window.keyword="${param.keyword}"
        //对分页导航条显示进行初始化
        initPagination()

        //全选，全不选择功能
        $("#sumaryBox").click(function () {
            //获取sumaryBox的当前状态。this代表当前的dom对象
            //this.checked  //checkStatus为true时表示被勾选,false表示没有被选中
            //根据checkStatus的 状态设置itemBox的状态
            $(".eacheItermBox").prop("checked", this.checked)

        });

        $(".uniqueRemoveBtn").click(function () {
            var adminId = $(this).attr("adminId") //获取当前元素的adminId值
            //获取当前元素的loginAcct
            var loginAcct = $(this).parents("tr").children("td:eq(2)").text()
            var confirResult = window.confirm("您真的要删除"+loginAcct+"这一条记录吗？")
            if (!confirResult){
                return;
            }
            var adminArray = new Array()
            adminArray.push(adminId)
            var requestBody = JSON.stringify(adminArray)
            //发送ajax请求，执行删除
            var pageNum = ${requestScope['PAGE-INFO'].pageNum};
            var keyword="${param.keyword}";
            removeAdminUser(requestBody);
        });

        $("#deleteBatchBtn").click(function () {
            var adminArray = new Array()
            var loginAcctArray = new Array()
            //首选定位到 每一个被选中的 eacheItermBox元素,然后遍历
            $(".eacheItermBox:checked").each(function () {
                //this.adminId 拿不到值的原因：this 作为dom对象 无法读取HTML本身没有的属性。只有jquery对象才能读取HTMl本身没有的对象。
                // 所以先把dom对象转换为jquery对象。然后调用jquery对象 的 attr方法来获取值
                var adminId = $(this).attr("adminId");
                //将每一个被选中的adminId存入到数组中
                adminArray.push(adminId);
                var loginAcct = $(this).parent("td").next().text();
                loginAcctArray.push(loginAcct);
            })
            var requestBody = JSON.stringify(adminArray)

            //检查数据是否有效，如果数组的长度为0 就不发送请求了
            if (adminArray.length == 0) {
                //给出提示
                alert("请勾选您要删除的数据");
                return;  //函数直接停止执行
            }
            //给出提示用户是否真的要删除数据
            var confirmResult = window.confirm("您真的要删除您选择的" + loginAcctArray.toString() + "吗？该操作可逆，请谨慎操作")
            if (!confirmResult) {//如果用户点击取消，直接结束方法的执行
                return
            }

            var pageNum = ${requestScope['PAGE-INFO'].pageNum};
            var keyword="${param.keyword}";
            //发送ajax请求将adminArray给controller处理
            removeAdminUser(requestBody);
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
                    <form id="queryForm" class="form-inline" role="form" style="float:left;"
                          action="admin/query/for/search.html" method="post">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input class="form-control has-success" name="keyword" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <!--
                        <button type="submit" class="btn btn-warning" onclick="$('#queryForm').submit()" ><i class="glyphicon glyphicon-search"></i> 查询</button>
                        -->
                        <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button id="deleteBatchBtn" type="button" class="btn btn-danger"
                            style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <a href="admin/to/add/page.html" class="btn btn-primary" style="float:right;">
                        <i class="glyphicon glyphicon-plus"></i> 新增
                    </a>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input id="sumaryBox" type="checkbox"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty requestScope['PAGE-INFO'].list}">
                                <tr>
                                    <td style="text-align: center" colspan="6">没有数据</td>
                                </tr>
                            </c:if>
                            <c:if test="${!empty requestScope['PAGE-INFO'].list}">
                                <c:forEach items="${requestScope['PAGE-INFO'].list}" var="admin" varStatus="status">
                                    <tr>
                                        <td>${status.count}</td>
                                        <!-- adminId 属性是我们自定义的属性-->
                                        <td><input adminId="${admin.id}" class="eacheItermBox" type="checkbox"></td>
                                        <td>${admin.loginAcct }</td>
                                        <td>${admin.userName }</td>
                                        <td>${admin.emai }</td>
                                        <td>
                                            <a href="assign/to/assign/role/page.html?adminId=${admin.id}&pageNum=${requestScope['PAGE-INFO'].pageNum}" class="btn btn-success btn-xs">
                                                <i class=" glyphicon glyphicon-check"></i>
                                            </a>
                                            <a href="admin/to/edit/page.html?adminId=${admin.id}&pageNum=${requestScope['PAGE-INFO'].pageNum}" class="btn btn-primary btn-xs">
                                                <i class=" glyphicon glyphicon-pencil"></i>
                                            </a>
                                            <button type="button" adminId="${admin.id}" class="uniqueRemoveBtn btn btn-danger btn-xs">
                                                <i class=" glyphicon glyphicon-remove"></i>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                            <tfoot>
                            <tr>
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

</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<div class="modal fade" id="roleAssingAuthModal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">尚筹网系统弹窗</h4>
            </div>
            <div class="modal-body">
                <%--显示树形结构的地方--%>
               <ul id="assignRoleAuthDemo" class="ztree"></ul>
            </div>
            <div class="modal-footer">
                <button id="roleAssingAuthBtn" type="button" class="btn btn-primary">分配</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
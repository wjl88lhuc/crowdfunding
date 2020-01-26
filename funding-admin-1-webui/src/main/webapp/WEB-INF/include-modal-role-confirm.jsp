<%--
  Created by IntelliJ IDEA.
  User: wenjunlong8
  Date: 2020/1/23 0023
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">尚筹网系统弹窗</h4>
            </div>
            <div class="modal-body">
                <p>您确定要出要删除下面显示的内容吗？</p>
                <table class="table  table-bordered">
                    <thead>
                    <tr>
                        <th width="30">#</th>
                        <th>账号</th>
                    </tr>
                    </thead>
                    <tbody id="confirmModalTableBody">

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="confirmBtn" type="button" class="btn btn-primary">ok</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="addConfirmModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form role="form">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="addModalLabel">尚筹网系统弹窗</h4>
                </div>
                <div class="modal-body">
                    <input id="roleNameInput" type="text" class="form-control" placeholder="请输入账号"/>
                </div>
                <div class="modal-footer">
                    <button id="addConfirmBtn" type="button" class="btn btn-success"><i class="glyphicon glyphicon-plus">保存</i></button>
                    <button id="addResetConfirmBtn" type="reset" class="btn btn-primary"><i class="glyphicon glyphicon-refresh">重置</i></button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

//更新弹出的模态框
<div class="modal fade" id="refreshConfirmModal" tabindex="-1" role="dialog" aria-labelledby="refreshModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form role="form">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="refreshModalLabel">尚筹网系统弹窗</h4>
                </div>
                <div class="modal-body">
                    <input id="refreshRoleNameInput" type="text" class="form-control" placeholder="请输入账号"/>
                </div>
                <div class="modal-footer">
                    <button id="refreshConfirmBtn" type="button" class="btn btn-success"><i class="glyphicon glyphicon-plus">更新</i></button>
                    <button type="reset" class="btn btn-primary"><i class="glyphicon glyphicon-refresh">重置</i></button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


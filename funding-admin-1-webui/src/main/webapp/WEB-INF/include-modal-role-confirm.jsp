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

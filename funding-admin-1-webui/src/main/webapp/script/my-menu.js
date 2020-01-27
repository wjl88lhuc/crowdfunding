//由view.view.addDiyDom 属性引用，负责显示自定义图标
// treeId 是 <ul id="treeDemo" class="ztree"></ul> 的 id属性值
//treeNode 对应的是每一个树形节点，也就是说每一个节点都是一个 treeNode 对象
function showMyIcon(treeId, treeNode) {
    //获取当前节点的id
    var currentNodeId = treeNode.tId;

    //在当前节点id之后附加 "_icon",就得到目标span的id
    var newClass = treeNode.icon;

    //获取新的class值用于替换
    var targetSpanId = currentNodeId + "_ico";

    //将目标span的旧class移除，添加新的class
    $("#" + targetSpanId).removeClass().addClass(newClass).css("background", "");

}

function initWholeZtree() {
    //setting对象中包含zTree设置的属性
    var setting = {
        view: {
            addDiyDom: showMyIcon,
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom
        },
        data: {
            key: {
                url: "notExistProperty"   //组织点击节点图标之后的跳转
            }
        }
    };

    //发送ajax请求，拿到树形结构zNodes的树结构
    $.ajax({
        "url": "menu/get/whole/tree.json",
        "type": "post",
        "dataType": "json",
        "success": function (response) {
            var result = response.result;
            if (result == "SUCCESS") {
                //zNodes 是用于显示真实树形结构的节点数据
                var zNodes = response.data;
                //执行树形结构的初始化操作
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            }
            if (result == "FAILED") {
                layer.msg("加载菜单失败: 原因是 " + response.message)
            }
        },
        "error": function (response) {
            layer.msg("加载菜单失败: 原因是 " + response.message)
        }
    });
}

//在鼠标移入节点范围内添加自定义组件
function addHoverDom(treeId, treeNode) {

    //在执行前先判断是否添加过，如果已经添加过就不再添加了
    //组装按钮组所在的span标签的 id
    var btnGrpSpanId = treeNode.tId + "_btnGrp";
    var btnGrpSpanLength = $("#" + btnGrpSpanId).length;
    if (btnGrpSpanLength > 0){
        return;
    }
    //由于按钮组是放在当前节点的超链接(<a>标签)后面，所以先定位到<a> 标签
    //按照id 生成贵族组装<a>标签的id，
    var anchorId = treeNode.tId + "_a";

    //调用已经封装函数生成按钮组
    var $btnGrpSpan = generateBtnGrp(treeNode);

    //在<a>标签后面追加按钮组
    $("#" + anchorId).after($btnGrpSpan);
}

//在鼠标移出节点范围时删除自定义组件
function removeHoverDom(treeId, treeNode) {
    //组装按钮组所在的span标签的 id
    var btnGrpSpanId = treeNode.tId + "_btnGrp";

    //删除id对应的元素
    $("#" + btnGrpSpanId).remove();


}

//专门生成按钮组的函数
function generateBtnGrp(treeNode) {
    //获取当前节点的id(HTML中li标签的id)
    var treeNodeId = treeNode.tId;

    //组装按钮组所在的span的id
    var btnGrpSpanId = treeNodeId + "_btnGrp";

    //获取当前节点在数据库中的id值
    var menuId = treeNode.id;

    //生成span标签对应的jquery对象
    var $span = $("<span id='" + btnGrpSpanId + "'></span>");

    //获取当前节点的level的值
    var level = treeNode.level;

    //声明三种按钮
    var addBtn = "<a onclick='showAddModal(this)' id='" + menuId + "' class='btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;'  title='添加子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg '></i></a>";
    var editBtn = "<a onclick='showEditModal(this)' id='" + menuId + "' class='btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;'  title='修改节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-edit rbg '></i></a>";
    var removeBtn = "<a onclick='showConfirmModal(this)' id='" + menuId + "' class='btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;'  title='删除节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg '></i></a>";

    //根据level的值进行判断
    if (level == 0) {
        $span.append(addBtn);
    }
    if (level == 1) {
        if (treeNode.children.length > 0) {
            $span.append(addBtn + " " + editBtn);
        } else {
            $span.append(addBtn + " " + editBtn + " " + removeBtn);
        }
    }
    if (level == 2) {
        $span.append(editBtn + " " + removeBtn);
    }
    return $span;
}

//在点击添加按钮时执行这个函数，打开模态框
function showAddModal(currentBtn) {
    $("#menuAddModal").modal("show");

    //将当前节点的id 存入全局变量
    window.menuId = currentBtn.id;
}

//在点击修改(编辑按钮)按钮时执行这个函数，打开模态框
function showEditModal(currentBtn) {
    $("#menuEditModal").modal("show");

    //回显
    //获取当前节点的id，存入全局变量
    window.menuId = currentBtn.id;

    //发送ajax请求，查询menu对象
    $.ajax({
        "url":"menu/get/" + window.menuId + ".json",
        "type":"get",
        "dataType":"json",
        "success":function (response) {
            var result = response.result;
            if (result == "SUCCESS") {
                //从响应对象中获取menu对象,
                var menu = response.data;
                var name = menu.name;
                var url = menu.url;
                var icon = menu.icon;
                //设置各个表单项
                $("#menuEditModal [name='name']").val(name);
                $("#menuEditModal [name='url']").val(url);

                //radio的menu值需要后端查询得到的值相同，回显被选中
                $("#menuEditModal [name='icon'][value='" + icon + "']").attr("checked",true);
            }
        },
        "error":function (response) {

        }
    });
}

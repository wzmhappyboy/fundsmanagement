var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    }
};
var ztree;

var vm = new Vue({
    el:'#rrapp',
    data:{
        showList: true,
        xmmx:true,
        manage:true,
        title: null,
        bxmxs:null,
        bxmxs2:null,
        home:true,
        dept:{
            parentName:null,
            parentId:0,
            orderNum:0
        }
    },
    methods: {
        getDept: function(){
            //加载项目树
            $.get(baseURL + "sys/dept/select", function(r){
                ztree = $.fn.zTree.init($("#deptTree"), setting, r.deptList);
                var node = ztree.getNodeByParam("deptId", vm.dept.parentId);
                ztree.selectNode(node);

                vm.dept.parentName = node.name;
            })
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.dept = {parentName:null,parentId:0,orderNum:0};
            vm.getDept();
        },
        mma: function(){
            var deptId =getDeptId();
            $.ajax({
                type: "POST",
                url:  "/management.html",
                data:{
                    "id":deptId,
                },
                success: function(data) {
                    vm.bxmxs=data.list;
                    vm.bxmxs2=data.list2;
                    vm.name=data.name;
                    vm.xmmx=false;
                    vm.home=false;
                },
                error:function (data) {
                    alert("系统错误");
                }
            });
        },
        manage2: function(){
            var deptId =getDeptId();

            $.ajax({
                type: "POST",
                url:  "/management.html",
                data:{
                    "id":deptId,
                },
                success: function(data) {
                    vm.bxmxs=data.list;
                    vm.bxmxs2=data.list2;
                    vm.name=data.name;
                    vm.manage=false;
                    vm.home=false;
                },
                error:function (data) {
                    alert("系统错误");
                }
            });
        },

        updateBxmx: function(id){
            $.ajax({
                type:"POST",
                url:   "/items",
                data:{"id":id},
                // dataType:"json",
                //async:true,
                success: function (data) {
                    vm.manage();
                },
                error:function (data) {
                    alert(data.responseText);
                }
            });

        },
        update: function () {
            var deptId = getDeptId();
            if(deptId == null){
                return ;
            }

            $.get(baseURL + "sys/dept/info/"+deptId, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.dept = r.dept;

                vm.getDept();
            });
        },
        del: function () {
            var deptId = getDeptId();
            if(deptId == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/dept/delete",
                    data: "deptId=" + deptId,
                    success: function(r){
                        if(r.code === 0){
                            alert('操作成功', function(){
                                vm.reload();
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.dept.deptId == null ? "sys/dept/save" : "sys/dept/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.dept),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        deptTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择部门",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级部门
                    vm.dept.parentId = node[0].deptId;
                    vm.dept.parentName = node[0].name;

                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.showList = true;
            vm.xmmx=true;
            vm.manage=true;
            vm.home=true;
            Dept.table.refresh();
        }
    }
});

var Dept = {
    id: "deptTable",
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Dept.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: '项目ID', field: 'deptId', visible: false, align: 'center', valign: 'middle', width: '80px'},
        {title: '项目名称', field: 'name', align: 'center', valign: 'middle', sortable: true, width: '180px'},
        {title: '上级项目', field: 'parentName', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '经费额度', field: 'orderNum', align: 'center', valign: 'middle', sortable: true, width: '100px'}]
    return columns;
};


function getDeptId () {
    var selected = $('#deptTable').bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        alert("请选择一条记录");
        return null;
    } else {
        return selected[0].id;
    }
}


$(function () {
    $.get(baseURL + "sys/dept/info", function(r){
        var colunms = Dept.initColumn();
        var table = new TreeTable(Dept.id, baseURL + "sys/dept/list", colunms);
        table.setRootCodeValue(r.deptId);
        table.setExpandColumn(2);
        table.setIdField("deptId");
        table.setCodeField("deptId");
        table.setParentCodeField("parentId");
        table.setExpandAll(false);
        table.init();
        Dept.table = table;
    });
});

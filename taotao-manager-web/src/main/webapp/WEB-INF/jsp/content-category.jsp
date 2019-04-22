<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	 <ul id="contentCategory" class="easyui-tree">  </ul>
</div>
<div id="contentCategoryMenu" class="easyui-menu" style="width:120px;" data-options="onClick:menuHandler">
    <div data-options="iconCls:'icon-add',name:'add'">添加</div>
    <div data-options="iconCls:'icon-remove',name:'rename'">重命名</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-remove',name:'delete'">删除</div>
</div>
<script type="text/javascript">

  //文档加载处理的逻辑
$(function(){

    // 在#contentCategory所在的标签中创建了一颗树
	$("#contentCategory").tree({
		url : '/content/category/list',
		animate: true,
		method : "GET",

        //右击鼠标触发
        onContextMenu: function(e,node){

		    //关闭原来的鼠标默认事件
            e.preventDefault();

            //选中 右击鼠标的节点
            $(this).tree('select',node.target);

            //展示我们的菜单栏
            $('#contentCategoryMenu').menu('show',{
                left: e.pageX, //鼠标的位置显示
                top: e.pageY   //鼠标的位置显示
            });
        },

        //在编辑之后触发
        onAfterEdit : function(node){

		    //获取树本身
        	var _tree = $(this);
        	if(node.id == 0){
        		// 新增节点
        		$.post("/content/category/create",{parentId:node.parentId,name:node.text},function(data){
        			if(data.status == 200){
        				_tree.tree("update",{
            				target : node.target,
            				id : data.data.id
            			});
        			}else{
        				$.messager.alert('提示','创建'+node.text+' 分类失败!');
        			}
        		});
        	}else{
        		$.post("/content/category/update",{id:node.id,name:node.text});
        	}
        }
	});
});

//处理菜单的点击事件
function menuHandler(item){

    //获取树节点
    var tree = $("#contentCategory");

    //获取被选中的节点 就是右击鼠标所在的节点
    var node = tree.tree("getSelected");


    //判断选择的是添加还是重命名还是删除
	if(item.name === "add"){
		tree.tree('append', {
            parent: (node?node.target:null),

            //数据
            data: [{
                text: '新建分类',
                id : 0,
                parentId : node.id  //新建节点的父节点的id
            }]
        }); 
		var _node = tree.tree('find',0);//根节点
		tree.tree("select",_node.target).tree('beginEdit',_node.target);
	}else if(item.name === "rename"){
		tree.tree('beginEdit',node.target);
	}else if(item.name === "delete"){
		$.messager.confirm('确认','确定删除名为 '+node.text+' 的分类吗？',function(r){
			if(r){ //如果是true 表示要执行以下逻辑
				$.post("/content/category/delete/",{id:node.id},function(){

				    //后台删除成功后，删除前端的节点
				    tree.tree("remove",node.target);
				});	
			}
		});
	}
}
</script>
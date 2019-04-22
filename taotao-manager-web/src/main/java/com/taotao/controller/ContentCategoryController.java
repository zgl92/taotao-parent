package com.taotao.controller;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 内容分类处理 controller
 *
 * @Author: zgl
 * @Date: 2019/4/21 14:28
 */

@Controller
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    /**
     * url : '/content/category/list',
     * animate: true,
     * method : "GET",
     * 参数  :  parentId
     */
    @RequestMapping(value = "/content/category/list", method = RequestMethod.GET)
    @ResponseBody
    public List<EasyUITreeNode> getContentCategoryList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        //1.引入服务
        //2.注入服务
        //3，调用
        return contentCategoryService.getContentCategoryList(parentId);
    }

    /**
     * 添加节点
     * @param parentId  新增父节点ID
     * @param name      新增节点的名字
     * @return
     */
    @RequestMapping(value = "/content/category/create", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createContentCategory(Long parentId,String name) {

        return contentCategoryService.createContentCategory(parentId, name);
    }

    /**
     *  更新节点
     * @param id    主键
     * @param name  更新节点名称
     * @return
     */
    @RequestMapping(value = "/content/category/update",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult updateContentCategory(long id,String name){

           return contentCategoryService.updateContentCategory(id,name);
    }

    /**
     *   删除节点
     * @param id     主键
     * @return
     */
    @RequestMapping(value = "/content/category/delete",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult deleteContentCategory(long id){

        return contentCategoryService.deleteContentCategory(id);
    }

}

package com.taotao.content.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

import java.util.List;

/**
 * @Author: zgl
 * @Date: 2019/4/21 14:08
 */
public interface ContentCategoryService {

    /**
     *  通过节点的Id查询该节点的子节点列表
     */
   public List<EasyUITreeNode> getContentCategoryList(Long parentId);

    /**
     * 创建内容分类
     * @param parentId 父节点的id
     * @param name   新增节点的名字
     * @return
     */
    public TaotaoResult createContentCategory(Long parentId,String name);

    /**
     * 更新节点
     * @param id   主键
     * @param name 更新节点的名称
     * @return
     */
    TaotaoResult updateContentCategory(long id, String name);

    /**
     * 删除节点
     * @param id   主键
     * @return
     */
    TaotaoResult deleteContentCategory(long id);
}

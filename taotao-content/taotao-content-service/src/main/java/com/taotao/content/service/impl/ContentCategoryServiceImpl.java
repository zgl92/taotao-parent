package com.taotao.content.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: zgl
 * @Date: 2019/4/21 14:15
 * 内容分类
 */

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getContentCategoryList(Long parentId) {
        //1.注入mapper

        //2.传教example
        TbContentCategoryExample example = new TbContentCategoryExample();

        //3.设置条件
        TbContentCategoryExample.Criteria criteria = example.createCriteria();

        // select * from tbContentCategory where parent_id=1
        criteria.andParentIdEqualTo(parentId);


        //4，执行查询
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);

        //5.转成EasyUITreeNode列表
        List<EasyUITreeNode> nodes = new ArrayList<>();
        for (TbContentCategory tbContentCategory : list) {
            EasyUITreeNode node = new EasyUITreeNode();

            node.setId(tbContentCategory.getId());
            node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
            //分类名称
            node.setText(tbContentCategory.getName());

            nodes.add(node);
        }

        return nodes;
    }

    @Override
    public TaotaoResult createContentCategory(Long parentId, String name) {

        //1.构建对象 补全属性
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setCreated(new Date());
        //新增的节点都是叶子节点
        tbContentCategory.setIsParent(false);
        tbContentCategory.setParentId(parentId);
        tbContentCategory.setName(name);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setStatus(1);
        tbContentCategory.setUpdated(tbContentCategory.getCreated());

        //2.插入数据
        tbContentCategoryMapper.insert(tbContentCategory);


        //3.判断如果要添加的节点的父节点本身叶子节点 需要更新其为父节点
        TbContentCategory parent = tbContentCategoryMapper.selectByPrimaryKey(parentId);

        //原本就是叶子节点
        if (parent.getIsParent() == false) {
            parent.setIsParent(true);

            //更新节点的 is_parent属性为true
            tbContentCategoryMapper.updateByPrimaryKeySelective(parent);
        }

        /**
         * 3.返回 TaotaoResult  包含内容分类的id 需要主键返回
         *   需要主键返回   修改 mybatis mapper.xml文件
         *   <selectKey keyProperty="id" order="BEFORE" resultType="long">
         *      SELECT LAST_INSERT_ID()
         *   </selectKey>
         */
        return TaotaoResult.ok(tbContentCategory);
    }

    @Override
    public TaotaoResult updateContentCategory(long id, String name) {
        //1.构建对象 补全属性
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setId(id);
        tbContentCategory.setName(name);
        tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteContentCategory(long id) {

        //查询 当前节点
        TbContentCategory category = tbContentCategoryMapper.selectByPrimaryKey(id);

        //1.查询此节点下面的所有的子节点
        List<TbContentCategory> list = tbContentCategoryMapper.getListByParentId(category.getId());


        //2. 若此节点下面没有子节点 需要把父节点的isparent改为false
        if (list.size() == 0) {
            category.setIsParent(false);
            tbContentCategoryMapper.updateByPrimaryKeySelective(category);
            //删除
            tbContentCategoryMapper.deleteByPrimaryKey(id);
            return TaotaoResult.ok();

        } else {

            //删除
            tbContentCategoryMapper.deleteByPrimaryKey(id);

            for (TbContentCategory tbContentCategory : list) {

                //判断 true=父类目  false=子目录
                if (tbContentCategory.getIsParent()) {

                    //有父类目 递归删除
                    this.deleteContentCategory(tbContentCategory.getId());

                } else {
                    //没有父目录了 删除
                    tbContentCategoryMapper.deleteByPrimaryKey(tbContentCategory.getId());

                }
            }
        }

        return TaotaoResult.ok();
    }
}
package com.taotao.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: zgl
 * @Date: 2019/4/22 22:05
 */

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Override
    public TaotaoResult saveContent(TbContent tbContent) {
        //1.注入mapper

        //2.补全属性
        tbContent.setCreated(new Date());
        tbContent.setUpdated(tbContent.getCreated());

        //3.插入内容表数据
        tbContentMapper.insertSelective(tbContent);

        return TaotaoResult.ok();
    }

    /**
     *
     * @param categoryId  id
     * @param page    当前页
     * @param rows    每页大小
     * @return
     */
    @Override
    public EasyUIDataGridResult selectTbContentList(Long categoryId, Integer page, Integer rows) {

        //设置分页信息
        if(page==null){
            page=1;
        }
        if(rows==null){
            rows=30;
        }
        //开始分页
        PageHelper.startPage(page, rows);

        //执行查询 创建条件
        TbContentExample example=new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);

        List<TbContent> tbContentList = tbContentMapper.selectByExample(example);

        //取分页信息
        PageInfo<TbContent> pageInfo = new PageInfo<>(tbContentList);


        //结果封装 创建返回结果对象
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal((int) pageInfo.getTotal());
        result.setRows(pageInfo.getList());

        return result;
    }

}

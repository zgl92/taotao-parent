package com.taotao.content.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;


/**
 * @Author: zgl
 * @Date: 2019/4/22 22:03
 *
 *  内容处理的接口
 */
public interface ContentService {

    /**
     *  插入内容表数据
     * @param tbContent
     * @return
     */
    public TaotaoResult saveContent(TbContent tbContent);

    /**
     *  查询内容集合
     * @param categoryId  id
     * @param page    当前页
     * @param rows    每页大小
     * @return
     */
    public EasyUIDataGridResult selectTbContentList(Long categoryId,Integer page, Integer rows);

}

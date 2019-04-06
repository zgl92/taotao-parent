package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;

/**
 * 商品接口
 * @author zgl
 */
public interface ItemService {
    /**
     * 查询商品列表
     * @param page 当前页
     * @param rows  每页显示大小
     * @return
     */
    public EasyUIDataGridResult getItemList(Integer page, Integer rows);
}

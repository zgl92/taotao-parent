package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: zgl
 * @Date: 2019/4/22 22:09
 * <p>
 * 处理内容表相关的请求
 */

@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;


    /**
     * 新增
     *
     * @param tbContent
     * @return
     */
    @RequestMapping(value = "/content/save", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult saveContent(TbContent tbContent) {
        //1.引入服务(dubbo)
        //2.注入服务
        //3.调用
        return contentService.saveContent(tbContent);
    }

    /**
     * 内容列表查询
     *
     * @param categoryId 内容id
     * @param page       当前页
     * @param rows       每页大小
     * @return
     */
    @RequestMapping(value = "/content/query/list", method = RequestMethod.GET)
    @ResponseBody
    public EasyUIDataGridResult contentQueryList(@RequestParam(value = "categoryId") Long categoryId,
                                                 @RequestParam(value = "page") Integer page,
                                                 @RequestParam(value = "rows") Integer rows) {

        return contentService.selectTbContentList(categoryId, page, rows);
    }
}

package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zgl
 * @Date: 2019/4/1 23:41
 *  显示页面
 */

@Controller
public class PageController {


    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }

    /**
     *  显示商品的查询页面
     *
     * url:/item-list
     *          -add
     * @return
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){

       return page;
    }

}

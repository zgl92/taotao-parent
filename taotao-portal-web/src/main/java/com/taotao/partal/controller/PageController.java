package com.taotao.partal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zgl
 * @Date: 2019/4/21 12:38
 * 展示首页
 */

@Controller
public class PageController {

    @RequestMapping("/index")
    public String showIndex() {
        return "index";
    }

}

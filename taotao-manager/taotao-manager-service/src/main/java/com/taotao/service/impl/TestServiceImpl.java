package com.taotao.service.impl;

import com.taotao.mapper.TestMapper;
import com.taotao.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: zgl
 * @Date: 2019/4/1 22:58
 */

@Service
public class TestServiceImpl implements TestService {


    @Autowired
    private TestMapper testMapper;
    /**
     * 查询当前的时间
     * @return
     */
    @Override
    public String queryNow() {


        return testMapper.queryNow();
    }
}

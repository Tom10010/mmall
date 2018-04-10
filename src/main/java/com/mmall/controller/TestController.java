package com.mmall.controller;

import com.mmall.dao.UserMapper;
import com.sun.jmx.snmp.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * Created by zhang on 2018/3/26.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private UserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    public static void main(String[] args) {
        //Timestamp a = new Timestamp(System.currentTimeMillis());
        Timestamp a = new Timestamp(System.currentTimeMillis());
        System.out.println(a);
        Date c = new Date();
        System.out.println(c);
    }

    @RequestMapping(value = "test.do")
    @ResponseBody
    public String test(String str){
        logger.info("testinfo");
        logger.warn("testwarm");
        logger.error("testerror");
        return "张洪涛是好人:506震惊部";
    }
}

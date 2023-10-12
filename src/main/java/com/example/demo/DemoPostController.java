package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.io.IOException;
import java.time.LocalDate;
import java.sql.Timestamp;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class DemoPostController {
    
    @Autowired
    private DemoService demoService;
    private static final Logger logger = LoggerFactory.getLogger(DemoPostController.class);

    @GetMapping(value = "/ping")
    public String getPing() {
        String response_val = "pong";
        System.out.println("Ping has been occurred");
        // Business Logic 추가
        return response_val;
    }

    @GetMapping(value = "/insertDepartment")
    public String insertDepartment(@RequestParam(value="deptId") Long deptId, @RequestParam(value="deptDescription") String deptDescription, @RequestParam(value="deptName") String deptName){
        logger.info("!!! insertDepartment Called !!!");
        return demoService.insertDepartment(deptId, deptDescription, deptName);
    }

    @GetMapping(value = "/insertUser")
    public String insertUser(@RequestParam(value="userId") Long userId, @RequestParam(value="userName") String userName, @RequestParam(value="userDescription") String userDescription, @RequestParam(value="deptId") Long deptId){
        logger.info("!!! insertUser Called !!!");
        return demoService.insertUser(userId, userName, userDescription, deptId);
    }

    @GetMapping(value = "/selectUserInfoByQuery")
    public String selectUserInfoByQuery(@RequestParam(value="userId") Long userId){
        logger.info ("!!! selectUserInfoByQuery called !!!");
        return demoService.selectUserByQuery(userId);
    }

    @GetMapping(value = "/selectUserInfoByJpa")
    public String selectUserInfoByJpa(@RequestParam(value="userId") Long userId){
        logger.info ("!!! selectUserInfoByJpa called !!!");
        return demoService.selectUserByJpa(userId);
    }
}
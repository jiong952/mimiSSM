package com.zjh.controller;

import com.zjh.pojo.Admin;
import com.zjh.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminAction {
    @Autowired
    AdminService adminService;
    @RequestMapping("/login")
    public String login(String name, String pwd, HttpServletRequest request){
        Admin admin = adminService.login(name, pwd);
        System.out.println(pwd);
        if(admin != null){
            request.setAttribute("admin",admin);
            System.out.println(admin);
            return "main";
        }else {
            request.setAttribute("errmsg","用户名或密码错误");
            return "login";
        }
    }
}

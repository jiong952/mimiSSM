package com.zjh.service.impl;

import com.zjh.mapper.AdminMapper;
import com.zjh.pojo.Admin;
import com.zjh.pojo.AdminExample;
import com.zjh.service.AdminService;
import com.zjh.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin login(String name, String pwd) {
        //使用AdminExample封装查询
        AdminExample example = new AdminExample();
        //增加name = 的条件
        example.createCriteria().andANameEqualTo(name);
        List<Admin> admins = adminMapper.selectByExample(example);
        //对比密码是否一致
        if(admins.size() > 0){
            //查到该用户名的用户
            Admin admin = admins.get(0);
            String md5_pwd = MD5Util.getMD5(pwd);
            if(md5_pwd.equals(admin.getaPass())){
                return admin;
            }
        }
        return null;
    }
}

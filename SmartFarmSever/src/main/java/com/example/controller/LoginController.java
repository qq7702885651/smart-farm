package com.example.controller;

import com.example.entity.Manager;
import com.example.service.ManagerService;
import com.example.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage/user")
public class LoginController {
    @Autowired
    ManagerService service;

    //实现登录功能
    @PostMapping("/login")
    public Result login(@RequestBody Manager manager) {
        Manager dbManager = service.getManagerByUsername(manager.getUsername());

        if (dbManager != null && dbManager.getPassw().equals(manager.getPassw())) {
            dbManager.setPassw(""); // 清除密码返回
            return Result.success(dbManager); // code = 200
        } else {
            return Result.error("用户名或密码错误"); // code = 500
        }
    }


}

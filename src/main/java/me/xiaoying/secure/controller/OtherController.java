package me.xiaoying.secure.controller;

import me.xiaoying.secure.Application;
import me.xiaoying.secure.constant.CommonConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class OtherController {
    @GetMapping("/getPassword")
    public String getPassword(HttpServletRequest request, String password) {
        if (!"PassAuthorize".equalsIgnoreCase(password))
            return null;
        Application.getLogger().info("获取密码: " + request.getRemoteAddr());
        return CommonConstant.PASSWORD;
    }
}
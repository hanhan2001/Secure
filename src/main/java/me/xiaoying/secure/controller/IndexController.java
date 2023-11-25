package me.xiaoying.secure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/login")
    public String index(HttpServletRequest response) {
        response.getCookies();
        return "index";
    }
}
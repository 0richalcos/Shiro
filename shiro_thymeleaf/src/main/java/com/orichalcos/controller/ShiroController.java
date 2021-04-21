package com.orichalcos.controller;

import com.orichalcos.entity.User;
import com.orichalcos.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Orichalcos
 */
@Controller
public class ShiroController {

    @Autowired
    private ShiroService shiroService;

    /**
     * 身份认证
     */
    @PostMapping("/login")
    public String login(String username, String password) {
        //获取主体对象
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
            return "redirect:index";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误！");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误！");
        }
        return "redirect:login";
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public String register(User user) {
        try {
            shiroService.register(user);
            return "redirect:login";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:register";
        }
    }
}

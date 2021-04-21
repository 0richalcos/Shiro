package com.orichalcos.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Orichalcos
 */
@Controller
public class PageController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @RequiresRoles("user")
    @GetMapping("/save")
    public String save() {
        return "save";
    }

    @GetMapping("/403")
    public String denied() {
        return "403";
    }
}

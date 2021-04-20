package com.orichalcos.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Orichalcos
 * 用于解决 shiroFilterFactoryBean.setUnauthorizedUrl("/403.jsp");不生效的问题
 * ·@ControllerAdvice：是一个Controller增强器，可对Controller中被@RequestMapping注解的方法加一些逻辑处理，
 * 最常用的就是异常处理、全局数据绑定、全局数据预处理
 * ·@Order或者Ordered接口：定义SpringIoC容器中Bean的执行顺序的优先级，而不是定义Bean的加载顺序，
 * Bean的加载顺序不受@Order或Ordered接口的影响
 * ·@ExceptionHandler：统一异常处理器
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AdviceController {
    @ExceptionHandler(AuthorizationException.class)
    public String authorizationException(Exception e) {
        return "403";
    }
}
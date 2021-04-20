package com.orichalcos.service;

import com.orichalcos.entity.User;

/**
 * @author Orichalcos
 */
public interface ShiroService {
    /**
     * 注册用户
     *
     * @param user 用户
     */
    void register(User user);

    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    User findUserByUsername(String username);
}
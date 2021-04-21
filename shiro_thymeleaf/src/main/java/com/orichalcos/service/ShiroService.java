package com.orichalcos.service;

import com.orichalcos.entity.Perms;
import com.orichalcos.entity.Role;
import com.orichalcos.entity.User;

import java.util.List;

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

    /**
     * 根据用户名查询所有角色
     *
     * @param username 用户名
     * @return 角色集合
     */
    List<Role> findRolesByUsername(String username);

    /**
     * 根据角色Id查询所有权限
     *
     * @param id 角色Id
     * @return 权限集合
     */
    List<Perms> findPermsByRoleId(String id);
}
package com.orichalcos.mapper;

import com.orichalcos.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Orichalcos
 */
@Mapper
public interface ShiroMapper {
    /**
     * 保存用户
     *
     * @param user 用户
     */
    void save(User user);

    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    User findUserByUsername(String username);
}
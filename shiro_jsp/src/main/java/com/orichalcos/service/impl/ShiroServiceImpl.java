package com.orichalcos.service.impl;

import com.orichalcos.entity.Perms;
import com.orichalcos.entity.Role;
import com.orichalcos.entity.User;
import com.orichalcos.mapper.ShiroMapper;
import com.orichalcos.service.ShiroService;
import com.orichalcos.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Orichalcos
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private ShiroMapper shiroMapper;

    @Override
    public void register(User user) {
        //生成随机盐
        String salt = SaltUtils.getSalt(8);
        //将随机盐保存到user中
        user.setSalt(salt);
        //对明文密码进行MD5+salt+Hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), ByteSource.Util.bytes(user.getSalt()), 1024);
        user.setPassword(md5Hash.toHex());
        shiroMapper.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return shiroMapper.findUserByUsername(username);
    }

    @Override
    public List<Role> findRolesByUsername(String username) {
        return shiroMapper.findRolesByUsername(username);
    }

    @Override
    public List<Perms> findPermsByRoleId(String id) {
        return shiroMapper.findPermsByRoleId(id);
    }
}
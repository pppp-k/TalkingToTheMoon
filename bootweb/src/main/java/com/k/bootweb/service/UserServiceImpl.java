package com.k.bootweb.service;

import com.k.bootweb.mapper.UserMapper;
import com.k.bootweb.pojo.dao.User;
import com.k.bootweb.utils.Tools;

import com.k.bootweb.utils.constant.TaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public int updateUserInfo(User user) {
//        if (null == user.getUid())
//            throw BusinessException.withErrorCode("用户编号不可能为空");
        return userMapper.updateUserInfo(user);
    }


//    @Override
//    public User login(String username, String password) {
////        if (StringUtils.isBlank(username) || StringUtils.isBlank(password))
////            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_IS_EMPTY);
//
//        String pwd = TaleUtils.md5(username +password);
//        User user = userMapper.getUserInfoByCond(username,pwd);
////        if (null == user)
////            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_ERROR);
//
//        return user;
//    }

    @Override
    public Map<String, String> register(User user) {
        Map<String, String> map = new HashMap<>();

        // 空值处理
        if (user == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        if (StringUtils.isBlank(user.getUsername())) {
            map.put("usernameMsg", "账号不能为空!");
            return map;
        }
        if (StringUtils.isBlank(user.getPassword())) {
            map.put("passwordMsg", "密码不能为空!");
            return map;
        }
        if (StringUtils.isBlank(user.getEmail())) {
            map.put("emailMsg", "邮箱不能为空!");
            return map;
        }

        // 验证账号
        User u = userMapper.selectByName(user.getUsername());
        if (u != null) {
            map.put("usernameMsg", "该账号已存在!");
            return map;
        }

        // 验证邮箱
        u = userMapper.selectByEmail(user.getEmail());
        if (u != null) {
            map.put("emailMsg", "该邮箱已被注册!");
            return map;
        }
        // 注册用户
        user.setSalt(TaleUtils.generateUUID().substring(0, 5));
        user.setPassword(TaleUtils.md5(user.getPassword() + user.getSalt()));
        userMapper.insertUser(user);
//        System.out.println(user);

        return map;
    }

    @Override
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<>();

        // 空值处理
        if (StringUtils.isBlank(username)) {
            map.put("usernameMsg", "账号不能为空!");
            return map;
        }
        if (StringUtils.isBlank(password)) {
            map.put("passwordMsg", "密码不能为空!");
            return map;
        }

        // 验证账号
        User user = userMapper.selectByName(username);
        if (user == null) {
            map.put("usernameMsg", "该账号不存在!");
            return map;
        }


        // 验证密码
        password = TaleUtils.md5(password + user.getSalt());
        if (!user.getPassword().equals(password)) {
            map.put("passwordMsg", "密码不正确!");
            return map;
        }

        return map;
    }
}

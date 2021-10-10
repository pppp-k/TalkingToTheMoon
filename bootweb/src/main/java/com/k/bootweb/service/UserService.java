package com.k.bootweb.service;

import com.k.bootweb.pojo.dao.User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {

    int updateUserInfo(User user);

    Map<String, String> register(User user);

    Map<String, Object> login(String username, String password);
}

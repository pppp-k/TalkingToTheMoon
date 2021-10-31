package com.k.bootweb.controller;

import com.k.bootweb.mapper.UserMapper;
import com.k.bootweb.pojo.dao.User;
import com.k.bootweb.pojo.dto.Result;
import com.k.bootweb.service.UserService;
import com.k.bootweb.utils.RedisKeyUtil;
import com.k.bootweb.utils.constant.CommunityConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Api("登录相关接口")
@RestController
@CrossOrigin(origins = "*")
public class UserController implements CommunityConstant {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/queryUser")
    public User queryUser(Integer id){
        User user=userMapper.selectById(id);
        return user;
    }

    @GetMapping("/queryUser1")
    public User queryUser1(String username){
        User user=userMapper.selectByName(username);
        return user;
    }

    @GetMapping("/queryUser2")
    public User queryUser2(String email){
        User user=userMapper.selectByEmail(email);
        return user;
    }


    @ApiOperation("登录")
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public Result login(String username, String password, boolean rememberme, HttpServletResponse response) {
//
//        String kaptcha = null;
//        if (StringUtils.isNotBlank(ticket)) {
//            kaptcha = (String) redisTemplate.opsForValue().get(ticket);
//        }
        int expiredSeconds = rememberme ? REMEMBER_EXPIRED_SECONDS : DEFAULT_EXPIRED_SECONDS;
        Map<String, Object> map = userService.login(username, password,expiredSeconds);
        if (StringUtils.isNotBlank((CharSequence) map.get("ticket"))) {
            Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
            cookie.setPath("/");
            cookie.setMaxAge(expiredSeconds);
            response.addCookie(cookie);
            return Result.success("登录成功");
        } else {
            if(StringUtils.isNotBlank((CharSequence) map.get("usernameMsg"))){
                return Result.fail((String) map.get("usernameMsg"));
            }
            else if(StringUtils.isNotBlank((CharSequence) map.get("passwordMsg"))){
                return Result.fail((String) map.get("passwordMsg"));
            }
            else
                return null;
        }

    }

    @PostMapping(path = "/register")
    public Result register(String username,String password,String email)
    {
//        System.out.println(username+"/ "+password+" /"+email);
        User user=new User(username,password,email);
//        System.out.println(user);
        Map<String, String> map = userService.register(user);
        if (map == null || map.isEmpty()) {
                return Result.success("注册成功");
        } else {
            if(StringUtils.isNotBlank(map.get("usernameMsg"))){
                return Result.fail(map.get("usernameMsg"));
            }
            else if(StringUtils.isNotBlank( map.get("passwordMsg"))){
                return Result.fail(map.get("passwordMsg"));
            }
            else if(StringUtils.isNotBlank(map.get("emailMsg")))
                return Result.fail(map.get("emailMsg"));
            else
                return null;
        }
    }
}

package com.k.bootweb.controller;

import com.k.bootweb.mapper.UserMapper;
import com.k.bootweb.pojo.dao.User;
import com.k.bootweb.pojo.dto.Result;
import com.k.bootweb.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api("登录相关接口")
@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

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

//    @ApiOperation("登录")
//    @PostMapping(value = "/login")
//    @ResponseBody
//    public Result toLogin(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            @ApiParam(name = "username", value = "用户名", required = true)
//            @RequestParam(name = "username", required = true)
//                    String username,
//            @ApiParam(name = "password", value = "密码", required = true)
//            @RequestParam(name = "password", required = true)
//                    String password,
//            @ApiParam(name = "remeber_me", value = "记住我", required = false)
//            @RequestParam(name = "remeber_me", required = false)
//                    String remeber_me
//    ){
//
//        try {
//            User userInfo = userService.login(username, password);
//            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, userInfo);
//            if (StringUtils.isNotBlank(remeber_me)) {
//                TaleUtils.setCookie(response, userInfo.getUid());
//            }
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//            String msg = "登录失败";
//            return Result.fail(msg);
//        }
//
//        return Result.success("登录成功");
//
//    }

    @ApiOperation("登录")
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public Result login(String username, String password, boolean rememberme) {
        Map<String, Object> map = userService.login(username, password);
        if (map == null || map.isEmpty()) {
            return Result.success("登录成功");
        } else {
            if(StringUtils.isNotBlank((String)map.get("usernameMsg"))){
                return Result.fail((String) map.get("usernameMsg"));
            }
            else if(StringUtils.isNotBlank((CharSequence) map.get("passwordMsg"))){
                return Result.fail((String) map.get("passwordMsg"));
            }
            else
                return null;
        }
//        if (map.containsKey("ticket")) {
//            Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
//            cookie.setPath(contextPath);
//            cookie.setMaxAge(expiredSeconds);
//            response.addCookie(cookie);
//            return "redirect:/index";
//        } else {
//            model.addAttribute("usernameMsg", map.get("usernameMsg"));
//            model.addAttribute("passwordMsg", map.get("passwordMsg"));
//            return "/site/login";
//        }
    }

    @PostMapping(path = "/register")
    public Result register(String username,String password,String email)
    {
        System.out.println(username+"/ "+password+" /"+email);
        User user=new User(username,password,email);
        System.out.println(user);
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

package com.k.bootweb;

import com.k.bootweb.mapper.TiebaMapper;
import com.k.bootweb.mapper.UserMapper;
import com.k.bootweb.pojo.dao.User;
import com.k.bootweb.utils.constant.TaleUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootWebApplicationTests {

    @Autowired
    private TiebaMapper tiebaMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        User user=userMapper.selectById(1001);
        System.out.println(user);
    }


}

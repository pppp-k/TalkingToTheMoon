package com.k.bootweb;

import com.k.bootweb.mapper.TiebaMapper;
import com.k.bootweb.mapper.UserMapper;
import com.k.bootweb.pojo.dao.User;
import com.k.bootweb.utils.constant.TaleUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.File;
import java.io.IOException;

@SpringBootTest
class BootWebApplicationTests {

    @Autowired
    private TiebaMapper tiebaMapper;


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("mykey","k");
        System.out.println(redisTemplate.opsForValue().get("mykey"));
    }

    @Test
    void test2(){
        System.out.println(tiebaMapper.updateCommentCount(1, 1));
    }

    @Test
    void Test1(){
        try {
            //1.加载配置文件
            ClientGlobal.init("fdfs_client.conf");
            //2.创建一个TrackerClient对象
            TrackerClient trackerClient=new TrackerClient();
            //3.使用TrackerClient对象获得Trackerserver对象
            TrackerServer trackerServer=trackerClient.getTrackerServer();
            //4。创建一个StorageClient对象
            StorageClient storageClient=new StorageClient(trackerServer,null);
            String path=System.getProperty("user.dir")+ File.separator+"1.png";
            //5.使用StorageClient对象下载文件
            storageClient.download_file("group1","M00/00/00/CIIf2GFzqXWATPzmAAgwsRqB08A752.png",path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

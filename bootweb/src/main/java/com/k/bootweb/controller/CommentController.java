package com.k.bootweb.controller;


import com.k.bootweb.pojo.dao.Comment;
import com.k.bootweb.pojo.dto.Result;
import com.k.bootweb.service.CommentService;
import com.k.bootweb.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = "/addcomment", method = RequestMethod.POST)
    public Result addComment(@RequestBody  Comment comment) {
        comment.setUser_id(hostHolder.getUser().getUid());
        comment.setStatus(0);
        comment.setCreate_time(new Date());
        commentService.addComment(comment);

        return Result.success("添加成功");
    }
}

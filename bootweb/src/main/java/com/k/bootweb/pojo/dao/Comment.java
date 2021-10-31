package com.k.bootweb.pojo.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    //评论编号
    private int comment_id;
    //用户账号
    private int user_id;
    //实体类型（1为帖子，2为评论）
    private int entity_type;
    //实体编号
    private int entity_id;
    //回复目标编号（如果是回复他人的评论，就为他人的uid）
    private int target_id;
    //评论内容
    private String comment_content;
    //帖子状态（0正常/1精华/2拉黑）
    private int status;
    //评论时间
    private Date create_time;
    //评论点赞数
    private int comment_likes;
}
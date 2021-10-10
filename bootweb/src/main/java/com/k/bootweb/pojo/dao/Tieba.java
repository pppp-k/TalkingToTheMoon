package com.k.bootweb.pojo.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tieba {
    private int uid;
    private String username;
    private String title;
    private String summary;
    private int like;
    private int comment;

}

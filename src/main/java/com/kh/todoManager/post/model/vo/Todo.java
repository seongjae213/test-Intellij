package com.kh.todoManager.post.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class Todo {
    private int no;
    private String userId;
    private String content;
    private int status;
    private Date createDate;
}

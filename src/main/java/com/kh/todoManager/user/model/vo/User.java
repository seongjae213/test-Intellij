package com.kh.todoManager.user.model.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class User {
	private int userNo;
	private String userId;
	private String userPwd;
	private String nickname;
	private String email;
	private Date joinDate;

}

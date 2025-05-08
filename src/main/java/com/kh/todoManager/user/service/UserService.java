package com.kh.todoManager.user.service;

import com.kh.todoManager.user.model.vo.User;

public interface UserService {

	/* 아이디 중복체크 */
	public boolean checkId(String id);

	/* 회원가입 */
	public int enroll(User enrollUser);

	/* 로그인 */
	public User login(User loginUser);
	
	/* 회원 정보 조회 */
	
	/* 회원 탈퇴 */
	
	
	
}

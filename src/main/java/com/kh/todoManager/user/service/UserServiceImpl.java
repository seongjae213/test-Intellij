package com.kh.todoManager.user.service;

import org.springframework.stereotype.Service;

import com.kh.todoManager.user.model.dao.UserDAO;
import com.kh.todoManager.user.model.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserDAO uDAO;
	
	@Override
	public boolean checkId(String id) {
		boolean result = true;
		
		//DB에서 아이디와 일치하는 개수를 조회하여 0인 경우는 true 리턴, 0보다 큰 경우 false 리턴
		if(uDAO.countByUserId(id) == 0) return true;
		else return false;
	}

	@Override
	public int enroll(User enrollUser) {
		return uDAO.enroll(enrollUser);
	}

	@Override
	public User login(User loginUser) {
		return uDAO.login(loginUser);
	}

}

package com.kh.todoManager.user.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.todoManager.user.model.vo.User;

@Mapper
public interface UserDAO {
	// class => 빈 등록 : @Repository, DB연동 : SqlSession

	// class => 빈 등록 : @Mapper, DB연동 : 추상메소드 정의, mapper.xml파일 namespace에 해당 인터페이스 전체 경로 설정
	
	int countByUserId(String id);

	int enroll(User enrollUser);

	User login(User loginUser);

}

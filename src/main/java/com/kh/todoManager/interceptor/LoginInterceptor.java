package com.kh.todoManager.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.todoManager.user.model.vo.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		User loginUser = (User)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			return true;
		}else {
//			response.setStatus(401);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().print("로그인 후 이용 가능합니다.");
			return false;
		}
		
	}
	
	

}

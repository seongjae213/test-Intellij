package com.kh.todoManager.user.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kh.todoManager.user.model.vo.User;
import com.kh.todoManager.user.service.MailService;
import com.kh.todoManager.user.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

//@CrossOrigin("http://localhost:5173")	=> 잘 안씀 config로 분류해서 받음

@RestController	// => @Controller + @ResponseBody
@RequiredArgsConstructor
public class UserController {
	
	private final MailService mailService;
	private final UserService uSer;
	
	/**
	 * 이메일은 전달받아 인증코드를 메일로 전송
	 * @param email
	 * @return "ok" : 인증코드 발송, "nok" : 인증코드 발송 실패
	 */
	@PostMapping("/email/send")
	public String sendEmailCode(@RequestBody Map<String, Object> requestBody) {
		/*
		 * 폼 요청 시 전송 형식과 axios(fetch) 요청 시 전송 형식이 달라 요청 본문에서 전달데이터를 추출해야 함
		 * 
		 * @RequestBody : 요청 본문. 전송 형식이 application/json
		 * @RequestParam : 쿼리 파라미터 또는 폼 데이터. 단일 데이터 처리
		 * @ModelAttribute : 쿼리 파라미터 또는 폼 데이터. 객체 바인딩 처리
		 */
		
		String email = (String)requestBody.get("email");
		System.out.println("email::" + email);
		
		try {
			mailService.sendMail(email);
		} catch(MessagingException e) {
			return "nok";
		}
		
		return "ok";	//TODO
	}
	
	/**
	 * 
	 * @param requestBody {email:이메일정보, code:인증코드}
	 * @return success:검증 성공, failed:검증실패
	 */
	@PostMapping("/email/verify")
	public String verifyEmailCode(@RequestBody Map<String, Object> requestBody) {
		String email = (String)requestBody.get("email");
		String code = (String)requestBody.get("code");
		
		System.out.println(email);
		System.out.println(code);
		
		boolean result = mailService.verifyCode(email, code);
		
		return result? "success":"failed";
	}
	
	/**
	 * 아이디 중복체크
	 * [POST] / checkId
	 * @param id 아이디
	 * @return "NNNNN" : 중복된 아이디, "NNNNY" : 사용 가능 아이디
	 */
	@PostMapping("/id/check")
	public String checkId(@RequestBody Map<String, Object> requestBody) {
		
		String id = (String)requestBody.get("id");
		
		boolean result = uSer.checkId(id);
		
		return result ? "NNNNY" : "NNNNN"; 
	}
	
	@PostMapping("/enroll")
	public String enroll(@RequestBody Map<String, Object> requestBody) {
		String id = (String)requestBody.get("id");
		String pwd = (String)requestBody.get("pwd");
		String nickname = (String)requestBody.get("nickName");
		String email = (String)requestBody.get("email");
		User enrollUser = new User();
		enrollUser.setUserId(id);
		enrollUser.setNickname(nickname);
		enrollUser.setEmail(email);
		enrollUser.setUserPwd(pwd);
		
		System.out.println(enrollUser);
		
		int result = uSer.enroll(enrollUser);
		if(result > 0) return "success";
		else return "fail";
		
	}
	
	/**
	 * 로그인 (회원 정보 조회)
	 * [POST] /login
	 * @param UserDTO 로그인 정보 { userId: 아이디, userPwd: 비밀번호 }
	 * @return "success" : 로그인 성공, "failed" : 로그인 실패
	 * 	로그인 성공 시, 세션에 사용자 정보를 저장
	 * 
	 */
	@PostMapping("login")
	public String userLogin(@RequestBody Map<String, Object> requestBody, HttpSession session) {
		String id = (String)requestBody.get("userId");
		String pwd = (String)requestBody.get("userPwd");
		
		User user = new User();
		user.setUserId(id);
		user.setUserPwd(pwd);
		
		User loginUser = uSer.login(user);
		System.out.println(loginUser);
		
		if(loginUser != null) {
			session.setAttribute("loginUser", loginUser);
			return "success";
		}else {
			return "failed";
		}
	}

	@PostMapping("login1")
	public String user1Login(@RequestBody Map<String, Object> requestBody, HttpSession session) {
		String id = (String)requestBody.get("userId");
		String pwd = (String)requestBody.get("userPwd");

		User user = new User();
		user.setUserId(id);
		user.setUserPwd(pwd);

		User loginUser = uSer.login(user);
		System.out.println(loginUser);

		if(loginUser != null) {
			session.setAttribute("loginUser", loginUser);
			return "success";
		}else {
			return "failed";
		}
	}
	
}

































package net.softsociety.subject1.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	//홈화면 컨트롤러
	@GetMapping({"", "/"})
	public String home(HttpServletResponse response) {
		//쿠키 선언
		Cookie customerID = new Cookie("id", "gildong111");
		Cookie customerName = new Cookie("name", "홍길동"); 
		Cookie customerAddr = new Cookie("address", "서울시강남구");
		
		//쿠키 유지 시간 (3일)
		customerID.setMaxAge(60 * 60 * 24 * 3);
		customerName.setMaxAge(60 * 60 * 24 * 3);
		customerAddr.setMaxAge(60 * 60 * 24 * 3);
		
		//쿠키 저장하기
		response.addCookie(customerID);
		response.addCookie(customerName);
		response.addCookie(customerAddr);
		
		//저장되었는가 확인하기
		log.debug("저장된 쿠키1 : {}", customerID.toString());
		log.debug("저장된 쿠키2 : {}", customerName.toString());
		log.debug("저장된 쿠키3 : {}", customerAddr.toString());
		
		return "home";
	}
}

package net.softsociety.spring2.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CookieController {
	//쿠키 저장
	@GetMapping("/ck/cookie1")
	public String cookie1(HttpServletResponse response) {
		//cookie 생성
		Cookie c1 = new Cookie("str", "abc"); //이름, 값
		Cookie c2 = new Cookie("num", "123");
		
		//cookie의 자동 삭제 시간 (수명. 단위: 초)
		c1.setMaxAge(60 * 60 * 24 * 3); // 3일
		c2.setMaxAge(60 * 60 * 24 * 3); 
		
		//cookie 저장
		response.addCookie(c1);
		response.addCookie(c2);
		
		//테스트용 코드
		System.out.println("Cookie 저장됨.");
		return "redirect:/";
	}
	//쿠키 삭제
	@GetMapping("/ck/cookie2")
	public String cookie2(HttpServletResponse response) {
		//쿠키를 삭제하는 메소드는 따로 없다.
		//이미 저장된 쿠키와 같은 이름을 가진 쿠키를 생성
		Cookie c1 = new Cookie("str", "abc");
		Cookie c2 = new Cookie("num", "0");
		
		//쿠키의 수명을 0초로 지정
		c1.setMaxAge(0);
		c2.setMaxAge(0);
		
		//쿠키를 클라이언트에 보낸다.
		response.addCookie(c1);
		response.addCookie(c2);
		
		System.out.println("삭제완료");
		System.out.println(c1.getValue());
		System.out.println(c2.getValue());
		return "redirect:/";
	}
	//쿠키 읽기
	@GetMapping("/ck/cookie3")
	public String cookie3(
			@CookieValue(name="str", defaultValue="없음") String str,
			@CookieValue(name="num", defaultValue="0") int num
			) {
		System.out.println("쿠키값1 : " + str);
		System.out.println("쿠키값2 : " + num);
		return "redirect:/";
	}
	
	//방문횟수 카운트
	@GetMapping("ex/cookie")
	public String cookie(HttpServletResponse response, @CookieValue(name="count", defaultValue="0") int count, Model model) {
		//방문 횟수 저장값 확인용 
//		System.out.println("방문 횟수 : " + count);
		
		//기존 쿠키에 새로 덮어씌울 새로운 방문횟수를 문자열화 시킨다.
		String sCount = Integer.toString(count + 1);
		
		//html에 가져가기 위해 model화(만약 한 페이지가 아닌 여기 저기서 사용하려면 session화 해야한다. model은 한 페이지에서만 사용하고 없어짐)
		model.addAttribute("count", count);
		
		
		//기존 쿠키와 같은 이름의 쿠키에 새 방문횟수 값을 넣어 생성한다.
		Cookie cookie = new Cookie("count", sCount);
		
		//쿠키의 수명
		cookie.setMaxAge(60*60*24*3);
		
		//새로운 쿠키 저장
		response.addCookie(cookie);
		
		return "visitCookie";
	}
}

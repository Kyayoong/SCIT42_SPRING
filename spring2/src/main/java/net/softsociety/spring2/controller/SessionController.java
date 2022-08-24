package net.softsociety.spring2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 세션을 처리하기위한 컨트롤러 클래스
 */
@Controller
public class SessionController {
	
	/**
	 * 세션에 값을 저장한다.
	 * @param session 세션객체
	 * @return redirect to index
	 */
	@GetMapping("ss/session1")
	public String session1(HttpSession session) {
		session.setAttribute("id", "abc");
		return "redirect:/";
	}
	
	
	@GetMapping("ss/session2")
	public String session2() {
		return "session";
	}
	
	
	@GetMapping("ss/session3")
	public String session3(HttpSession session) {
		session.removeAttribute("id");
		return "redirect:/";
	}
	
	@GetMapping("ss/session4")
	public String session4(HttpSession session) {
		
		if(session.getAttribute("id") == null) {
			return "redirect:/";
		}
		return "session4";
	}
	
}

//두번째. test 페이지를 위한 컨트롤러
package net.softsociety.spring1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	
	@GetMapping("test")
	public String test() {
		return "test";
	}
}

package net.softsociety.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("js")
@Slf4j
public class JavaScriptController {
	@GetMapping("js1")
	public String js1() {
		return "/jsView/js1";
	}
	
	@GetMapping("js2")
	public String js2() {
		return "/jsView/js2";
	}
	
	@GetMapping("js3")
	public String js3() {
		return "/jsView/js3";
	}
		
	//모바일 화면 넘어가기
	@GetMapping("mobile")
	public String mobile() {
		return "/jsView/mobile";
	}
}

package net.softsociety.spring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring2.vo.Member;

@Slf4j
@Controller
public class lombokController {
	@GetMapping("/log/lombok")
	public String lombokTest() {
		Member m = new Member();
		
		m.setId("김아무개");
		m.setName("김구구");
		m.setPassword("1111");
		m.setAddress("삼성동");
		m.Test();
		
		System.out.println(m.toString());
		return "redirect:/";
	}
	
	@GetMapping("/log/logger")
	public String logTest() {
		System.out.println("System.out.println()으로 출력");
		String s= "변수내용";
		log.error("error()로 출력" + s);
		log.warn("warn()으로 출력 {} {}", s,100);
		log.info("info()로 출력");		//기록남기기용
		log.debug("debug()로 출력");		//테스트용... 나중에 지워야할것듯..
		log.trace("trace()로 출력");
		return "redirect:/";
	}
}

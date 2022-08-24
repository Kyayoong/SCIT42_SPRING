package net.softsociety.spring2.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.softsociety.spring2.vo.Member;

@RequestMapping("/th") //모든 경로에 중간경로 삽입하기 이러면 thymeleaf1이 th/thymeleaf1로 적용된다.
@Controller
public class thymeleafController {
	
	@GetMapping("thymeleaf1")
	public String thymeleaf1(Model model) {
		String str = "문자열";
		int num = 100;
		Member member = new Member("aaa", "111", "홍길동", "서울시");
		String tag = "<marquee>html태그</marquee>";
		String url = "https://google.com";
		
		model.addAttribute("str", str);
		model.addAttribute("num", num);
		model.addAttribute("member", member);
		model.addAttribute("tag", tag);
		model.addAttribute("url", url);
		
		int n1 = 1000000;
		double n2 = 123.34567;
		Date d = new Date();
		model.addAttribute("inum", n1);
		model.addAttribute("dnum", n2);
		model.addAttribute("date", d);
		
		return "th/thymeleaf1";
	}
	
	@GetMapping("thymeleaf2")
	public String thymeleaf2(Model model) {
		String str = "모코모코";
		int num = 1;
		String values = "Java, HTML, CSS";
		ArrayList<String> list = new ArrayList<>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", "keyboard");
		map.put("price", "10000");
		
		model.addAttribute("str", str);
		model.addAttribute("num", num);
		model.addAttribute("values", values);
		model.addAttribute("list", list);
		model.addAttribute("map", map);
		
		return "th/thymeleaf2";
	}

}

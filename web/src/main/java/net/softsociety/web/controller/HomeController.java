package net.softsociety.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping({"", "/"})
	public String home(Model model) {
		//ajax 게시글 추천수 올리는 예제를 위해 게시글 번호를 미리 정해서 건내준다.
		int boardnum = 1;
		model.addAttribute("boardnum", boardnum);
		return "home";
	}
}

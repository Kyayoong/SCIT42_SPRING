package net.softsociety.indiv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.indiv.vo.Person;

@Slf4j
@Controller
public class insertController {
	@GetMapping("insert")
	public String insert() {
		return "/insert";
	}
	
	@PostMapping("insert")
	public String insert(Person person) {
		log.debug("객체 : {}", person);
		return "redirect:/";
	}
}

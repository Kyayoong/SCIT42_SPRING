package net.softsociety.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("aj")
public class IdcheckController {
	@GetMapping("idcheck")
	public String idcheck() {
		return "/ajaxView/idCheck";
	}
}

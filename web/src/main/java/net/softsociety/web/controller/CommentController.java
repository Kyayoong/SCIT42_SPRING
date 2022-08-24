package net.softsociety.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("aj")
public class CommentController {
	//여기서는 댓글 관련 일반 메소드만 다룸 (ajax는 따로 관리함)
	@GetMapping("comment")
	public String comment() {
		return "/ajaxView/comment";
	}
}

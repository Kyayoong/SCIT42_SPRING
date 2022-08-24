package net.softsociety.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.web.service.CommentService;
import net.softsociety.web.service.IdcheckService;

@Slf4j
@RequestMapping("aj")
//ajax만 모아둔 컨트롤러... 보통은 rest~~~로 쌍으로 만들어준다. 이럴경우 responseBody를 맨 위에 통합할 수 있다.
@ResponseBody
@Controller
public class IdcheckRestController {
	@Autowired
	IdcheckService service;
	
	@ResponseBody
	@PostMapping("idValidation")
	public int chech(String memberid) {
		int cnt = 0;
		cnt = service.countMemberid(memberid);
		return cnt;
	}
}

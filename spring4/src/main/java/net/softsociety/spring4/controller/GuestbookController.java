package net.softsociety.spring4.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring4.service.GuestbookService;
import net.softsociety.spring4.vo.Guestbook;
import oracle.jdbc.proxy.annotation.Post;

@Slf4j
@Controller
public class GuestbookController {
	@Autowired
	GuestbookService service;
	
	//방명록1
	@GetMapping("list")
	public String visitlist(Model model) {
		ArrayList<Guestbook> list = service.selectGuestbook();
		log.debug(list.toString());
		
		model.addAttribute("list", list);
		
		return "visitlist";
	}
	
	@GetMapping("write")
	public String write() {
		return "writeform";
	}
	
	@PostMapping("write")
	public String write(Guestbook guestbook) {
		log.debug("전달된 객체 : {}", guestbook);
		int result = service.writeGuestbook(guestbook);
		if(result == 0) {
			return "redirect:/writeform";
		}
		
		return "redirect:/";
	}
	
	@PostMapping("delete")
	public String delete(Guestbook guestbook) {
		log.debug("전달된 객체 : {}", guestbook);
		int result = service.deleteGuestbook(guestbook);
		
		return "redirect:/list";
	}
	
	//방명록2
	@GetMapping("list2")
	public String visitlist2(Model model) {
		ArrayList<Guestbook> list = service.selectGuestbook();
		log.debug(list.toString());
		
		model.addAttribute("list", list);
		
		return "visitlist2";
	}
	@PostMapping("delete2")
	public String delete2(Guestbook guestbook) {
		log.debug("전달된 객체 : {}", guestbook);
		int result = service.deleteGuestbook(guestbook);
		
		return "redirect:/list2";
	}
}

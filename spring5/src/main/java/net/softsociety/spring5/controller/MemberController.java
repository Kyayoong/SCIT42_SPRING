package net.softsociety.spring5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring5.domain.Member;
import net.softsociety.spring5.service.MemberService;

@Controller
@RequestMapping("member")
@Slf4j
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@GetMapping("/join")
	public String join() {
		return "/memberView/signUpForm";
	}
	
	@PostMapping("join")
	public String signUpSubmit(Member member) {
		log.debug("전달된 객체 : {}", member);
		
		int result = service.insertMember(member);
		
		if(result != 0) {
			log.debug("저장된 객체 : {}", member);
		} else {
			log.debug("저장 실패");
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/idcheck")
	public String idcheck() {
		return "/memberView/idForm";
	}
	
	@PostMapping("/idcheck")
	public String idcheck(String searchId, Model model) {
		log.debug("검색할 아이디 값: {}", searchId);
		
		boolean result = service.idcheck(searchId);
		
		model.addAttribute("result", result);	  //검색한 아이디를 사용해도 되는지 여부
		model.addAttribute("searchId", searchId); //검색한 아이디가 무엇이었는지 다시 알려줘야함
		
		log.debug("사용가능 여부 : {}", result);
		
		return "/memberView/idForm";
	}
	
	//로그인 페이지로 이동하는 컨트롤러
	@GetMapping("/loginForm")
	public String loginForm() {
		return "/memberView/loginForm";
	}
	
	/**
	 * 개인정보수정 페이지로 이동하는 컨트롤러
	 * @param model
	 * @param user: 인증된 사용자의 정보를 받아옴.
	 * @return
	 */
	@GetMapping("/mypage")
	public String myPage(Model model, @AuthenticationPrincipal UserDetails user) {
		String id = user.getUsername();
		log.debug("전달 받은 허가된 유저의 아이디: {}", id);
		
		Member member = service.getMemberInfo(id);
		
		model.addAttribute("member", member);
		
		return "/memberView/myPage";
	}
	
	/**
	 * 개인정보 수정 정보를 받아 업데이트함.
	 * @param member: 폼에 입력된 해당회원의 수정정보
	 * @param user: 해당 회원의 아이디정보 (수정에서 input이 아니라 p로 아이디를 보여줄 경우, 아이디 정보가 없음...)
	 * @return
	 */
	@PostMapping("/mypage")
	public String myPage(Member member, @AuthenticationPrincipal UserDetails user) {
		//전달된 member객체의 값을 출력
		log.debug(member.toString());
		
		//로그인한 아이디를 읽어서 member객체의 추가 (나는 필요없지만 수업에서 하니까 해봄)
		member.setMemberid(user.getUsername());
		log.debug("최종수정하는 멤버의 정보 : {}", member.toString());
		
		//member객체를 서비르로 전달하여 DB에 update
		int result = service.updateUser(member);
		
		return "redirect:/";
	}
	
}

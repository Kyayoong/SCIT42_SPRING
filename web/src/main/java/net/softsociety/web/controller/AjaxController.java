package net.softsociety.web.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.web.domain.Person;
import net.softsociety.web.service.RecommendService;

@RequestMapping("aj")
@Controller
@Slf4j
public class AjaxController {
	//추천수 예제용
	@Autowired 
	RecommendService service;
	
	//aj1페이지
	@GetMapping("aj1")
	public String aj1() {
		return "/ajaxView/aj1";
	}
	
	/* Ajax의 경우 return의 의미가 다르다. 
	 * 리턴값이 있더라도, 리턴은 돌아가는 경로가 아니다. (진짜 가져가는 결과값을 뜻함)
	 * @ResponseBody는 호출한 곳으로 다시 데이터를 보내준다는 뜻.
	 */
	@ResponseBody
	@GetMapping("test1")
	public void test1() {
		log.debug("컨트롤러의 test1()실행");
	}
	
	/*
	 * 보낼때, 받을때의 파라미터 명이 같으면 된다.
	 */
	@ResponseBody
	@PostMapping("test2")
	public void test2(String str) {
		log.debug("전달 받은 문자열 : {}", str);
	}
	
	/*
	 * 요청 받을 때, return으로 데이터를 보내준다.
	 * 성공했을 때, 실행할 함수의 파라미터로 보내진다.
	 */
	@ResponseBody
	@GetMapping("test3")
	public String test3() {
		String s = "서버에서 보낸 문자열";
		return s;
	}
	
	/*
	 * [연습1]
	 * 가져오고 다시 보내고
	 */
	@ResponseBody
	@PostMapping("test4")
	public String test4(String str) {
		log.debug("전달 받은 문자열 : {}", str);
		String res = str.toUpperCase();
		return res;
	}
	
	//aj2페이지
	@GetMapping("aj2")
	public String aj2() {
		return "/ajaxView/aj2";
	}
	/*
	 * 각 파라미터를 따로따로 받기
	 */
	@ResponseBody
	@PostMapping("insert1")
	public void insert1(String name, int age, String phone) {
		log.debug("전달 받은 이름: {}, 나이: {}, 전화번호: {}", name, age, phone);
		return;
	}
	
	/*
	 * 객체로 하기
	 * 파라미터를 객체로 설정하면 바로 객체로 전달된다.(보낼때 ajax에서 이름을 잘 맞춰야한다. ??? : ??? 앞쪽부분을 객체와 동일시)
	 */
	@ResponseBody
	@PostMapping("insert2")
	public void insert2(Person p) {
		log.debug("전달 받은 객체: {}", p);
		return;
	}
	
	/*
	 * 폼 자체를 객체로 받기
	 */
	@ResponseBody
	@PostMapping("insert3")
	public void insert3(Person p) {
		log.debug("전달 받은 객체: {}", p);
		return;
	}
	
	/*
	 * 서버에서 객체를 string으로 보내기. 객체 리턴시, aj가 text로 받는데 자동으로 변환이 된다.
	 * ajax는 이 p 객체를 {"name":"홍길동","age":22,"phone":"123-456-7890"} 이런 형식의 text로 받는다.
	 */
	@ResponseBody
	@GetMapping("getObject1")
	public Person getObject1() {
		Person p = new Person("홍길동", 22, "123-456-7890");
		return p;
	}
	
	/*
	 * [번외 예제1]
	 * 서버에서 보내온 객체 리스트를 표에 표기하라
	 */
	@ResponseBody
	@GetMapping("getList")
	public ArrayList<Person> getList() {
		ArrayList<Person> list = new ArrayList<>();
		list.add(new Person("홍길동", 30, "010-1111-1111"));
		list.add(new Person("김길동", 35, "010-2222-2222"));
		list.add(new Person("이길동", 21, "010-3333-3333"));
		return list;
	}
	
	/*
	 *	[예제2] AJAX로 추천수 올라가기
	 */
	//추천 기능 페이지로 이동
	@GetMapping("recommend")
	public String recommend(int boardnum, Model model) {
		//처음에 해당 글의 추천수 가져오기 혹은 recomment.html의 ready스크립트에 처음 로드할 때, 불러오는 함수 추가하면 대체가능.
		int n = service.getCntBoard(boardnum);
		model.addAttribute("cnt", n);
		model.addAttribute("boardnum", boardnum);
		return "/ajaxView/recommend";
	}
	//추천 AJAX요청 처리
	@ResponseBody
	@GetMapping("vote")
	public int vote(int boardnum) {
		/*
		 * cnt는 sql에서 올려주는데, 자바단에서 안하는 이유는 여러 유저가 동시에 할 때, cnt값을 가져가는 시점이 동시에 100을 가져가면
		 * 오류가 나기때문... sql 자체에서 cnt자체를 올려주는 것으로 이러한 오류를 방지할 수 있기 때문이다.
		 * 추천횟수 제한은 db나 세션, 혹은 쿠키에 해당 정보를 저장하면 가능해진다.
		 */
		
		//해당 글번호의 추천수를 1 증가
		int result = service.updateCnt(boardnum);
		//현재 추천수를 읽어서 리턴
		int n = service.getCntBoard(boardnum);
		log.debug("추천수 : {}", n);
		return n;
	}
	//첫 페이지 방문시, ajax로 추천수 읽어오는 relaod 함수
	@ResponseBody
	@GetMapping("reload")
	public int reload(int boardnum) {
		//현재 추천수를 읽어서 리턴
		int n = service.getCntBoard(boardnum);
		log.debug("추천수 : {}", n);
		return n;
	}
}

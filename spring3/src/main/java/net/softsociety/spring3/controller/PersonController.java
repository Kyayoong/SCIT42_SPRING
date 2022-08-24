package net.softsociety.spring3.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring3.dao.PersonDAO;
import net.softsociety.spring3.service.PersonService;
import net.softsociety.spring3.vo.Person;
import oracle.jdbc.proxy.annotation.Post;

@Slf4j
@Controller
public class PersonController {
	
	@Autowired
	PersonService service;
	
	//어디로 이동하나요 insert
	@GetMapping("insert")
	public String insert() {
		return "insertform";
	}

	@PostMapping("insert")
	public String insert(Person person) {
		log.debug("전달된 객체 : {}", person);//실제로 서버에 값을 남기는 것이 아닌 궁금해서 찍는건 debug
				
		int result = service.insertPerson(person); //int형이 리턴
		
		if(result == 0) {
			return "redirect:/insert";
		}
		return "insertform";				
	}
	
	@GetMapping("delete")
	public String delete(String name) {
		log.debug("삭제할 이름 : {}", name);
		
		int result = service.deletePerson(name);
		if(result == 0) {
			log.debug("삭제 실패");
		} else {
			log.debug("삭제 성공!");
		}
		
		return "redirect:/select";
	}
	
	@GetMapping("deleteAll")
	public String deleteAll(String name) {
		log.debug("삭제할 이름 : {}", name);
		
		int result = service.deleteAllPerson(name);
		if(result == 0) {
			log.debug("삭제 실패");
		} else {
			log.debug("삭제 성공! : {}", result); //삭제 성공 갯수
		}
		
		return "redirect:/select";
	}
	
	//전체 조회
	@GetMapping("select")
	public String select(Model model) {
		ArrayList<Person> list = service.selectPerson();
		log.debug("result : {}", list);
		
		model.addAttribute("list", list);
		
		return "list";
	}
	
	//update
	@GetMapping("update")
	public String update(String name, Model model) {
		//전달받은 이름을 정보검색하여 Person객체로 리턴
		Person person = service.selectOne(name);
		//객체를 Model에 저장
		model.addAttribute("person", person);
		//HTML로 이동
		return "updateForm";
	}
	@PostMapping("update")
	public String update(Person person) {
		log.debug("전달된 객체 : {}", person);//실제로 서버에 값을 남기는 것이 아닌 궁금해서 찍는건 debug
		
		int result = service.updatePerson(person); //int형이 리턴
		
		return "redirect:/select";
	}
}

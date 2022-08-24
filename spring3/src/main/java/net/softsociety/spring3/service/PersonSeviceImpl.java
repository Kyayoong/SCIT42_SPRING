package net.softsociety.spring3.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring3.dao.PersonDAO;
import net.softsociety.spring3.vo.Person;

//PersonService 인터페이스의 구현체
@Service
@Slf4j
public class PersonSeviceImpl implements PersonService {

    @Autowired
    private PersonDAO personDAO;

	//저장
	@Override
	public int insertPerson(Person person) {

		 int result = personDAO.insertPerson(person);
		 
		return result;
	}
	
	//이름으로 삭제하기
	@Override
	public int deletePerson(String name) {
		int result = personDAO.deletePerson(name);
		
		return result;
	}
	
	//비슷한 이름 전체 삭제하기
	@Override
	public int deleteAllPerson(String name) {
		int result = personDAO.deleteAllPerson(name);
		
		return result;
	}

	//전체조회
	@Override
	public ArrayList<Person> selectPerson() {
		ArrayList<Person> list = personDAO.selectPerson();
		return list;
	}

	
	//한사람만 검색
	@Override
	public Person selectOne(String name) {
		Person person = personDAO.selectOne(name);
		log.debug("person : " + person.getName() + " " + person.getAge());
		return person;
	}
	
	//update
	@Override
	public int updatePerson(Person person) {
		int result = personDAO.updatePerson(person);
		return result;
	}
}

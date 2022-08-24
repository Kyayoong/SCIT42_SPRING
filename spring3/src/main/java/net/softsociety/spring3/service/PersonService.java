package net.softsociety.spring3.service;

import java.util.ArrayList;

import net.softsociety.spring3.vo.Person;

//서비스 인터페이스
public interface PersonService {
	//저장
	public int insertPerson(Person person);
	
	//삭제
	public int deletePerson(String name);
	
	public int deleteAllPerson(String name);
	
	//목록보기
	public ArrayList<Person> selectPerson();
	
	//한사람 검색하기
	public Person selectOne(String name);
	
	//update
	public int updatePerson(Person person);

}

package net.softsociety.spring3.dao;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import net.softsociety.spring3.vo.Person;

@Mapper
public interface PersonDAO {
	//저장
	int insertPerson(Person person);
	
	//삭제
	int deletePerson(String name);
	int deleteAllPerson(String name);
	
	//목록보기
	ArrayList<Person> selectPerson();
	
	//한사람 검색
	Person selectOne(String name);
	
	//update
	int updatePerson(Person person);
}

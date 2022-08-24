package net.softsociety.spring5.dao;

import org.apache.ibatis.annotations.Mapper;
import net.softsociety.spring5.domain.Member;

@Mapper
public interface MemberDAO {
	/**
	 * 회원가입
	 * @param member 저장할 회원 정보
	 * @return 저장된 행의 개수
	 */
	public int insertMember(Member member);
	
	
	/**
	 * 회원정보 조회
	 * @param memberid 조회할 회원의 아이디
	 * @return 해당 멤버의 정보
	 */
	public Member selectOne(String memberid);

	
	/**
	 * 회원 정보 수정
	 * @param member
	 * @return
	 */
	public int updateUser(Member member);
	
}

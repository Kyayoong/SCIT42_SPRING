package net.softsociety.spring5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring5.dao.MemberDAO;
import net.softsociety.spring5.domain.Member;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder; //security에서 암호화된 비밀번호
	
	@Override
	public int insertMember(Member member) {
		//객체에 있는 비밀번호를 암호화.
		String encodedPassword = passwordEncoder.encode(member.getMemberpw());
		//암호화된 비밀번호를 member객체에 저장
		member.setMemberpw(encodedPassword);
		
		int result = memberDAO.insertMember(member);
		
		return result;
	}

	@Override
	public boolean idcheck(String searchId) {
		boolean result = false;
		
		Member member = memberDAO.selectOne(searchId);	
		
		//만약 아이디가 존재하지 않으면 member객체 자체가 null로 저장된다.(아이디가 아니라)
		if(member == null) {
			result = true;
		}
		
		return result;
	}

	@Override
	public Member getMemberInfo(String id) {
		Member member = memberDAO.selectOne(id);
		log.debug("DB로부터 전달 받은 허가된 유저의 아이디: {}", member.getUsername());
		return member;
	}

	@Override
	public int updateUser(Member member) {
		//만약 비밀번호가 포함되면 암호화하여 재저장.
		if(!member.getPassword().isEmpty()) {
			String encodedPassword = passwordEncoder.encode(member.getMemberpw());
			//암호화된 비밀번호를 member객체에 저장
			member.setMemberpw(encodedPassword);
		}
		
		int result = memberDAO.updateUser(member);
		
		return 0;
	}
	
}

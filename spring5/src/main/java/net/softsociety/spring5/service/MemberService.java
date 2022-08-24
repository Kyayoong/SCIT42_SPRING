package net.softsociety.spring5.service;

import net.softsociety.spring5.domain.Member;

public interface MemberService {
	public int insertMember(Member member);
	
	public boolean idcheck(String searchId);

	public Member getMemberInfo(String id);

	public int updateUser(Member member);
	
	
}

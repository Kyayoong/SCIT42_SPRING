package net.softsociety.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.softsociety.web.dao.IdCheckDAO;

@Service
public class IdcheckServiceImpl implements IdcheckService {
	
	@Autowired
	IdCheckDAO dao;
	
	@Override
	public int countMemberid(String memberid) {
		int cnt = 0;
		cnt = dao.countMemberid(memberid);
		
		return cnt;
	}

}

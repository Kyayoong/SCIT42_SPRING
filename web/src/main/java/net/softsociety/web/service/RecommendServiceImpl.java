package net.softsociety.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.web.dao.BoardDAO;

@Service
@Slf4j
public class RecommendServiceImpl implements RecommendService {
	@Autowired
	BoardDAO dao;
	
	@Override
	public int getCntBoard(int boardnum) {
		// 게시글의 추천수를 받아온다
		int cnt = dao.getCntBoard(boardnum);
		return cnt;
	}

	@Override
	public int updateCnt(int boardnum) {
		// 게시글의 추천수를 업데이트한다.
		int result = dao.updateCnt(boardnum);
		return result;
	}
	
}

package net.softsociety.web.service;

public interface RecommendService {
	//게시글의 추천수를 받아옴
	public int getCntBoard(int boardnum);
	
	//게시글의 추천수를 업데이트함
	public int updateCnt(int boardnum);
}

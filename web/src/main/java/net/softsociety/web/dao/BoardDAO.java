package net.softsociety.web.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDAO {

	public int getCntBoard(int boardnum);

	public int updateCnt(int boardnum);
	
}

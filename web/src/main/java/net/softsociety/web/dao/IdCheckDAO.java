package net.softsociety.web.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IdCheckDAO {

	int countMemberid(String memberid);

}

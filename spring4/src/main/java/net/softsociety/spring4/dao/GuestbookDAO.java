package net.softsociety.spring4.dao;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import net.softsociety.spring4.vo.Guestbook;

@Mapper
public interface GuestbookDAO {
	//방명록 저장
	int writeGuestbook(Guestbook guestbook);
	ArrayList<Guestbook> selectAll();
	int deleteGuestbook(Guestbook guestbook);
}

package net.softsociety.spring4.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring4.dao.GuestbookDAO;
import net.softsociety.spring4.vo.Guestbook;

@Service
@Slf4j
@Transactional //트랜잭션 사이에서 오류가 났을 때, 자동으로 롤백해주는 애노테이션.. 스프링은 자동으로 commit해버리는데, 오류 났을 때의 커밋을 막아준다.
public class GuestbookServiceImpl implements GuestbookService {
	
	@Autowired
	private GuestbookDAO GuestbookDAO;

	@Override
	public int writeGuestbook(Guestbook guestbook) {
		log.debug("good");
		int result = GuestbookDAO.writeGuestbook(guestbook);
		return result;
	}

	@Override
	public ArrayList<Guestbook> selectGuestbook() {
		log.debug("selectAll");
		ArrayList<Guestbook> list = GuestbookDAO.selectAll();
		return list;
	}

	@Override
	public int deleteGuestbook(Guestbook guestbook) {
		log.debug(guestbook.toString());
		int result = GuestbookDAO.deleteGuestbook(guestbook);
		return result;
	}
	
	

}

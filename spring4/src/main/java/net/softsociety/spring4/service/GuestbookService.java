package net.softsociety.spring4.service;

import java.util.ArrayList;

import net.softsociety.spring4.vo.Guestbook;

//서비스 인터페이스
public interface GuestbookService {
	public int writeGuestbook(Guestbook guestbook);
	
	public ArrayList<Guestbook> selectGuestbook();
	
	public int deleteGuestbook(Guestbook guestbook);
}

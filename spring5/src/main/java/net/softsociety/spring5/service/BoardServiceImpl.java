package net.softsociety.spring5.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring5.dao.BoardDAO;
import net.softsociety.spring5.domain.Board;
import net.softsociety.spring5.domain.Reply;
import net.softsociety.spring5.util.PageNavigator;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	/**
	 * 게시글 저장 메소드
	 * 
	 * @param Board 객체(게시글)
	 * @return 성공 여부 int.
	 */
	@Override
	public int insertBoard(Board board) {
		int result = boardDAO.insertBoard(board);
		return result;
	}
	
	/**
	 * 게시글 수 카운트하는 메소드
	 * @param map 검색할 때도 가능하게
	 * @return 게시글 개수
	 */
	@Override
	public int count(String type, String searchWord) {
		
		HashMap<String, String> map = new HashMap<>();
		
		map.put("type", type);
		map.put("searchWord", searchWord);
		
		int result = boardDAO.count(map);
		
		return result;
	}
	
	/**
	 * 저장된 게시글 목록 가져오는 메소드
	 * @return ArrayList형태의 게시글 목록
	 */
	@Override
	public ArrayList<Board> selectAllBoard(PageNavigator navi, String type, String searchWord) {
		HashMap<String, String> map = new HashMap<>();
		
		map.put("type", type);
		map.put("searchWord", searchWord);
		
		//모든 글을 페이지로 나누어 해당 페이지의 정보만 가져오는 myBatis 기능
		//시작부분과 끝부분 두개의 숫자만 보내주면 된다.
		RowBounds rb = new RowBounds(navi.getStartRecord(), navi.getCountPerPage());
		
		
		ArrayList<Board> list = boardDAO.selectAllBoard(map, rb);
		
		return list;
	}
	
	
	/**
	 * 저장된 글 하나를 가져오는 메소드
	 * @param 선택된 게시글의 boardnum
	 * @return 해당 글의 내용을 포함한 모든 정보
	 */
	@Override
	public Board selectBoard(int boardnum) {
		Board board = boardDAO.selectBoard(boardnum);
		
		return board;
	}
	
	
	/**
	 * 특정 글의 조회수 상향
	 * @param boardnum: 해당 글의 번호
	 */
	@Override
	public int updateHits(int boardnum) {
		int result = boardDAO.updateHits(boardnum);
		return result;
	}

	
	/**
	 * PageNavigator 생성자
	 */
	@Override
	public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String type,
			String searchWord) {
		HashMap<String, String> map = new HashMap<>();
		map.put("type", type);
		map.put("searchWord", searchWord);
		int total = boardDAO.count(map);
		
		PageNavigator navi = new PageNavigator(pagePerGroup, countPerPage, page, total);
		
		return navi;
	}
	
	/**
	 * 게시글 삭제
	 * @param boardnum : 게시글 번호
	 * @param memberid : 작성자 이름
	 * @return
	 */
	@Override
	public int deleteBoard(int boardnum, String memberid) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("boardnum", boardnum);
		map.put("memberid", memberid);
		int result = boardDAO.deleteBoard(map);
		
		return result;
	}
	
	//게시글에 댓글 입력
	@Override
	public int replyWrite(Reply reply) {
		int result = boardDAO.replyWrite(reply);
		return result;
	}
	//특정 게시글의 댓글 가져오기
	@Override
	public ArrayList<Reply> selectAllReply(int boardnum) {
		ArrayList<Reply> rplist = boardDAO.selectAllReply(boardnum);
		
		return rplist;
	}
	
	//특정 댓글 가져오기
	@Override
	public Reply selectReply(int replynum) {
		Reply reply = boardDAO.selectReply(replynum);
		return reply;
	}
	
	//특정 댓글 삭제하기
	@Override
	public int deleteReply(Reply reply) {
		int result = boardDAO.deleteReply(reply);
		return result;
	}

}

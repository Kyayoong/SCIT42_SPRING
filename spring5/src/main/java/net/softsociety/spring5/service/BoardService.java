package net.softsociety.spring5.service;

import java.util.ArrayList;
import net.softsociety.spring5.domain.Board;
import net.softsociety.spring5.domain.Reply;
import net.softsociety.spring5.util.PageNavigator;

public interface BoardService {
	/**
	 * 게시글 저장 메소드
	 * 
	 * @param Board 객체(게시글)
	 * @return 성공 여부 int.
	 */
	public int insertBoard(Board board);
	
	/**
	 * 게시글 수 카운트하는 메소드
	 * @param map 검색할 때도 가능하게
	 * @return 게시글 개수
	 */
	public int count(String type, String searchWord);
	
	/**
	 * 저장된 게시글 목록 가져오는 메소드
	 * @return ArrayList형태의 게시글 목록
	 */
	public ArrayList<Board> selectAllBoard(PageNavigator navi, String type, String searchWord);
	
	/**
	 * 저장된 글 하나를 가져오는 메소드
	 * @param 선택된 게시글의 boardnum
	 * @return 해당 글의 내용을 포함한 모든 정보
	 */
	public Board selectBoard(int boardnum);
	
	/**
	 * 특정 글의 조회수 상향
	 * @param boardnum: 해당 글의 번호
	 */
	public int updateHits(int boardnum);

	/**
	 * PageNavigator 생성자
	 */
	public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String type, String searchWord);
	
	/**
	 * 특정 게시글 삭제
	 * @param boardnum 해당하는 게시글 번호
	 * @param memberid 삭제요청한 접속자의 정보
	 * @return
	 */
	public int deleteBoard(int boardnum, String memberid);
	
	/**
	 * 댓글 입력하기
	 * @param 댓글 정보 (댓글 달린 게시글, 댓글 내용, 댓글 작성자)
	 * @return
	 */
	public int replyWrite(Reply reply);
	
	/**
	 * 댓글 목록 가져오기
	 * @param boardnum : 가져올 댓글의 게시글 번호
	 * @return
	 */
	public ArrayList<Reply> selectAllReply(int boardnum);
	
	/**
	 * 특정 댓글 정보 조회
	 * @param replynum : 해당 댓글의 번호
	 * @return
	 */
	public Reply selectReply(int replynum);
	
	/**
	 * 특정 댓글의 삭제
	 * @param reply : 삭제할 댓글의 정보
	 * @return
	 */
	public int deleteReply(Reply reply);
}

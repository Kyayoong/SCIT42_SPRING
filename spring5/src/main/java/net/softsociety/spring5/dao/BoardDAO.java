package net.softsociety.spring5.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import net.softsociety.spring5.domain.Board;
import net.softsociety.spring5.domain.Reply;

@Mapper
public interface BoardDAO {
	/**
	 * 게시판 글 저장
	 * @param board: 저장할 게시글 객체
	 * @return
	 */
	public int insertBoard(Board board);
	
	/**
	 * 모든 게시글 다 가져오는 메소드
	 * @param rb : 현재 페이지의 정보를 보냄. mapper에는 따로 뭐 안해도 mybatis가 자동으로 처리해준다.
	 * @param map: 검색시에도 이용할 수 있게하기 위한 parameter. searchtype과 searchword가 들어있음
	 * @return
	 */
	public ArrayList<Board> selectAllBoard(HashMap<String, String> map, RowBounds rb);
	
	/**
	 * 글의 개수를 가져오는 메소드
	 * @param map 검색시에도 이용할 수 있게 해주는 parameter.
	 * @return 해당 게시글 개수
	 */
	public int count(HashMap<String, String> map);
	
	/**
	 * 하나의 게시글의 정보를 가져오는 메소드
	 * @param boardnum: 가져올 게시글의 번호
	 * @return board: 내용을 포함한 해당 번호의 게시글 정보
	 */
	public Board selectBoard(int boardnum);
	
	/**
	 * 특정 게시글의 조회수 상향
	 * @param boardnum: 게시글의 번호
	 * @return 성공여부
	 */
	public int updateHits(int boardnum);
	
	/**
	 * 특정 게시글의 삭제
	 * @param map : 삭제요청 정보. 삭제글 번호(boardnum), 삭제요청자(memberid)
	 * @return 성공여부
	 */
	public int deleteBoard(HashMap<String, Object> map);
	
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

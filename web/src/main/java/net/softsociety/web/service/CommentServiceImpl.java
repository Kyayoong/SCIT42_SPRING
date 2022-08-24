package net.softsociety.web.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.softsociety.web.dao.CommentDAO;
import net.softsociety.web.domain.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentDAO dao;
	
	/**
	 * 댓글 입력하는 메소드
	 * @param comment: 저장될 댓글의 정보
	 */
	@Override
	public int insertComment(Comment comment) {
		int result = dao.insertComment(comment);
		return result;
	}
	
	/**
	 *  댓글을 불러오는 메소드
	 * @return 댓글 목록
	 */
	@Override
	public ArrayList<Comment> readAllComment() {
		ArrayList<Comment> list = dao.readAllComment();
		return list;
	}

	/**
	 *  댓글을 삭제할 때의 메소드
	 *  @param num: 삭제할 게시글의 번호.
	 */
	@Override
	public int deleteComment(int num) {
		int result = dao.deleteComment(num);
		return result;
	}
}

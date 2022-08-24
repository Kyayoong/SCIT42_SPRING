package net.softsociety.web.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.web.domain.Comment;
import net.softsociety.web.service.CommentService;

@Slf4j
@RequestMapping("aj")
//ajax만 모아둔 컨트롤러... 보통은 rest~~~로 쌍으로 만들어준다. 이럴경우 responseBody를 맨 위에 통합할 수 있다.
@ResponseBody
@Controller
public class CommentRestController {
	
	@Autowired
	CommentService service;
	
	/**
	 * 댓글 입력하는 ajax
	 * @param comment: 저장될 댓글의 정보
	 */
	@PostMapping("insert")
	public void insertComment(Comment comment) {
		log.debug("호출됨");
		service.insertComment(comment);
	}
	
	/**
	 *  댓글을 불러오는 ajax
	 * @return 댓글 목록
	 */
	@GetMapping("read")
	public ArrayList<Comment> readAllComment(){
		ArrayList<Comment> list = service.readAllComment();
		return list;
	}
	
	/**
	 *  댓글을 삭제할 때의 ajax
	 */
	@GetMapping("deleteComment")
	public int deleteComment(int num) {
		int result = service.deleteComment(num);
		log.debug("삭제 성공 여부 : {}", result);
		return result;
	}
}

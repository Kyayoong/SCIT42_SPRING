package net.softsociety.web.service;

import java.util.ArrayList;

import net.softsociety.web.domain.Comment;

public interface CommentService {
	public int insertComment(Comment comment);

	public ArrayList<Comment> readAllComment();

	public int deleteComment(int num);
}

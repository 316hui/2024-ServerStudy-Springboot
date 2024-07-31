package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Comment;

public interface CommentService {
	public void createComment(Comment comment);
	public List<Comment> getAllComments();
}

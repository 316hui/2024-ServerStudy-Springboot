package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Comment;
import com.example.demo.mapper.CommentMapper;

@Service("CommentServiceImpl")
public class CommentServiceImpl implements CommentService{
	
	@Autowired CommentMapper commentMapper;

	@Override
	public void createComment(Comment comment) {
		commentMapper.createComment(comment);
		
	}

	@Override
	public List<Comment> getAllComments(int bId) {
		return commentMapper.getAllComments(bId);
	}

}

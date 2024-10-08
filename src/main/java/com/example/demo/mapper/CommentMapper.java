package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Comment;

@Mapper
public interface CommentMapper {
	public void createComment(Comment comment);
	public List<Comment> getAllComments(int bId);
}

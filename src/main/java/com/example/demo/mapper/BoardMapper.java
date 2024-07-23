package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Board;

@Mapper
public interface BoardMapper {
	public List<Board> selectBoardList();
	
	public void createBoard(Board board);
	public Board readBoard(int idx);
	public void deleteBoard(int idx);
	public void updateBoard(Board board);
	
	public List<Board> getAllBoards();
	public void increaseViews(Board board);

	
}

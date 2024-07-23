package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Board;

public interface BoardService {
	public List<Board> selectBoardList();
	
	public void createBoard(Board board);
	public Board readBoard(int idx);
	public void updateBoard(Board board);
	public void deleteBoard(int idx);
	public List<Board> getAllBoards();
	}

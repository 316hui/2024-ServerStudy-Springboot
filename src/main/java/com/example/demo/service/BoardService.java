package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Board;
import com.example.demo.domain.Pagination;
import com.example.demo.domain.User;

public interface BoardService {
	public List<Board> selectBoardList();
	
	public void createBoard(Board board);
	public Board readBoard(int idx);
	public void updateBoard(Board board);
	public void deleteBoard(int idx);
	public List<Board> getAllBoards();
	public void increaseViews(Board board);
	
	public int getboardsCount(Pagination pagination);
	public List<User> getUsers(Pagination pagination);
	}

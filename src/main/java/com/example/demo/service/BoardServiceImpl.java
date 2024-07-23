package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Board;
import com.example.demo.mapper.BoardMapper;

@Service("BoardServiceImpl")
public class BoardServiceImpl implements BoardService{
	
	
	@Autowired BoardMapper boardmapper;
	@Override
	public List<Board> selectBoardList() {
		return boardmapper.selectBoardList();
	}
	
	
	@Override
	public void createBoard(Board board) {
		//boardmapper.createBoard(board);
		
	}
	@Override
	public Board readBoard(int idx) {
		return boardmapper.readBoard(idx);
		
	}
	@Override
	public void updateBoard(Board board) {
		boardmapper.updateBoard(board);
		
	}
	@Override
	public void deleteBoard(int idx) {
		boardmapper.deleteBoard(idx);
		
	}

	@Override
	public List<Board> getAllBoards() {
		return boardmapper.getAllBoards();
	}


	@Override
	public void increaseViews(Board board) {
		boardmapper.increaseViews(board);
		
	}
	
	
	
	
}

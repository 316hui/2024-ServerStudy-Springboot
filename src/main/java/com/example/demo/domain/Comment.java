package com.example.demo.domain;

public class Comment {
	private User user;
	private Board board;
	private String cDatetime;
	private String cContent;
	
	private int cId;
	
	public Comment(User user, Board board, String cContent) {
		this.user = user;
		this.cContent = cContent;
		this.board = board;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public String getcDatetime() {
		return cDatetime;
	}
	public void setcDatetime(String cDatetime) {
		this.cDatetime = cDatetime;
	}
	public String getcContent() {
		return cContent;
	}
	public void setcContent(String cContent) {
		this.cContent = cContent;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	
	
}

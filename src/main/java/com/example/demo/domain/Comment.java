package com.example.demo.domain;

public class Comment {
	private User user;
	private Board board;
	private String cDatetime;
	private String cContent;
	private int bId;
	//private int cId;
	
	/*
	 * public Comment(User user, String cContent, Board board) { this.user = user;
	 * this.cContent = cContent; this.board = board; }
	 */
	//pojo 라는 오류. jsp에서 넘어오는 값을 스프링을 (Comment comment) 처럼 사용하고 싶으면 '기본생성자'

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
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
/*	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	*/
	
	
}

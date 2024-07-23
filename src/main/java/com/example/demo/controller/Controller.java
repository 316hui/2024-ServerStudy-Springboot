package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Board;
import com.example.demo.domain.User;
import com.example.demo.service.BoardService;
import com.example.demo.service.UserService;

@org.springframework.stereotype.Controller
public class Controller {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired UserService userservice;
	@Autowired BoardService boardservice;
	@Autowired PasswordEncoder passwordEncoder;
	
	
	
	@RequestMapping("/")
	public String home(Model model) {
		
		List<Board> list = boardservice.selectBoardList();
		model.addAttribute("list", list);
		logger.debug("debug");
	    logger.info("info");
	    logger.error("error");
	     
		return "/index";

	}
	
	@RequestMapping("/beforeSignup")
	public String beforeSignUp() {
		return "/signup";	// 수정
	}
	
	@RequestMapping("/signup")
	public String signup(User user) {
		//비밀번호 암호화
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		
		//유저 데이터 세팅
		user.setPassword(encodedPassword);
		user.setAccountNonExpired(true);
		user.setEnabled(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));
				
//		List<SimpleGrantedAuthority> authList = new ArrayList<>();
//		authList.add(new SimpleGrantedAuthority("ROLE_USER"));
//		authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//		user.setAuthorities(authList);
		
	
		//유저 생성
		userservice.createUser(user);
		//유저 권한 생성
		userservice.createAuthorities(user);
		
		return "/login";
	}
	@RequestMapping(value="/login")
	public String beforeLogin(Model model) {
		return "/login";
	}
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value="/admin")
	public String admin(Model model) {
		return "/admin";
	}
	@Secured({"ROLE_USER"})
	@RequestMapping(value="/user/info")
	public String userInfo(Model model) {
		return "/user_info";
	}
	@RequestMapping(value="/denied")
	public String denied(Model model) {
		return "/denied";
	}
	
	
	//Code about Board below this line
	private Board board;
	private List<Board> boards;
	
	@RequestMapping("/boardList")
	public String boardList(Model model) {
		boards = boardservice.getAllBoards();
		model.addAttribute("boards", boards);
		return "/boardList";
	}
	
	@RequestMapping("/createBoard")
	public String createBoard(Board board) {
		boardservice.createBoard(board);
		
		return "/createBoard";
	}

	@RequestMapping("/readBoard")
	public String readBoard(@ModelAttribute("bTitle") int idx, Model model) {
		board = boardservice.readBoard(idx);
		model.addAttribute("board", board);
		
		return "/detailBoard";
	}
	
	@RequestMapping("/updateBoard")
	public String editBoard(@RequestParam("bId") int idx, Model model) {
		Board board = boardservice.readBoard(idx);
		model.addAttribute("board", board);
		
		return "/editBoard";
	}
	@RequestMapping("/complete-updateBoard")
	public String completeEditBoard() {
		return "/AllAboutComplete";
	}
	
	@RequestMapping("/deleteBoard")
	public String updateBoard(@RequestParam("bId") int idx) {
		boardservice.deleteBoard(idx);
		
		return "/deleteBoard.jsp";
	}
	@RequestMapping("/complete-deleteBoard")
	public String CompleteDeleteBoard() {
		
		return "/AllAboutComplete";
	}

}
	
	
	
	

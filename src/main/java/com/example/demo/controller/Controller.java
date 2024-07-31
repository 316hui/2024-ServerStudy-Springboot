package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
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
import com.example.demo.domain.Comment;
import com.example.demo.domain.Pagination;
import com.example.demo.domain.Search;
import com.example.demo.domain.User;
import com.example.demo.service.BoardService;
import com.example.demo.service.CommentService;
import com.example.demo.service.UserService;

@org.springframework.stereotype.Controller
public class Controller {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired UserService userservice;
	@Autowired BoardService boardservice;
	@Autowired CommentService commentService;
	@Autowired PasswordEncoder passwordEncoder;
	
	
	
	@RequestMapping("/")
	public String home(Model model) {
		//List<Board> list = boardservice.getAllBoards(pagination);
		//model.addAttribute("list", list);
		logger.debug("debug");
	    logger.info("info");
	    logger.error("error");
	     
	    return "redirect:/boardList"; // "/" 가 디폴트 주소인데 "/boardList"로 리다이렉트 함.
		//return "/index";

	}
	
	@RequestMapping("/beforeSignup")
	public String beforeSignUp() {
		return "/signup";	//회원가입 페이지
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
	@RequestMapping(value="/logout")
	public String logout(Model model) {
		return "/logout";    
	}
	
	
	
	
	@RequestMapping("/boardList")
	public String boardList(
			Model model, 
			Search search, 
			@RequestParam(value="page", required=false, defaultValue="1") int page) {
		logger.debug("check page {}", page);
		int count = 0;
		
		Pagination pagination = new Pagination();
		pagination.setPage(page);
		pagination.setSearch(search);
		
		count = boardservice.getboardsCount(pagination);
		pagination.setCount(count);
		pagination.build();
		
		List<Board> boards = boardservice.getAllBoards(pagination);
		model.addAttribute("boards", boards);
		model.addAttribute("pagination", pagination);
		
		return "/boardList";
	}
	
	@RequestMapping("/board/createBoard")
	public String createBoard() {
		return "/createBoard";
	}
	
	@RequestMapping("/board/completeCreateBoard")
	public String completeCreateBoard(Board board) {
		 boardservice.createBoard(board);
		
		return "/AllAboutComplete";
	}

	@RequestMapping("/board/readBoard")
	public String readBoard(@RequestParam(value="b_id", required=true) int idx, 
							@RequestParam(value="c_content", required=true) String cContent,
							@RequestParam(value="user", required=true) User user,
							Model model) {
		Board board = boardservice.readBoard(idx);
		boardservice.increaseViews(board); //조회수 올리기
		
		//------------
		Comment comment = new Comment(user, board, cContent); //컨텐츠가 입력되었을 시 실행.
		
		commentService.createComment(comment);
		List<Comment> comments = commentService.getAllComments(idx);
		//위에 내일 구현.
		
		model.addAttribute("board", board);
		model.addAttribute("comments", comments);
		return "/detailBoard";
	}
	
	@RequestMapping("/updateBoard")
	public String editBoard(@RequestParam("b_id") int idx, Model model) {
		Board board = boardservice.readBoard(idx);
		model.addAttribute("board", board);
		
		return "/editBoard";
	}
	@RequestMapping("/complete-updateBoard")
	public String completeEditBoard(Board board) {
		boardservice.updateBoard(board);
		
		return "/AllAboutComplete";
	}
	
	@RequestMapping("/deleteBoard")
	public String updateBoard(@RequestParam("b_id") int idx) {
		boardservice.deleteBoard(idx);
		
		return "/AllAboutComplete";
	}
	

}
	
	
	
	

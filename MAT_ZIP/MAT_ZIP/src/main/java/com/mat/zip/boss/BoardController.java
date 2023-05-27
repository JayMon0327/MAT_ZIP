package com.mat.zip.boss;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 스프링에서 제어하는 역할로 등록!
@RequestMapping("/boss")
public class BoardController {

	@Autowired
	BoardDAO boardDAO;
	@Autowired
	ComDAO comDAO;

	@RequestMapping("/Board_insert")
	public void insert(BoardVO bag) {

		System.out.println("insert요청됨.");
		System.out.println(bag);

		boardDAO.insert(bag);

	}

	@PostMapping("/Board_update")
	public String update(@ModelAttribute BoardVO bag, HttpSession session, Model model) {
	    System.out.println("update요청됨.");
	    boardDAO.update(bag);
	    model.addAttribute("bag", bag);
	   System.out.println(bag);
	    // 이전 페이지로 리다이렉트
	    String prevPage = (String) session.getAttribute("prevPage");
	    if (prevPage != null) {
	        return "redirect:" + prevPage;
	    } else {
	        // 이전 페이지 정보가 없는 경우, 기본 페이지로 리다이렉트
	        return "forward:Board_list"; // 이 부분은 원하는 기본 페이지 URL로 변경하세요
	    }
	}


	@RequestMapping("/Board_delete")
	public void delete(int board_id) {
		System.out.println("delete요청됨.");
		System.out.println(board_id);
		boardDAO.delete(board_id);

	}

//	
//
//	
	@RequestMapping("/Board_one")
	public void one(int board_id, Model model) {
		System.out.println("one요청됨.");
		boardDAO.view(board_id);
		BoardVO bag = boardDAO.one(board_id);
		System.out.println(bag);
		model.addAttribute("bag", bag);
	}

	@RequestMapping("/Board_detail")
	public void detail(int board_id, Model model) {
		System.out.println("상세페이지요청됨.");
		BoardVO bag = boardDAO.one(board_id);
		boardDAO.view(board_id);
		List<ComVO> list = comDAO.list(board_id);
		List<BoardVO> list2 = boardDAO.list(5,0);
		//댓글개수가져오기 
	    int commentCount = comDAO.getCommentCount(board_id);
	    
		model.addAttribute("Com_list", list);
		model.addAttribute("Board_list", list2);
		model.addAttribute("commentCount", commentCount);
		
		System.out.println("댓글테이블요청됨");
		model.addAttribute("bag", bag);

	}

	@RequestMapping("/Board_list")
	public void list(Model model, @RequestParam(defaultValue = "1") int page) {
	    int limit = 15;
	    int offset = (page - 1) * limit;
	    List<BoardVO> list = boardDAO.list(limit, offset);
	    
	 // 댓글 개수 가져오기
	    for (BoardVO board : list) {
	        int commentCount = comDAO.getCommentCount(board.getBoard_id());
	        board.setCommentCount(commentCount);
	    }
	    
	    int totalCount = boardDAO.count();
	    int totalPages = (int) Math.ceil((double) totalCount / limit);
	    
	    model.addAttribute("Board_list", list);
	    model.addAttribute("totalCount", totalCount);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("currentPage", page);
	    
	}

	@PostMapping("/bosslike")
	public ResponseEntity<?> like(HttpSession session, @RequestParam("board_id") int board_id) {
	    String user_id = session.getAttribute("user_id").toString();
	    if (boardDAO.isLikedByUser(user_id, board_id)) {
	    	boardDAO.decreaseLikeCount(board_id);
	    	boardDAO.removeLike(user_id, board_id);
	    } else {
	    	boardDAO.increaseLikeCount(board_id);
	    	boardDAO.addLike(user_id, board_id);
	    }
	    int likeCount = boardDAO.getLikeCount(board_id);
	    return new ResponseEntity<>(likeCount, HttpStatus.OK);
	}

	    }



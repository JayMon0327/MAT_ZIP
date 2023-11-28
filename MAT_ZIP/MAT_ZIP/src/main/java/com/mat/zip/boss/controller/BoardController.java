package com.mat.zip.boss.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mat.zip.boss.dao.BoardDAO;
import com.mat.zip.boss.dao.ComDAO;
import com.mat.zip.boss.model.BoardVO;
import com.mat.zip.boss.model.ComVO;

@Controller // 스프링에서 제어하는 역할로 등록!
@RequestMapping("/boss")
public class BoardController {

	@Autowired
	BoardDAO boardDAO;
	@Autowired
	ComDAO comDAO;

	/**
	 * 게시판 글 생성
	 */
	@RequestMapping("/Board_insert")
	public void insert(BoardVO bag) {
		boardDAO.insert(bag);

	}

	/**
	 * 게시판 글 수정
	 */
	@PostMapping("/Board_update")
	public String update(@ModelAttribute BoardVO bag, HttpSession session, Model model) {
	    boardDAO.update(bag);
	    model.addAttribute("bag", bag);
	    // 이전 페이지로 리다이렉트
	    String prevPage = (String) session.getAttribute("prevPage");
	    if (prevPage != null) {
	        return "redirect:board_index.jsp";
	    } else {
	        // 이전 페이지 정보가 없는 경우, 기본 페이지로 리다이렉트
	        return "forward:board_index.jsp"; // 이 부분은 원하는 기본 페이지 URL로 변경하세요
	    }
	}


	/**
	 * 게시판 글 삭제
	 */
	@RequestMapping("/Board_delete")
	public void delete(int board_id) {
		boardDAO.delete(board_id);

	}

	/**
	 * 게시판 글 검색 기능
	 */
	@RequestMapping("/Board_search")
	public void search(String keyword, Model model) {
	    System.out.println("search 요청됨.");
	    List<BoardVO> list = boardDAO.searchByTitleOrContent(keyword);
	    System.out.println(list);
	    model.addAttribute("list", list); // attribute 이름을 'bag'에서 'list'로 변경
	}

	/**
	 * 게시판 상세 페이지
	 */
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

	/**
	 * 댓글 리스트 가져오기
	 */
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

	/**
	 * 좋아요 기능
	 */
	@PostMapping("/bosslike")
	public ResponseEntity<?> like(HttpSession session, @RequestParam("board_id") int board_id) {
	    String user_id = session.getAttribute("user_id").toString();
	    boolean isLiked;
	    if (boardDAO.isLikedByUser(user_id, board_id)) {
	        boardDAO.decreaseLikeCount(board_id);
	        boardDAO.removeLike(user_id, board_id);
	        isLiked = false;
	    } else {
	        boardDAO.increaseLikeCount(board_id);
	        boardDAO.addLike(user_id, board_id);
	        isLiked = true;
	    }
	    int likeCount = boardDAO.getLikeCount(board_id);

	    // 응답 형태를 수정합니다.
	    Map<String, Object> response = new HashMap<>();
	    response.put("count", likeCount);
	    response.put("isLiked", isLiked);
	    System.out.println(response);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	//	//페이지 로드될때 꽉찬하트 빈하트
//	@GetMapping("/bosslikeload/{board_id}")
//    public ModelAndView getBoard(HttpSession session, @PathVariable("board_id") int board_id) {
//        ModelAndView mav = new ModelAndView();
//        String user_id = session.getAttribute("user_id").toString();
//        boolean isLiked = boardDAO.isLikedByUser(user_id, board_id);
//        BoardVO board = boardDAO.detail(board_id);
//        mav.addObject("board", board);
//        mav.addObject("isLiked", isLiked);
//        mav.setViewName("board/detail");
//        return mav;
//    }
}



package com.mat.zip.boss.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mat.zip.boss.dao.BoardDAO;
import com.mat.zip.boss.dao.ReplyDAO;
import com.mat.zip.boss.model.BoardVO;
import com.mat.zip.boss.model.ReplyVO;

@Controller
public class ReplyController {

    @Autowired
    ReplyDAO replyDAO;
    @Autowired
    BoardDAO BoardDAO;

    /**
     * 댓글
     */
    @RequestMapping("/boss/Board_insertcom")
    public void insert(ReplyVO bag, int board_id, Model model) {
        List<ReplyVO> list = replyDAO.list(board_id);
        model.addAttribute("Com_list", list);
        replyDAO.insert(bag);
    }

    @PutMapping("/boss/Com_update")
    public void update(ReplyVO bag, HttpSession session) {
        String currentUserId = (String) session.getAttribute("user_id");
        ReplyVO existingComment = replyDAO.one(bag.getReply_id());
        if (currentUserId.equals(existingComment.getWriter())) {
            replyDAO.update(bag);
        } else {
            throw new RuntimeException("현재사용자와 댓글사용자가 일치하지 않습니다.");
        }
    }

    @DeleteMapping("/boss/Com_delete")
    public void delete(int reply_id, HttpSession session) {
        String currentUserId = (String) session.getAttribute("user_id");
        ReplyVO existingComment = replyDAO.one(reply_id);
        if (currentUserId.equals(existingComment.getWriter())) {
            replyDAO.delete(reply_id);
        } else {
            throw new RuntimeException("현재 사용자가 댓글 작성자와 일치하지 않습니다");
        }
    }

    @GetMapping("Com_one")
    public void one(int Com_id, Model model) {
        ReplyVO bag = replyDAO.one(Com_id);
        model.addAttribute("bag", bag);
    }

    @GetMapping("Board_view")
    public String view(BoardVO bag, Model model, int board_id) {
        BoardVO board = BoardDAO.one(board_id);
        model.addAttribute("bag", board);

        List<ReplyVO> list = replyDAO.list(board_id);
        model.addAttribute("Com_list", list);

        return "Board_detail";
    }

}
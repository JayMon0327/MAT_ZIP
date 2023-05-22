package com.mat.zip.point;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class gifticonController {

	@Autowired
	gifticonDAO dao;

	@RequestMapping("gifticon")
	public void gifticon(Model model) {
		List<gifticonVO> list = dao.findall();
		model.addAttribute("list", list);
	}

}
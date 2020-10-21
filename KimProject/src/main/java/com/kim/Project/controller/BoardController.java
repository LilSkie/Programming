package com.kim.Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kim.Project.dto.BoardDTO;
import com.kim.Project.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	
	
	private ModelAndView mav;
	
	//글작성
	@RequestMapping(value="BoardWriteForm")
	public String BoardWriteForm() {
		
		
		return "BoardV/BoardWrite";
	}
	
	@RequestMapping(value="/boardwrite")
	public ModelAndView MemberWrite(@ModelAttribute BoardDTO board) {
		
		mav = new ModelAndView();
		mav = boardService.boardWrite(board);
		
		
		return mav;
		
	}
	//글목록
	@RequestMapping(value="/boardlist")
	public ModelAndView BoardList() {
		
		mav = new ModelAndView();
		mav = boardService.BoardList();
		
		return mav;
	}
	//글상세조회
	@RequestMapping(value="boardview")
	public ModelAndView BoardView(@RequestParam ("bnumber") int bnumber) {
		
		mav = new ModelAndView();
		
		mav = boardService.BoardView(bnumber);
		
		return mav;
	}
	//업데이트
	@RequestMapping(value="/boardupdate")
	public ModelAndView boardUpdate(@RequestParam("bnumber") int bnumber) {
		mav = boardService.boardUpdate(bnumber);
		return mav;
	}
	
	@RequestMapping(value="/boardupdateprocess")
	public ModelAndView boardUpdateProcess(@ModelAttribute BoardDTO board) {
		mav = boardService.boardUpdateProcess(board);
		return mav;
	}
	//삭제
	@RequestMapping(value="/boarddelete")
	public ModelAndView boardDelete(@RequestParam("bnumber") int bnumber) {
		mav = boardService.boardDelete(bnumber);
		return mav;
	}
	
	

}

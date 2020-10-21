package com.kim.Project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.kim.Project.dao.BoardDAO;
import com.kim.Project.dto.BoardDTO;

@Service
public class BoardService {

	@Autowired
	private BoardDAO boardDAO;

	private ModelAndView mav;
	//글작성
	public ModelAndView boardWrite(BoardDTO board) {

		mav = new ModelAndView();

		int WriteResult = boardDAO.BoardWrite(board);

		if (WriteResult > 0) {

			mav.setViewName("redirect:/BoardV/boardlist");

		} else {

			mav.setViewName("Main");

		}

		return mav;
	}
	public ModelAndView BoardList() {
		
		mav = new ModelAndView();
		List<BoardDTO>boardList = boardDAO.BoardList();
		
		if(boardList != null) {
			
			mav.addObject("boardList",boardList);
			mav.setViewName("BoardV/BoardList");
			
		}
		
		
		
		return mav;
	}
	public ModelAndView BoardView(int bnumber) {

		mav = new ModelAndView();
		BoardDTO boardView = boardDAO.BoardView(bnumber);
		
		if(boardView != null) {
			
			mav.addObject("boardView",boardView);
			mav.setViewName("BoardView");
		
		}
		
		
		
		
		return mav;
	}
	public ModelAndView boardUpdate(int bnumber) {
		mav = new ModelAndView();
		BoardDTO boardUpdate = boardDAO.BoardView(bnumber);
		mav.addObject("boardUpdate", boardUpdate);
		mav.setViewName("boardv/BoardUpdate");
		return mav;
	}

	public ModelAndView boardUpdateProcess(BoardDTO board) {
		mav = new ModelAndView();
		int updateResult = boardDAO.BoardUpdate(board);
		if(updateResult > 0)
			mav.setViewName("redirect:/boardview?bnumber="+board.getBnumber());
		else 
			mav.setViewName("boardv/BoardUpdateFail");
		return mav;
	}

	public ModelAndView boardDelete(int bnumber) {
		mav = new ModelAndView();
		int deleteResult = boardDAO.BoardDelete(bnumber);
		if(deleteResult > 0)
			mav.setViewName("redirect:/boardlist");
		else 
			mav.setViewName("boardv/BoardDeleteFail");
		return mav;
	}

}

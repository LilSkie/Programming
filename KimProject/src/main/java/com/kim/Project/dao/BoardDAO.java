package com.kim.Project.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kim.Project.dto.BoardDTO;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate sql;

	public int BoardWrite(BoardDTO board) {
		
		return sql.insert("Board.BoardWrite",board);
	}

	public List<BoardDTO> BoardList() {
		
		return sql.selectList("Board.BoardList");
	}

	public BoardDTO BoardView(int bnumber) {
		
		return sql.selectOne("Board.BoardView",bnumber);
	}
	public int BoardUpdate(BoardDTO board) {
		return sql.update("Board.boardUpdate", board);
	}
	public int BoardDelete(int bnumber) {
		return sql.delete("Board.boardDelete", bnumber);
	}

}

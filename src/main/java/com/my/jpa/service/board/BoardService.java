package com.my.jpa.service.board;

import java.util.List;

import com.my.jpa.model.BoardModel;
import com.my.jpa.param.BoardParam;

public interface BoardService {

	List<BoardModel> getBoards();

	BoardModel addBoard(BoardParam boardParam);

	BoardModel getBoard(Long id);
	
	BoardModel updateBoard(Long id, BoardParam boardParam);
	
	BoardModel deleteBoard(Long id);

}

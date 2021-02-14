package com.my.jpa.service.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.my.jpa.entity.BoardEntity;
import com.my.jpa.mapper.BoardMapper;
import com.my.jpa.model.BoardModel;
import com.my.jpa.param.BoardParam;
import com.my.jpa.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardRepository boardRepository;
	private final BoardMapper boardMapper;
	
	@Override
	public List<BoardModel> getBoards() {
		return boardMapper.toListModel(boardRepository.findAll());
	}
	
	@Override
	public BoardModel getBoard(Long id) {
		return boardMapper.toModel(findById(id));
	}
	
	@Override
	public BoardModel addBoard(BoardParam boardParam) {
		return boardMapper.toModel(
				boardRepository.save(boardMapper.toEntity(boardParam))
			   );
	}
	
	@Override
	@Transactional
	public BoardModel updateBoard(Long id, BoardParam boardParam) {
		BoardEntity boardEntity = findById(id);
		boardEntity.update(boardParam);
		return boardMapper.toModel(boardEntity);
	}
	
	@Override
	public BoardModel deleteBoard(Long id) {
		BoardEntity boardEntity = findById(id);
		boardRepository.deleteById(id);
		return boardMapper.toModel(boardEntity);
	}
	
	private BoardEntity findById(Long id) {
		return boardRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, id + " is not found"));
	}
	
}

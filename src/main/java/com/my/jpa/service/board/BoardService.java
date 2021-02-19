package com.my.jpa.service.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.my.jpa.entity.BoardEntity;
import com.my.jpa.entity.CommentEntity;
import com.my.jpa.mapper.BoardMapper;
import com.my.jpa.mapper.CommentMapper;
import com.my.jpa.model.BoardModel;
import com.my.jpa.model.CommentModel;
import com.my.jpa.param.BoardParam;
import com.my.jpa.param.CommentParam;
import com.my.jpa.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final BoardMapper boardMapper;
	private final CommentMapper commentMapper;
	
	public List<BoardModel> getBoards() {
		return boardMapper.toListModel(boardRepository.findAll());
	}
	
	public BoardModel getBoard(Long id) {
		return boardMapper.toModel(findBoardById(id));
	}
	
	public BoardModel addBoard(BoardParam boardParam) {
		return boardMapper.toModel(
				boardRepository.save(boardMapper.toEntity(boardParam))
			   );
	}
	
	public BoardModel updateBoard(Long id, BoardParam boardParam) {
		BoardEntity boardEntity = findBoardById(id);
		boardEntity.update(boardParam);
		return boardMapper.toModel(boardEntity);
	}
	
	public BoardModel deleteBoard(Long id) {
		BoardEntity boardEntity = findBoardById(id);
		boardRepository.deleteById(id);
		return boardMapper.toModel(boardEntity);
	}

	public List<CommentModel> getCommentsInBoard(Long boardId) {
		return commentMapper.toListModel(findBoardById(boardId).getComments());
	}

	public CommentModel addCommentInBoard(Long boardId, CommentParam commentParam) {
		BoardEntity boardEntity = findBoardById(boardId);
		boardEntity.addComment(commentMapper.toEntity(commentParam));
		List<CommentEntity> comments = boardRepository.save(boardEntity).getComments();
		return commentMapper.toModel(comments.get(comments.size() - 1));
	}

	public CommentModel updateCommentInBoard(Long boardId, Long commentId, CommentParam commentParam) {
		BoardEntity boardEntity = findBoardById(boardId);
		CommentEntity commentEntity = findCommentById(commentId, boardEntity.getComments());
		commentEntity.update(commentParam);
		boardRepository.save(boardEntity);
		return commentMapper.toModel(commentEntity);
	}
	
	public CommentModel deleteCommentInBoard(Long boardId, Long commentId) {
		BoardEntity boardEntity = findBoardById(boardId);
		CommentEntity commentEntity = findCommentById(commentId, boardEntity.getComments());
		boardEntity.getComments().remove(commentEntity);
		boardRepository.save(boardEntity);
		return commentMapper.toModel(commentEntity);
	}
	
	private BoardEntity findBoardById(Long boardId) {
		return boardRepository.findById(boardId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, boardId + " is not found"));
	}
	
	private CommentEntity findCommentById(Long commentId, List<CommentEntity> comments) {
		return comments.stream()
				.filter(e -> e.getId() == commentId)
				.findFirst()
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, commentId + " is not found"));
	}
}

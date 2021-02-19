package com.my.jpa.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.jpa.model.BoardModel;
import com.my.jpa.model.CommentModel;
import com.my.jpa.param.BoardParam;
import com.my.jpa.param.CommentParam;
import com.my.jpa.service.board.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;

	@GetMapping
	public List<BoardModel> getBoards() {
		return boardService.getBoards();
	}
	
	@GetMapping("{id}")
	public BoardModel getBoard(@PathVariable("id") Long id) {
		return boardService.getBoard(id);
	}
	
	@GetMapping("{boardId}/comments")
	public List<CommentModel> getCommentsInBoard(@PathVariable("boardId") Long boardId) {
		return boardService.getCommentsInBoard(boardId);
	}
	
	@PostMapping
	public ResponseEntity<BoardModel> addBoard(@Valid @RequestBody BoardParam boardParam) throws URISyntaxException {
		BoardModel boardModel = boardService.addBoard(boardParam);
		return ResponseEntity.created(new URI("/board/" + boardModel.getId()))
				.body(boardModel);
	}
	
	@PostMapping("{boardId}/comment")
	public CommentModel addCommentInBoard(@PathVariable("boardId") Long boardId, @Valid @RequestBody CommentParam commentParam) {
		return boardService.addCommentInBoard(boardId, commentParam);
	}
	
	@PutMapping("{id}")
	public BoardModel updateBoard(@PathVariable("id") Long id, @Valid @RequestBody BoardParam boardParam) {
		return boardService.updateBoard(id, boardParam);
	}
	
	@PutMapping("{boardId}/comment/{commentId}")
	public CommentModel updateCommentInBoard(@PathVariable("boardId") Long boardId, 
											 @PathVariable("commentId") Long commentId,
 											 @Valid @RequestBody CommentParam commentParam) {
		return boardService.updateCommentInBoard(boardId, commentId, commentParam);
	}
	
	@DeleteMapping("{id}")
	public BoardModel deleteBoard(@PathVariable("id") Long id) {
		return boardService.deleteBoard(id);
	}
	
	@DeleteMapping("{boardId}/comment/{commentId}")
	public CommentModel deleteCommentInBoard(@PathVariable("boardId") Long boardId, @PathVariable("commentId") Long commentId) {
		return boardService.deleteCommentInBoard(boardId, commentId);
	}
	
}

package com.my.jpa.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.my.jpa.entity.BoardEntity;
import com.my.jpa.model.BoardModel;
import com.my.jpa.param.BoardParam;

@Component
public class BoardMapper {
		
	public BoardModel toModel(BoardEntity boardEntity) {
		return BoardModel.builder()
				.id(boardEntity.getId())
				.title(boardEntity.getTitle())
				.writer(boardEntity.getWriter())
				.content(boardEntity.getContent())
				.build();
	}
	
	public BoardEntity toEntity(BoardParam boardParam) {
		return BoardEntity.builder()
				.title(boardParam.getTitle())
				.writer(boardParam.getWriter())
				.content(boardParam.getContent())
				.build();
	}
	
	public List<BoardModel> toListModel(List<BoardEntity> boardEntities) {
		return boardEntities.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}

}

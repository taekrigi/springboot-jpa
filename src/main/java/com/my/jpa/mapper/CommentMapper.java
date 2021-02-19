package com.my.jpa.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.my.jpa.entity.CommentEntity;
import com.my.jpa.model.CommentModel;
import com.my.jpa.param.CommentParam;

@Component
public class CommentMapper {
		
	public CommentModel toModel(CommentEntity commentEntity) {
		return CommentModel.builder()
				.id(commentEntity.getId())
				.writer(commentEntity.getWriter())
				.content(commentEntity.getContent())
				.build();
	}
	
	public CommentEntity toEntity(CommentParam commentParam) {
		return CommentEntity.builder()
				.writer(commentParam.getWriter())
				.content(commentParam.getContent())
				.build();
	}
	
	public List<CommentModel> toListModel(List<CommentEntity> commentEntities) {
		return commentEntities.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}

}

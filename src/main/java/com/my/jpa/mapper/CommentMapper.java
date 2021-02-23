package com.my.jpa.mapper;

import org.mapstruct.Mapper;

import com.my.jpa.entity.CommentEntity;
import com.my.jpa.model.CommentModel;
import com.my.jpa.param.CommentParam;

@Mapper(componentModel = "spring")
public interface CommentMapper extends BaseMapper<CommentEntity, CommentModel, CommentParam> {

}

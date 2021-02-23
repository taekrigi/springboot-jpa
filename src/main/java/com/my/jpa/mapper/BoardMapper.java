package com.my.jpa.mapper;

import org.mapstruct.Mapper;

import com.my.jpa.entity.BoardEntity;
import com.my.jpa.model.BoardModel;
import com.my.jpa.param.BoardParam;

@Mapper(componentModel = "spring")
public interface BoardMapper extends BaseMapper<BoardEntity, BoardModel, BoardParam> {

}

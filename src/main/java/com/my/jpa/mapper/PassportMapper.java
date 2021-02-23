package com.my.jpa.mapper;

import org.mapstruct.Mapper;

import com.my.jpa.entity.PassportEntity;
import com.my.jpa.model.PassportModel;
import com.my.jpa.param.PassportParam;

@Mapper(componentModel = "spring")
public interface PassportMapper extends BaseMapper<PassportEntity, PassportModel, PassportParam> {

}

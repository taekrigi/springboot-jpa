package com.my.jpa.mapper;

import org.mapstruct.Mapper;

import com.my.jpa.entity.UserEntity;
import com.my.jpa.model.UserModel;
import com.my.jpa.param.UserParam;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserEntity, UserModel, UserParam> {

}

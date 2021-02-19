package com.my.jpa.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.my.jpa.entity.UserEntity;
import com.my.jpa.model.UserModel;
import com.my.jpa.param.UserParam;

@Component
public class UserMapper {
		
	public UserModel toModel(UserEntity userEntity) {
		return UserModel.builder()
				.id(userEntity.getId())
				.userId(userEntity.getUserId())
				.userPassword(userEntity.getUserPassword())
				.build();
	}
	
	public UserEntity toEntity(UserParam userParam) {
		return UserEntity.builder()
				.userId(userParam.getUserId())
				.userPassword(userParam.getUserPassword())
				.build();
	}
	
	public List<UserModel> toListModel(List<UserEntity> userEntities) {
		return userEntities.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}

}
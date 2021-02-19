package com.my.jpa.service.board;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.my.jpa.entity.UserEntity;
import com.my.jpa.mapper.UserMapper;
import com.my.jpa.model.UserModel;
import com.my.jpa.param.UserParam;
import com.my.jpa.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private UserRepository userRepository;
	private UserMapper userMapper;
	
	public List<UserModel> getUsers() {
		return userMapper.toListModel(userRepository.findAll());
	}

	public UserModel getUser(UUID id) {
		return userMapper.toModel(findUserById(id));
	}
	
	public UserModel addUser(UserParam userParam) {
		return userMapper.toModel(
				userRepository.save(userMapper.toEntity(userParam))
			   );
	}
	
	public UserModel updateUser(UUID id, UserParam userParam) {
		UserEntity user = findUserById(id);
		user.setPassword(userParam.getUserPassword());
		return userMapper.toModel(userRepository.save(user));
	}
	
	public UserModel deleteUser(UUID id) {
		UserEntity user = findUserById(id);
		userRepository.deleteById(id);
		return userMapper.toModel(user);
	}
	
	
	private UserEntity findUserById(UUID id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, id + " is not found"));
	}
}

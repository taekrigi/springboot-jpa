package com.my.jpa.service.board;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.my.jpa.entity.UserEntity;
import com.my.jpa.mapper.PassportMapper;
import com.my.jpa.mapper.UserMapper;
import com.my.jpa.model.PassportModel;
import com.my.jpa.model.UserModel;
import com.my.jpa.param.PassportParam;
import com.my.jpa.param.UserParam;
import com.my.jpa.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private UserRepository userRepository;
	private UserMapper userMapper;
	private PassportMapper passportMapper;
	
	public List<UserModel> getUsers() {
		return userMapper.toListModel(userRepository.findAll());
	}

	public UserModel getUser(UUID id) {
		System.out.println(findUserById(id).getPassport().getCountry());
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
	
	public UserModel getUserByUserId(String userId) {
		return userMapper.toModel(findUserByUserId(userId));
	}

	public UserModel updateUserByUserId(String userId, @Valid UserParam userParam) {
		UserEntity user = findUserByUserId(userId);
		user.setPassword(userParam.getUserPassword());
		return userMapper.toModel(userRepository.save(user));
	}

	@Transactional
	public UserModel deleteUserByUserId(String userId) {
		UserEntity user = findUserByUserId(userId);
		userRepository.deleteByUserId(userId);
		return userMapper.toModel(user);
	}

	private UserEntity findUserById(UUID id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, id + " is not found"));
	}
	
	private UserEntity findUserByUserId(String userId) {
		return userRepository.findByUserId(userId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, userId + " is not found"));
	}

	public PassportModel addPassportInUser(UUID id, PassportParam passportParam) {
		UserEntity user = findUserById(id);
		user.setPassport(passportMapper.toEntity(passportParam));
		return passportMapper.toModel(userRepository.save(user).getPassport());
	}
}

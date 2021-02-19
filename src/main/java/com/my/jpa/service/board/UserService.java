package com.my.jpa.service.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.my.jpa.entity.PassportEntity;
import com.my.jpa.entity.UserEntity;
import com.my.jpa.mapper.PassportMapper;
import com.my.jpa.mapper.UserMapper;
import com.my.jpa.model.PassportModel;
import com.my.jpa.model.UserModel;
import com.my.jpa.param.PassportParam;
import com.my.jpa.param.UserParam;
import com.my.jpa.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PassportMapper passportMapper;
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
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

	@Async
	public CompletableFuture<List<UserModel>> addUsersByCsv(MultipartFile file) throws IOException {
		System.out.println("what the...?");
		long start = System.currentTimeMillis();
		
		List<UserEntity> users = parseCsvToUserEntity(file);
		System.out.println(users);
		logger.info("Saving list of users of size {}, {}", users.size(), Thread.currentThread().getName());
		
		userRepository.saveAll(users);
		
		long end = System.currentTimeMillis();
		
		logger.info("Time elapsed : {}", end - start);
		
		return CompletableFuture.completedFuture(userMapper.toListModel(users));
	}
	
	@Async
	public CompletableFuture<List<UserModel>> findAllUsersByAsync() {
		logger.info("Get list of user by {}", Thread.currentThread().getName());
		
		List<UserEntity> users = userRepository.findAll();
		
		return CompletableFuture.completedFuture(userMapper.toListModel(users));
	}
	
	private List<UserEntity> parseCsvToUserEntity(MultipartFile file) throws IOException {
		List<UserEntity> users = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] datas = line.split(",");
				UserEntity user = UserEntity.builder()
						.userId(datas[0])
						.userPassword(datas[1])
						.passport(PassportEntity.builder()
								.country(datas[2])
								.gender(datas[3])
								.countVisitedCountry(Integer.parseInt(datas[4]))
								.build())
						.build();
				users.add(user);
			}
			return users;
		}
	}
}

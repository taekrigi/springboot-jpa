package com.my.jpa.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.my.jpa.model.PassportModel;
import com.my.jpa.model.UserModel;
import com.my.jpa.param.PassportParam;
import com.my.jpa.param.UserParam;
import com.my.jpa.service.board.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {
	
	private UserService userService;
	
	@GetMapping
	public List<UserModel> getUsers() {
		return userService.getUsers();
	}
	
	@GetMapping("{id}")
	public UserModel getUser(@PathVariable("id") UUID id) {
		return userService.getUser(id);
	}
	
	@GetMapping("/userId/{userId}")
	public UserModel getUserByUserId(@PathVariable("userId") String userId) {
		return userService.getUserByUserId(userId);
	}
	
	@GetMapping("async")
	public CompletableFuture<ResponseEntity<List<UserModel>>> findAllUsersByAsync() {
		return userService.findAllUsersByAsync().thenApply(ResponseEntity::ok);
	}
	
	@GetMapping("async/multiple")
	public ResponseEntity<List<List<UserModel>>> findAllUserByMultipleAsync() {
		CompletableFuture<List<UserModel>> user1 = userService.findAllUsersByAsync();
		CompletableFuture<List<UserModel>> user2 = userService.findAllUsersByAsync();
		CompletableFuture<List<UserModel>> user3 = userService.findAllUsersByAsync();
		
		List<List<UserModel>> users = Stream.of(user1, user2, user3)
			.map(CompletableFuture::join)
			.collect(Collectors.toList());
		
		return ResponseEntity.ok(users);
	}
	
	@PostMapping
	public ResponseEntity<UserModel> addUser(@Valid @RequestBody UserParam userParam) throws URISyntaxException {
		UserModel userModel = userService.addUser(userParam);
		return ResponseEntity.created(new URI("/user/" + userModel.getUserId()))
				.body(userModel);
	}

	@PostMapping("{id}/passport")
	public ResponseEntity<PassportModel> addPassportInUser(@PathVariable("id") UUID id, @RequestBody PassportParam passportParam) throws URISyntaxException {
		PassportModel passportModel = userService.addPassportInUser(id, passportParam);
		return ResponseEntity.created(new URI("/user/" + id + "/passport/" + passportModel.getId()))
				.body(passportModel);
	}
	
	@PostMapping("csv")
	public ResponseEntity<Void> addUsersByCsv(@RequestParam("files") List<MultipartFile> files) throws IOException, URISyntaxException {		
		for (MultipartFile file : files) {
			userService.addUsersByCsv(file);
		}
		System.out.println("??");
		return ResponseEntity.created(new URI("/user/async"))
				.build();
	}
	
	@PutMapping("{id}")
	public UserModel updateUser(@PathVariable("id") UUID id, @Valid @RequestBody UserParam userParam) {
		return userService.updateUser(id, userParam);
	}
	
	@PutMapping("/userId/{userId}")
	public UserModel updateUserByUserId(@PathVariable("userId") String userId, @Valid @RequestBody UserParam userParam) {
		return userService.updateUserByUserId(userId, userParam);
	}
	
	@DeleteMapping("{id}")
	public UserModel deleteUser(@PathVariable("id") UUID id) {
		return userService.deleteUser(id);
	}
	
	@DeleteMapping("/userId/{userId}")
	public UserModel deleteUserByUserId(@PathVariable("userId") String userId) {
		return userService.deleteUserByUserId(userId);
	}
	
}

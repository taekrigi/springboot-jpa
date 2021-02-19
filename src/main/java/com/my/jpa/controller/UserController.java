package com.my.jpa.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.jpa.model.UserModel;
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
	
	@PostMapping
	public ResponseEntity<UserModel> addUser(@Valid @RequestBody UserParam userParam) throws URISyntaxException {
		UserModel userModel = userService.addUser(userParam);
		return ResponseEntity.created(new URI("/user/" + userModel.getUserId()))
				.body(userModel);
	}
	
	@PutMapping("{id}")
	public UserModel updateUser(@PathVariable("id") UUID id, @Valid @RequestBody UserParam userParam) {
		return userService.updateUser(id, userParam);
	}
	
	@DeleteMapping("{id}")
	public UserModel deleteUser(@PathVariable("id") UUID id) {
		return userService.deleteUser(id);
	}
	
	
}

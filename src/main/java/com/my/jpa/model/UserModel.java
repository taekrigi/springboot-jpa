package com.my.jpa.model;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserModel {

	private UUID id;
	
	private String userId;
	
	private String userPassword;
	
}

package com.my.jpa.param;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class UserParam {
	
	@NotBlank
	private String userId;
	
	@NotBlank
	private String userPassword;
}

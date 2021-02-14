package com.my.jpa.param;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class BoardParam {

	@NotBlank
	private String title;
	
	@NotBlank
	private String writer;
	
	@NotBlank
	private String content;
}

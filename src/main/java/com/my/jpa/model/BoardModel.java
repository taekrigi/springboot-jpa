package com.my.jpa.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardModel {

	private Long id;
	
	private String title;
	
	private String writer;
	
	private String content;
}

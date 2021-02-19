package com.my.jpa.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentModel {

	private Long id;
	
	private String writer;
	
	private String content;
}

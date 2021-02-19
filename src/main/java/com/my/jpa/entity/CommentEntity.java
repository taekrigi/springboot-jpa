package com.my.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.my.jpa.param.CommentParam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "COMMENT")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=500, nullable=false)
	private String writer;
	
	@Column(length=500, nullable=false)
	private String content;
	
	@Column(name = "board_id")
	private Long boardId;

	public void update(CommentParam commentParam) {
		this.writer = commentParam.getWriter();
		this.content = commentParam.getContent();
	}
	
	
}

package com.my.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.my.jpa.param.BoardParam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length=500, nullable=false)
	private String title;
	
	@Column(length=500, nullable=false)
	private String writer;
	
	@Column(length=1000, nullable=false)
	private String content;
	
	public void update(BoardParam boardParam) {
		this.title = boardParam.getTitle();
		this.writer = boardParam.getWriter();
		this.content = boardParam.getContent();
	}
	
}

package com.my.jpa.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.my.jpa.param.BoardParam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="BOARD")
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
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "board_id")
	private List<CommentEntity> comments;
	
	public void update(BoardParam boardParam) {
		this.title = boardParam.getTitle();
		this.writer = boardParam.getWriter();
		this.content = boardParam.getContent();
	}
	
	public void addComment(CommentEntity commentEntity) {
		comments.add(commentEntity);
	}
}

package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;

import br.com.alura.forum.model.Answer;
import lombok.Getter;

@Getter
public class AnswerDto {

	private Long id;
	private String message;
	private LocalDateTime createdAt;
	private String authorName;
	
	public AnswerDto(Answer answer) {
		this.id = answer.getId();
		this.message = answer.getMessage();
		this.createdAt = answer.getCreatedAt();
		this.authorName = answer.getAuthor().getName();
	}
	
}

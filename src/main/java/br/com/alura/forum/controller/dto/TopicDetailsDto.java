package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.modelo.TopicStatus;
import br.com.alura.forum.modelo.Topic;
import lombok.Getter;

@Getter
public class TopicDetailsDto {

	private Long id;
	private String title;
	private String message;
	private LocalDateTime createdAt;
	private String authorName;
	private TopicStatus status;
	private List<AnswerDto> answers;
	
	public TopicDetailsDto(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.message = topic.getMessage();
		this.createdAt = topic.getCreatedAt();
		this.authorName = topic.getAuthor().getName();
		this.status = topic.getStatus();
		this.answers = new ArrayList<>();
		this.answers.addAll(topic.getAnswers().stream().map(AnswerDto::new).collect(Collectors.toList()));
	}
	
}

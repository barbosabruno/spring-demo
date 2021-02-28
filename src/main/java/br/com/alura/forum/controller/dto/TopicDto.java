package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;

import br.com.alura.forum.modelo.Topic;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class TopicDto {

	private Long id;
	private String title;
	private String message;
	private LocalDateTime createdAt;
	
	public TopicDto(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.message = topic.getMessage();
		this.createdAt = topic.getCreatedAt();
	}

	public static Page<TopicDto> converter(Page<Topic> topics) {
		return topics.map(TopicDto::new);
	}

}

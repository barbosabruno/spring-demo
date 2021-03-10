package br.com.alura.forum.controller.form;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.model.Topic;
import br.com.alura.forum.repository.TopicRepository;

@Getter
@Setter
public class UpdateTopicForm {
	
	@NotBlank
	@Length(min = 5)
	private String title;

	@NotBlank
	@Length(min = 10)
	private String message;

	public Topic update(Long id, TopicRepository topicRepository) {
		Topic topic = topicRepository.getOne(id);
		
		topic.setTitle(this.title);
		topic.setMessage(this.message);
		
		return topic;
	}
	
}

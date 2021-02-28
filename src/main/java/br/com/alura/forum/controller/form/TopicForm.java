package br.com.alura.forum.controller.form;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.modelo.Course;
import br.com.alura.forum.modelo.Topic;
import br.com.alura.forum.repository.CourseRepository;

@Getter
@Setter
public class TopicForm {

	@NotBlank
	@Length(min = 5)
	private String title;

	@NotBlank
	@Length(min = 10)
	private String message;

	@NotBlank
	private String courseName;

	public Topic converter(CourseRepository courseRepository) {
		final Course course = courseRepository.findByName(courseName);
		return new Topic(title, message, course);
	}

}

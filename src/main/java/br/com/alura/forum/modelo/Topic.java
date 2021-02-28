package br.com.alura.forum.modelo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Data
@NoArgsConstructor
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String message;

	private LocalDateTime createdAt = LocalDateTime.now();

	@Enumerated(EnumType.STRING)
	private TopicStatus status = TopicStatus.NOT_ANSWERED;

	@ManyToOne
	private User author;

	@ManyToOne
	private Course course;

	@OneToMany(mappedBy = "topic")
	private List<Answer> answers = new ArrayList<>();
	
	public Topic(String title, String message, Course course) {
		this.title = title;
		this.message = message;
		this.course = course;
	}

}

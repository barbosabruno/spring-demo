package br.com.alura.forum.modelo;

import lombok.Data;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String message;
	
	@ManyToOne
	private Topic topic;

	private LocalDateTime createdAt = LocalDateTime.now();

	@ManyToOne
	private User author;

	private Boolean solution = false;

}

package br.com.alura.forum.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.TopicDetailsDto;
import br.com.alura.forum.controller.dto.TopicDto;
import br.com.alura.forum.controller.form.UpdateTopicForm;
import br.com.alura.forum.controller.form.TopicForm;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.TopicRepository;

@RestController
@RequestMapping("/topics")
public class TopicController {

	private static final String KEY_TOPICS_LIST = "topicsList";

	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping
	@Cacheable(value = KEY_TOPICS_LIST)
	public Page<TopicDto> findAll(@RequestParam(required = false) String courseName,
								@PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

		if (courseName == null) {
			Page<Topic> topics = this.topicRepository.findAll(pageable);
			return TopicDto.converter(topics);
		}

		Page<Topic> topics = this.topicRepository.findByCourseName(courseName, pageable);
		return TopicDto.converter(topics);
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = KEY_TOPICS_LIST, allEntries = true)
	public ResponseEntity<TopicDto> save(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
		Topic topic = form.converter(courseRepository);
		this.topicRepository.save(topic);
		
		URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicDto(topic));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TopicDetailsDto> findById(@PathVariable Long id) {
		Optional<Topic> topic = this.topicRepository.findById(id);
		if (topic.isPresent()) {
			return ResponseEntity.ok(new TopicDetailsDto(topic.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = KEY_TOPICS_LIST, allEntries = true)
	public ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody @Valid UpdateTopicForm form) {
		Optional<Topic> optional = this.topicRepository.findById(id);
		if (optional.isPresent()) {
			Topic topic = form.update(id, this.topicRepository);
			return ResponseEntity.ok(new TopicDto(topic));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = KEY_TOPICS_LIST, allEntries = true)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Topic> topicOptional = this.topicRepository.findById(id);
		if (topicOptional.isPresent()) {
			this.topicRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}








package br.com.alura.forum.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;

@Getter
@Setter
public class TopicoForm {

	@NotBlank
	@Length(min = 5)
	private String titulo;

	@NotBlank
	@Length(min = 10)
	private String mensagem;

	@NotBlank
	private String nomeCurso;

	public Topico converter(CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findByNome(nomeCurso);
		return new Topico(titulo, mensagem, curso);
	}

}

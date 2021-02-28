package br.com.alura.forum.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;

@Getter
@Setter
public class AtualizacaoTopicoForm {
	
	@NotBlank
	@Length(min = 5)
	private String titulo;

	@NotBlank
	@Length(min = 10)
	private String mensagem;

	public Topico atualizar(Long id, TopicoRepository topicoRepository) {
		Topico topico = topicoRepository.getOne(id);
		
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		
		return topico;
	}
	
}

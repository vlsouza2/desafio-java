package br.com.desafio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.time.Instant;
import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.desafio.model.Projeto;
import br.com.desafio.repository.ProjetoRepository;

@DataJpaTest
public class ProjetoRepositoryTest {
	
	@Autowired
	private ProjetoRepository projetoRepository;

	@Test
	public void testSaveProjeto() {
		
		Projeto projetoBuilder = Projeto.builder().id(1L)
				.dataInicio(Date.from(Instant.parse("2023-10-01T00:00:00.000Z")))
				.dataFim(Date.from(Instant.parse("2024-01-06T00:00:00.000Z")))
				.dataPrevisaoFim(Date.from(Instant.parse("2024-01-04T00:00:00.000Z"))).descricao("Teste desc")
				.nome("Teste nome").orcamento(new Double("22")).risco("Baixo").status("iniciado").build();

		Projeto savedProjeto = projetoRepository.save(projetoBuilder);

		Assertions.assertThat(savedProjeto).isNotNull();

	}
	
	
	@Test
	public void testDeleteProjeto() {
		Projeto projeto = new Projeto();
		ArgumentCaptor<Projeto> arg = ArgumentCaptor.forClass(Projeto.class);

		verify(projetoRepository).delete(arg.capture());
		assertEquals(projeto.getId(), arg.getValue().getId());

	}
}

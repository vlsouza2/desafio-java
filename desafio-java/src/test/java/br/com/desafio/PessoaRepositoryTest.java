package br.com.desafio;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import br.com.desafio.model.Pessoa;
import br.com.desafio.repository.PessoaRepository;

@DataJpaTest
public class PessoaRepositoryTest {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Test
	public void testSavePessoa() {
				
		Pessoa pessoaBuilder = Pessoa.builder().id(7L).cpf("625.587.600-41")
				.dataNascimento(Date.from(Instant.parse("1981-01-01T00:00:00.000Z"))).funcionario(true).build();

		Pessoa savedPessoa = pessoaRepository.save(pessoaBuilder);

		Assertions.assertThat(savedPessoa).isNotNull();
		Assertions.assertThat(savedPessoa.getId()).isGreaterThan(0);

	}
	
	@Test
	public void testGeAllPessoas() {
		
		Pessoa pessoaBuilder1 = Pessoa.builder().id(9L).cpf("622.587.600-41")
				.dataNascimento(Date.from(Instant.parse("1983-01-01T00:00:00.000Z"))).funcionario(true).build();
		
		Pessoa pessoaBuilder2 = Pessoa.builder().id(10L).cpf("628.587.600-41")
				.dataNascimento(Date.from(Instant.parse("1984-01-01T00:00:00.000Z"))).funcionario(true).build();

		 pessoaRepository.save(pessoaBuilder1);
		 
		 pessoaRepository.save(pessoaBuilder2);
		 
		 
		 List<Pessoa> pessoaList = pessoaRepository.findAll();
		 
		 
		 Assertions.assertThat(pessoaList).isNotNull();
		 
		 Assertions.assertThat(pessoaList.size()).isEqualTo(2);
		
	}	
	


}

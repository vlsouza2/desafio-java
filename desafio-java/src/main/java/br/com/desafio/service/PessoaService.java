package br.com.desafio.service;

import java.util.List;
import java.util.Optional;

import br.com.desafio.model.Pessoa;

public interface PessoaService {

	List<Pessoa> getAll();
	
	List<Pessoa> getAllWithoutProjeto();
	
	List<Pessoa> getAllFuncionarios();
	
	Optional<Pessoa> findOne(Long id);
	
	Pessoa create(Pessoa pessoa);
	
	Pessoa update(Pessoa pessoa);
	
	void delete(Long id);
}

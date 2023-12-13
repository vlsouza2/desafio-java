package br.com.desafio.service;

import java.util.List;
import java.util.Optional;

import br.com.desafio.model.Projeto;

public interface ProjetoService {

	List<Projeto> getAll();
	
	Optional<Projeto> findOne(Long id);
	
	Projeto create(Projeto projeto);
	
	Projeto update(Projeto projeto);
	
	void delete(Long id);
}

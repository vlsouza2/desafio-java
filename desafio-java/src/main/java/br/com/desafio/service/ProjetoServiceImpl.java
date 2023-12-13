package br.com.desafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.model.Projeto;
import br.com.desafio.repository.ProjetoRepository;

@Service
public class ProjetoServiceImpl implements ProjetoService{
	

	private ProjetoRepository projetoRepository;

	@Autowired
	public ProjetoServiceImpl(ProjetoRepository projetoRepository) {
		this.projetoRepository = projetoRepository;
	}

	@Override
	public List<Projeto> getAll() {
		return projetoRepository.findAll();
	}

	@Override
	public Optional<Projeto> findOne(Long id) {
		return projetoRepository.findById(id);
	}

	@Override
	public Projeto create(Projeto projeto) {
		return projetoRepository.save(projeto);
	}

	@Override
	public Projeto update(Projeto projeto) {
		return projetoRepository.save(projeto);
	}

	@Override
	public void delete(Long id) {
		Optional<Projeto> projeto = projetoRepository.findById(id);
		projeto.get().setMembros(null);
		projeto.get().setIdGerente(null);
		projetoRepository.save(projeto.get());
		projetoRepository.deleteById(id);
	}
}

package br.com.desafio.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.desafio.model.Projeto;
import br.com.desafio.service.PessoaService;
import br.com.desafio.service.ProjetoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/projeto")
public class ProjetoController {
	
	private ProjetoService projetoService;
	private PessoaService pessoaService;
 
	@Autowired
	public ProjetoController(ProjetoService projetoService, PessoaService pessoaService) {
		this.projetoService = projetoService;
		this.pessoaService = pessoaService;
	}

	@GetMapping("")
	public String home(Model model) {
		log.info("ProjetoController - home");
		model.addAttribute("projetos", projetoService.getAll());
		return "projeto/index";
	}
	
	@GetMapping("/novo")
	public ModelAndView novo() {
		log.info("ProjetoController - novo");
		return new ModelAndView("projeto/novo", "projeto", new Projeto())
				.addObject("pessoas", pessoaService.getAllWithoutProjeto())
				.addObject("funcionarios", pessoaService.getAllFuncionarios());
	}
	
	@PostMapping("/salvar")
	public String salvar(@ModelAttribute Projeto projeto){
		log.info("ProjetoController - salvar");
		projetoService.create(projeto);
        return "redirect:/projeto";
    }
	
	@GetMapping("/{id}/editar")
	public ModelAndView editar(@PathVariable("id") Long id) {
		log.info("ProjetoController - editar");
        Optional<Projeto> projeto = projetoService.findOne(id);
        return new ModelAndView("projeto/editar", "projeto", projeto.get())
        		.addObject("pessoas", pessoaService.getAllWithoutProjeto())
				.addObject("funcionarios", pessoaService.getAllFuncionarios());
    }
	
	@PostMapping("/atualizar")
	public String atualizar(@ModelAttribute Projeto projeto) {
		log.info("ProjetoController - atualizar");
		projetoService.update(projeto);
		return "redirect:/projeto";
	}
	
	@GetMapping("/{id}/excluir")
	public String excluir(@PathVariable("id") Long id) {
		log.info("ProjetoController - excluir");
		if (projetoService.findOne(id).get().getStatus().equals("iniciado")
				|| projetoService.findOne(id).get().getStatus().equals("em_andamento")
				|| projetoService.findOne(id).get().getStatus().equals("encerrado")) {
			log.info("ProjetoController - excluir - Projeto não pode ser excluído");
		} else {
			projetoService.delete(id);
		}
		return "redirect:/projeto";
	}
}

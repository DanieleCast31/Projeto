package br.edu.ifrn.crud.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import br.edu.ifrn.crud.dominio.Programar;
import br.edu.ifrn.crud.dominio.Setor;
import br.edu.ifrn.crud.dto.AutocompleteDTO;
import br.edu.ifrn.crud.repository.ProgramarRepository;
import br.edu.ifrn.crud.repository.SetorRepository;




@Controller
@RequestMapping("/programar")
public class ProgramarController {
	
	@Autowired
	private ProgramarRepository programarRepository;
	
	@Autowired
	private SetorRepository setorRepository;
	
	@GetMapping("/programar")
	public String entarCadastro(ModelMap model) {
		model.addAttribute("programar" , new Programar());
		return "/programar";
	}
	
	@PostMapping("/salvar")
	public String salvar(Programar programar, RedirectAttributes attr,
								HttpSession sessao) {
		
		programarRepository.save(programar);
	attr.addFlashAttribute("msgSucesso", "Operação Realizada com Sucesso!");
	
		return "redirect:/programar/programar";
	}
	
	@GetMapping("/editar/{id}")
	public String iniciarEdicao(
			@PathVariable("id")Integer idProgramar,
			ModelMap model,
			HttpSession sessao
		) {
		
		Programar p = programarRepository.findById(idProgramar).get();
		
		model.addAttribute("programar", p);

		return "/programar";
	} 
	
	@SuppressWarnings({})
	@GetMapping("/remover/{id}")
	public String remover(@PathVariable("id") Integer idProgramar, HttpSession sessao, RedirectAttributes attr) {

		programarRepository.findById(idProgramar);
		attr.addFlashAttribute("msgSucesso", "Programação removida com Sucesso!!!");

		return "redirect:/programar/buscar";
	}
	
	@GetMapping("/autocompleteSetores")
	@Transactional(readOnly = true)
	@ResponseBody
	public List<AutocompleteDTO> autocompleteSetores(
			@RequestParam("term") String termo){
		

		List<Setor> setor = setorRepository.findByNome(termo);
		
		List<AutocompleteDTO> resultados = new ArrayList<>();
		
		setor.forEach(p -> resultados.add(
			new AutocompleteDTO(p.getNome(), p.getId())
		));
		
		return resultados;
	}
	
	
	
	@ModelAttribute("dia")
	public List<String> getProfissoes(){
		return Arrays.asList("Segunda-Feira", "Terça-Feira", "Quarta-Feira",
				"Quinta-Feira", "Sexta-Feira", "Sabado", "Domingo");
	}
	
}
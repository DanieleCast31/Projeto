package br.edu.ifrn.crud.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.crud.dominio.Setor;
import br.edu.ifrn.crud.dominio.Equipamento;
import br.edu.ifrn.crud.dto.AutocompleteDTO;
import br.edu.ifrn.crud.repository.EquipamentoRepository;
import br.edu.ifrn.crud.repository.SetorRepository;

@Controller
@RequestMapping("/equipamentos")
public class EquipamentoController {
	
	@Autowired
	private EquipamentoRepository equipamentoRepository;
	
	@Autowired
	private SetorRepository setorRepository;
	
	@GetMapping("/cadastro")
	public String entarCadastro(ModelMap model) {
		model.addAttribute("equipamento" , new Equipamento());
		return "/equipamento";
	}
	
	//metodo de cadastro e edicao
	@PostMapping("/salvar")
	public String salvar(Equipamento equipamento, RedirectAttributes attr,
								HttpSession sessao) {
		
		equipamentoRepository.save(equipamento);
	attr.addFlashAttribute("msgSucesso", "Operação Realizada com Sucesso!");
	
		return "redirect:/equipamentos/cadastro";
	}
	
	//metodo serve para completar a palavra digitada autocomplete
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
	
}
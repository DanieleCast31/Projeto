package br.edu.ifrn.crud.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.crud.dominio.Equipamento;
import br.edu.ifrn.crud.dominio.Programar;
import br.edu.ifrn.crud.repository.EquipamentoRepository;
import br.edu.ifrn.crud.repository.ProgramarRepository;

@Controller
@RequestMapping("/agendar")
public class AgendarController {

	@Autowired
	private ProgramarRepository programarRepository;
	
	@Autowired
	private EquipamentoRepository equipamentoRepository;

	//metodo de buscar programacoes no banco de dados
	@GetMapping("/busca")
	public String entrarAgendar( HttpSession sessao, ModelMap model) {
		
		

		List<Programar> programacoesEncontradas = programarRepository.findAll();
		List<Equipamento>  equipamentosEncontrados = equipamentoRepository.findAll();
		
		model.addAttribute("programacoesEncontradas", programacoesEncontradas);
		model.addAttribute("equipamentosEncontrados",equipamentosEncontrados);
		
		return "agendar";
	}
	
	//metodo para remover programaçoes do banco de dados pelo id da programacao
	@SuppressWarnings({})
	@GetMapping("/remover/{id}")
	public String remover(@PathVariable("id") Integer idProgramar, HttpSession sessao, RedirectAttributes attr) {

		programarRepository.findById(idProgramar);
		attr.addFlashAttribute("msgSucesso", "Programação removida com Sucesso!!!");

		return "redirect:/programar/buscar";
	}
	


}
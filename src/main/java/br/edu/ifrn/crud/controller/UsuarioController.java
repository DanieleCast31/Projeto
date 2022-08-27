package br.edu.ifrn.crud.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.crud.dominio.Cidade;
import br.edu.ifrn.crud.dominio.Usuario;
import br.edu.ifrn.crud.dto.AutocompleteDTO;
import br.edu.ifrn.crud.repository.CidadeRepository;
import br.edu.ifrn.crud.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository; 
	
	@GetMapping("/cadastro")
	public String entarCadastro(ModelMap model) {
		model.addAttribute("usuario" , new Usuario());
		return "/usuario";
	}
	
	@PostMapping("/salvar")
	public String salvar(Usuario usuario, RedirectAttributes attr,
								HttpSession sessao) {
		
		//criptografando senha
		String senhaCriptografada = 
				new  BCryptPasswordEncoder().encode(usuario.getSenha());
				usuario.setSenha(senhaCriptografada);
	
	//Já serve para cadastro e ediçao
	usuarioRepository.save(usuario);
	attr.addFlashAttribute("msgSucesso", "Operação Realizada com Sucesso!");
	
		return "redirect:/usuarios/cadastro";
	}
	
	@GetMapping("/autocompleteCidades")
	@Transactional(readOnly = true)
	@ResponseBody
	public List<AutocompleteDTO> autocompleteCidades(
			@RequestParam("term") String termo){
		

		List<Cidade> cidades = cidadeRepository.findByNome(termo);
		
		List<AutocompleteDTO> resultados = new ArrayList<>();
		
		cidades.forEach(p -> resultados.add(
			new AutocompleteDTO(p.getNome(), p.getId())
		));
		
		return resultados;
	}
}
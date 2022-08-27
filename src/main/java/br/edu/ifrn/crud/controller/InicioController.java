package br.edu.ifrn.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
	
	//metodo que abri pagina principal
	@GetMapping("/")
	public String inicio(){
		return "login";
	}
	
	//metodo que abri a pagina se dos dados de login estiver correto
	@GetMapping("/login")
	public String login(){
		return "menu";
	}
	
	////metodo que mostra mensagem de erro se os dados de login estiver incorretos
	@GetMapping("/login-error")
	public String loginError(ModelMap model){
		model.addAttribute("msgErro", "Login ou senha incorretos.Tente novamente");
		return "login";
	}
}
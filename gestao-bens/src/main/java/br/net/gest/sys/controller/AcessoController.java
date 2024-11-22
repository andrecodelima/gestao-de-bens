package br.net.gest.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.net.gest.sys.entity.Acesso;
import br.net.gest.sys.service.AcessoServiceInterface;

@Controller
public class AcessoController {

	@Autowired
	private AcessoServiceInterface acessoServiceInterface;
	
	@GetMapping("/")
	public String teste() {
		
		return "/index";
	}
	
	
	@PostMapping("/saveAcesso")
	public Acesso saveAcesso(Acesso acesso) {
		
		return acessoServiceInterface.saveAcesso(acesso);
	}
	
	
}

package br.net.gest.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.net.gest.sys.entity.Acesso;
import br.net.gest.sys.repository.AcessoRepository;
import br.net.gest.sys.service.AcessoServiceInterface;

@RestController
public class AcessoController {

	@Autowired
	private AcessoServiceInterface acessoServiceInterface;
	
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	
	@GetMapping("/")
	public String teste() {
		
		return "/index";
	}
	
	
	@PostMapping(value = "/saveAcesso") // Mapeando a URL para receber o JSON
	public ResponseEntity<Acesso> saveAcesso(@RequestBody Acesso acesso) { // Recebe o Json e converte para objeto
		
		Acesso acessoSalvo = acessoServiceInterface.saveAcesso(acesso);
		
//		return new ResponseEntity<>(acessoSalvo, HttpStatus.CREATED);
		return ResponseEntity.status(HttpStatus.CREATED).body(acessoSalvo);
			
	}
	
	@DeleteMapping(value = "/deleteAcesso")
	public ResponseEntity<Acesso> deleteAcesso(@RequestBody Acesso acesso){
		
		acessoRepository.deleteById(acesso.getId());
		
		return new ResponseEntity("Acesso Removido", HttpStatus.OK);
		
	}
	
	
	
	
	@GetMapping("/acessos")
	public List<Acesso> getAllAcessos(){
		
		return acessoRepository.findAll();
	}
	
	
}

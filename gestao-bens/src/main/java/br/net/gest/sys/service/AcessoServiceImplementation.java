package br.net.gest.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.net.gest.sys.entity.Acesso;
import br.net.gest.sys.repository.AcessoRepository;

@Service
public class AcessoServiceImplementation implements AcessoServiceInterface{

	@Autowired
	private AcessoRepository acessoRepository;
	
	
	@Override
	public Acesso saveAcesso(Acesso acesso) {
		 
		return acessoRepository.save(acesso);
	}

	
}

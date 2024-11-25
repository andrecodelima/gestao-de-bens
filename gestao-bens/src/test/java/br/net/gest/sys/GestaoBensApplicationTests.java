package br.net.gest.sys;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.net.gest.sys.controller.AcessoController;
import br.net.gest.sys.entity.Acesso;
import br.net.gest.sys.repository.AcessoRepository;

@SpringBootTest(classes = GestaoBensApplication.class)
public class GestaoBensApplicationTests {

	@Autowired
	private AcessoController acessoController;
	
	
	@Test
	public void testeCadastraAcesso() {
		
		Acesso acesso = new Acesso();
		
		acesso.setDescricao("ROLE_USER");
		
		acessoController.saveAcesso(acesso);
		
	}

}

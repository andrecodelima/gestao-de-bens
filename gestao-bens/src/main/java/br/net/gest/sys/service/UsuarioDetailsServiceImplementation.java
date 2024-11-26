package br.net.gest.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.net.gest.sys.entity.Usuario;
import br.net.gest.sys.repository.UsuarioRepository;


@Service
public class UsuarioDetailsServiceImplementation implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	 
		Usuario usuario = usuarioRepository.findUsuarioByLogin(username); // Recebe o Login para consulta
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getAuthorities());
	}

	
	
	
}

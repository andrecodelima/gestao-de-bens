package br.net.gest.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.net.gest.sys.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query(value="SELECT u FROM Usuario u WHERE u.login = ?1")
	Usuario findUsuarioByLogin(String login);
	
}

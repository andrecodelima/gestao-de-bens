package br.net.gest.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.net.gest.sys.entity.Acesso;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Long> {

	
}

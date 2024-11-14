package br.net.gest.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.net.gest.sys.entity.Beneficio;

@Repository
public interface BeneficioRepository extends JpaRepository<Beneficio, Long> {

}

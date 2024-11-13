package br.net.gest.sys.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="usuario")
@SequenceGenerator(name="usuario_id_seq", sequenceName = "usuario_id_seq", initialValue = 1, allocationSize = 1)
public class Usuario {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="perfil_acesso", length = 50)
	private String perfilAcesso;
	
	@Column(name="login", length = 100, unique = true, nullable = false)
	private String login;
	
	@Column(name="senha", length = 255, nullable = false)
	private String senha;
	
	@Column(name="data_atual_senha")
	private LocalDate dataAtualSenha;
	
	@Column(name="data_criacao")
	private LocalDate dataCriacao;
	
	@Column(name="data_modificacao")
	private LocalDate dataModificacao;
	
	@Column(name="status")
	private boolean status = false;
	
	
	
	
	
	
}

package br.gov.ce.secult.water.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author nalomy.souza
 * @version 1.0
 */
@Entity
@Table(name = "tbwater_usuarios")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String login;
	private String nome;
	private String email;
	private String senha;
	private String cpf;
	private BigDecimal saldo;
	private boolean ativo;
	private Permissao permissao;
	private List<Credito> creditos;
	private List<Credito> creditosCadastrados;
	private List<Entrega> entregas;

	public Usuario() {
	}

	@Id
	@SequenceGenerator(name = "ci_usuario", sequenceName = "tbwater_usuarios_ci_usuario_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ci_usuario")
	@Column(name = "ci_usuario")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank
	@Column(name = "ds_login", nullable = false, length = 60)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@NotBlank
	@Column(name = "ds_nome", nullable = false, length = 60)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Email
	@Column(name = "ds_email", nullable = false, length = 40)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotBlank
	@Column(name = "ds_senha", nullable = false, length = 15)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Column(name = "ds_cpf", length = 20)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column(name = "vl_saldo", nullable = false)
	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Credito> getCreditos() {
		return creditos;
	}

	public void setCreditos(List<Credito> creditos) {
		this.creditos = creditos;
	}

	@Transient
	@OneToMany(mappedBy = "cadastrante", cascade = CascadeType.ALL)
	public List<Credito> getCreditosCadastrados() {
		return creditosCadastrados;
	}

	public void setCreditosCadastrados(List<Credito> creditosCadastrados) {
		this.creditosCadastrados = creditosCadastrados;
	}

	@ManyToMany(mappedBy="pagantes")
	public List<Entrega> getEntregas() {
		return entregas;
	}

	public void setEntregas(List<Entrega> entregas) {
		this.entregas = entregas;
	}

	@Column(name = "fl_ativo")
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "ds_permissao", nullable = false, length = 20)
	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
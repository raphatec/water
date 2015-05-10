package br.gov.ce.secult.water.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author nalomy.souza
 * @version 1.0
 */
@Entity
@Table(name = "tbwater_creditos")
public class Credito implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private BigDecimal valor;
	private String motivo;
	private Usuario usuario;
	private Usuario cadastrante;
	private Date dataCadastro;

	

	public Credito() {
	}

	/**
	 * Getters e Setters
	 */
	@Id
	@SequenceGenerator(name = "ci_credito", sequenceName = "tbwater_creditos_ci_credito_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ci_credito")
	@Column(name = "ci_credito")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "vl_valor", nullable = false)
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@NotBlank
	@Column(name = "ds_motivo", nullable = false)
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "ci_usuario", referencedColumnName = "ci_usuario", nullable = false)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "ci_cadastrante", referencedColumnName = "ci_usuario", nullable = false)
	public Usuario getCadastrante() {
		return cadastrante;
	}

	public void setCadastrante(Usuario cadastrante) {
		this.cadastrante = cadastrante;
	}
	

	@Column(name="dt_cadastro", nullable = false)
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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
		Credito other = (Credito) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

package br.gov.ce.secult.water.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbwater_entregas")
public class Entrega implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private BigDecimal valor;
	private BigDecimal valorCota;
	private Usuario cadastrante;
	private Date dataEntrega;
	private List<Usuario> pagantes;

	public Entrega() {

	}

	@Id
	@SequenceGenerator(name = "ci_entrega", sequenceName = "tbwater_entregas_ci_entrega_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ci_entrega")
	@Column(name = "ci_entrega")
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

	@Column(name = "vl_cota", nullable = false)
	public BigDecimal getValorCota() {
		return valorCota;
	}

	public void setValorCota(BigDecimal valorCota) {
		this.valorCota = valorCota;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_entrega", nullable = false)
	public Date getDataEntrega() {
		return dataEntrega;
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

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	@ManyToMany
	@JoinTable(name = "tbwater_pagamentos", joinColumns = { @JoinColumn(name = "ci_entrega") }, inverseJoinColumns = { @JoinColumn(name = "ci_usuario") })
	public List<Usuario> getPagantes() {
		return pagantes;
	}

	public void setPagantes(List<Usuario> pagantes) {
		this.pagantes = pagantes;
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
		Entrega other = (Entrega) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

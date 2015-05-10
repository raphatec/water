package br.gov.ce.secult.water.controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.gov.ce.secult.water.entity.Credito;
import br.gov.ce.secult.water.entity.Usuario;
import br.gov.ce.secult.water.service.CreditoService;
import br.gov.ce.secult.water.util.jsf.FacesUtil;

/**
 * @author nalomy.souza
 * @version 1.0
 * @see ManagedBean para Cadastro e Atualização de Crédito(s)
 */
@Named
@ViewScoped
public class FormCreditoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private Credito credito;
	@Inject
	private CreditoService creditoService;
	private List<Usuario> usuarios;

	public void init() {
		if (FacesUtil.isNotPostback()) {
			usuarios = creditoService.findOrderByName();
		}
	}

	public void salvar() {
		
		this.credito = creditoService.salvar(this.credito);
		
		if (isEditavel()) {
			FacesUtil.addInfoMessage("Informações Atualizadas com sucesso!");
		} else {
			limpar();
			FacesUtil.addInfoMessage("Cadastrado com sucesso!");
		}
	}

	public FormCreditoBean() {
		limpar();
	}

	private void limpar() {
		this.credito = new Credito();
	}

	public boolean isEditavel() {
		return this.credito.getId() != null;
	}

	public Credito getCredito() {
		return credito;
	}

	public void setCredito(Credito credito) {
		this.credito = credito;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

}

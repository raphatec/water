package br.gov.ce.secult.water.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.ce.secult.water.entity.Credito;
import br.gov.ce.secult.water.service.CreditoService;
import br.gov.ce.secult.water.util.jsf.FacesUtil;

/**
 * @author nalomy.souza
 * @version 1.0
 * @see ManagedBean para Consultas, Filtros e Exclusão de Crédito(s)
 */
@Named
@ViewScoped
public class ListCreditoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private Credito creditoSelecionado;
	@Inject
	private CreditoService creditoService;
	private List<Credito> creditos;

	public void init() {
		if (creditos == null) {
			creditos = creditoService.AllOrderById();
		}
	}
	
	public void excluir(){
		if(creditoSelecionado != null){
			creditoService.remover(creditoSelecionado);
			creditos = null;
			FacesUtil.addInfoMessage("Crédito do usuário " + creditoSelecionado.getUsuario().getNome() + " excluído com sucesso.");
		
		}else{FacesUtil.addErrorMessage("Você não selecionou o crédito que será excluído.");}
	}

	public Credito getCreditoSelecionado() {
		return creditoSelecionado;
	}

	public void setCreditoSelecionado(Credito creditoSelecionado) {
		this.creditoSelecionado = creditoSelecionado;
	}

	public List<Credito> getCreditos() {
		return creditos;
	}

}

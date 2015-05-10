package br.gov.ce.secult.water.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.ce.secult.water.entity.Entrega;
import br.gov.ce.secult.water.service.EntregaService;
import br.gov.ce.secult.water.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ListEntregaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Entrega entregaSelecionado;
	@Inject
	private EntregaService entregaService;
	private List<Entrega> entregas;

	public void init() {
		if (entregas == null) {
			entregas = entregaService.AllOrderById();
		}
	}

	public void excluir() {
		if (entregaSelecionado != null) {
			entregaService.remover(entregaSelecionado);
			entregas = null;
			FacesUtil.addInfoMessage("Entrega excluída com sucesso.");

		} else {
			FacesUtil
					.addErrorMessage("Você não selecionou a entrega que será excluído.");
		}
	}

	public Entrega getEntregaSelecionado() {
		return entregaSelecionado;
	}

	public void setEntregaSelecionado(Entrega entregaSelecionado) {
		this.entregaSelecionado = entregaSelecionado;
	}

	public List<Entrega> getEntregas() {
		return entregas;
	}

}

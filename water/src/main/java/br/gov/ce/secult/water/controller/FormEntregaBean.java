package br.gov.ce.secult.water.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.ce.secult.water.entity.Entrega;
import br.gov.ce.secult.water.entity.Usuario;
import br.gov.ce.secult.water.service.EntregaService;
import br.gov.ce.secult.water.service.UsuarioService;
import br.gov.ce.secult.water.util.jsf.FacesUtil;

@Named
@ViewScoped
public class FormEntregaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private Entrega entrega;
	@Inject
	private EntregaService entregaService;
	@Inject
	private UsuarioService usuarioService;
	private List<Usuario> usuarios;
	private List<Usuario> usuariosSelecionados;

	public void init() {
		if (FacesUtil.isNotPostback()) {
			usuarios = usuarioService.allOrderByNome();
		}
	}

	public void salvar() {
		if (isEditavel()) {
			entrega = entregaService.salvar(this.entrega);
			FacesUtil.addInfoMessage("Informações Atualizadas com sucesso!");
		} else {
			entrega.setPagantes(usuariosSelecionados);
			entrega = entregaService.salvar(this.entrega);
			limpar();
			FacesUtil.addInfoMessage("Cadastrado com sucesso!");
		}
	}

	public FormEntregaBean() {
		limpar();
	}

	private void limpar() {
		this.entrega = new Entrega();
	}

	public boolean isEditavel() {
		return this.entrega.getId() != null;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public List<Usuario> getUsuariosSelecionados() {
		return usuariosSelecionados;
	}

	public void setUsuariosSelecionados(List<Usuario> usuariosSelecionados) {
		this.usuariosSelecionados = usuariosSelecionados;
	}

	public void setCredito(Entrega entrega) {
		this.entrega = entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

}

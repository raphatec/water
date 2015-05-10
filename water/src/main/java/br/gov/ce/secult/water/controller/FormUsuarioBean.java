package br.gov.ce.secult.water.controller;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.ce.secult.water.entity.Permissao;
import br.gov.ce.secult.water.entity.Usuario;
import br.gov.ce.secult.water.service.UsuarioService;
import br.gov.ce.secult.water.util.jsf.FacesUtil;

/**
 * @author nalomy.souza
 * @version 1.0
 * @see ManagedBean para Cadastro e Atualização de Usuario(s)
 */
@Named
@ViewScoped
public class FormUsuarioBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioService usuarioService;
	@Inject
	private Usuario usuario;

	private Permissao[] permissoes;

	public void init() {
		if (FacesUtil.isNotPostback()) {
			permissoes = Permissao.values();
			System.out.println("executou");
		}

	}

	public void salvar() {
		if (isEditavel()) {
			this.usuario = usuarioService.salvar(this.usuario);
			FacesUtil.addInfoMessage("Informações Atualizadas com sucesso!");
		} else {
			this.usuario.setSaldo(new BigDecimal(0));
			this.usuario = usuarioService.salvar(this.usuario);
			limpar();
			FacesUtil.addInfoMessage("Cadastrado com sucesso!");
		}
	}

	public FormUsuarioBean() {
		limpar();
	}

	private void limpar() {
		this.usuario = new Usuario();
	}

	public boolean isEditavel() {
		return this.usuario.getId() != null;
	}

	public Permissao[] getPermissoes() {
		return permissoes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

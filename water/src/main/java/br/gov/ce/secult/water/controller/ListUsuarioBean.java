package br.gov.ce.secult.water.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.ce.secult.water.entity.Usuario;
import br.gov.ce.secult.water.service.UsuarioService;
import br.gov.ce.secult.water.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ListUsuarioBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioService usuarioService;
	@Inject
	private Usuario selecionado;

	private List<Usuario> usuarios;

	public void init() {
		if (usuarios == null) {
			usuarios = usuarioService.allOrderByNome();
			System.out.println("preenchendo a lista de usuário");
		}
		System.out.println("concluindo o init");
	}

	public void excluir() {
		if(selecionado != null){
			usuarioService.remover(selecionado);
			usuarios = null;
			FacesUtil.addInfoMessage("Usuário " + selecionado.getNome() + " excluído com sucesso.");
		
		}else{FacesUtil.addErrorMessage("Você não selecionou o usuário que será excluído.");}
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public Usuario getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Usuario selecionado) {
		this.selecionado = selecionado;
	}

}

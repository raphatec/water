package br.gov.ce.secult.water.service;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.gov.ce.secult.water.entity.Usuario;
import br.gov.ce.secult.water.security.UsuarioLogado;
import br.gov.ce.secult.water.security.UsuarioSistema;

/**
 * @author
 * @see Retorno de Objeto de UsuarioSistema e Usuario, nome do usuário logado
 *
 */
@Named
@RequestScoped
public class SecurityService {

	@Inject
	private ExternalContext externalContext;

	/**
	 * @see Retorna o nome do usuário logado
	 * 
	 * @return String
	 */
	public String getNomeUsuario() {
		String nome = null;
		UsuarioSistema usuarioLogado = getUsuarioLogado();
		if (usuarioLogado != null) {
			nome = usuarioLogado.getUsuario().getNome();
		}
		return nome;
	}

	/**
	 * @see Retorna o Objeto usuário
	 * 
	 * @return Usuario
	 */
	public Usuario getUsuario() {
		Usuario nome = null;
		UsuarioSistema usuarioLogado = getUsuarioLogado();
		if (usuarioLogado != null) {
			nome = usuarioLogado.getUsuario();
		}
		return nome;
	}

	/**
	 * @see classe produtora de Objeto UsuarioSistema
	 * 
	 * @return UsuarioSistema
	 */
	@Produces
	@UsuarioLogado
	public UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();
		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}
		return usuario;
	}

	public boolean isEnviarEmail() {
		return externalContext.isUserInRole("ADMINISTRADORES")
				|| externalContext.isUserInRole("GERENTES");
	}

}

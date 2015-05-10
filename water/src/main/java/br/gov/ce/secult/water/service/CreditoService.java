package br.gov.ce.secult.water.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import br.gov.ce.secult.water.entity.Credito;
import br.gov.ce.secult.water.entity.Usuario;
import br.gov.ce.secult.water.repository.CreditoDAO;
import br.gov.ce.secult.water.repository.UsuarioDAO;
import br.gov.ce.secult.water.security.Seguranca;
import br.gov.ce.secult.water.util.jpa.Transactional;

/**
 * 
 */
public class CreditoService implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private CreditoDAO creditoDAO;
	@Inject
	private UsuarioDAO usuarioDAO;
	@Inject
	private Seguranca seguranca;

	@Transactional
	public Credito salvar(Credito credito) {
		credito.setCadastrante(seguranca.getUsuario());
		creditoDAO.saveOrUpdate(credito);
		return credito;
	}

	@Transactional
	public void remover(Credito credito) {
		try {
			creditoDAO.remover(credito);
		} catch (PersistenceException e) {
			throw new NegocioException("Credito não pode ser excluído.");
		}
	}

	public List<Credito> AllOrderById() {
		return creditoDAO.findAllOrderById();
	}

	public List<Usuario> findOrderByName() {
		return usuarioDAO.findAllOrderByName();
	}
}

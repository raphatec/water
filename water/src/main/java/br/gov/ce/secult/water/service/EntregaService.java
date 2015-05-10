package br.gov.ce.secult.water.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import br.gov.ce.secult.water.entity.Entrega;
import br.gov.ce.secult.water.entity.Usuario;
import br.gov.ce.secult.water.repository.EntregaDAO;
import br.gov.ce.secult.water.util.jpa.Transactional;

public class EntregaService implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private EntregaDAO entregaDAO;
	@Inject
	private SecurityService securityService;
	@Inject
	private UsuarioService usuarioService;

	@Transactional
	public Entrega salvar(Entrega entrega) {
		if (entrega.getId() == null) {
			entrega.setCadastrante(securityService.getUsuario());
			entrega.setValorCota(new BigDecimal(entrega.getPagantes().size()).divide(entrega.getValor()));
			entrega = entregaDAO.saveOrUpdate(entrega);
		}

		for (Usuario pagante : entrega.getPagantes()) {
			usuarioService.atualizarSaldo(pagante, entrega.getValorCota());
		}

		return entrega;
	}

	@Transactional
	public void remover(Entrega entrega) {
		try {
			entregaDAO.remover(entrega);
		} catch (PersistenceException e) {
			throw new NegocioException("Entrega não pode ser excluída.");
		}
	}

	public List<Entrega> AllOrderById() {
		return entregaDAO.findAllOrderById();
	}

}

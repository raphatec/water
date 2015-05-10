package br.gov.ce.secult.water.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import br.gov.ce.secult.water.repository.UsuarioDAO;
import br.gov.ce.secult.water.entity.Usuario;
import br.gov.ce.secult.water.util.jpa.Transactional;

public class UsuarioService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO dao;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		dao.saveOrUpdate(usuario);
		return usuario;
	}

	@Transactional
	public void remover(Usuario usuario) {
		try {
			dao.remover(usuario);
		} catch (PersistenceException e) {
			throw new NegocioException("Produto não pode ser excluído.");
		}

	}

	public List<Usuario> allOrderByNome() {
		return dao.findAllOrderByName();
	}

	public void atualizarSaldo(Usuario usuario, BigDecimal cota) {
		BigDecimal novoSaldo = usuario.getSaldo().subtract(cota);
		usuario.setSaldo(novoSaldo);
		salvar(usuario);

	}
}

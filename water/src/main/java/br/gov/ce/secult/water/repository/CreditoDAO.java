package br.gov.ce.secult.water.repository;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import br.gov.ce.secult.water.entity.Credito;
import br.gov.ce.secult.water.util.jpa.Transactional;

/**
 * @author nalomy.souza
 * @version 1.0
 * @see Repositório para CRUD de Credito(s)
 */
public class CreditoDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	/**
	 * @see Salvar ou Atualizar
	 * @param Credito(Credito)
	 * 
	 * @return Credito
	 */
	@Transactional
	public Credito saveOrUpdate(Credito credito) {
		return manager.merge(credito);
	}

	/**
	 * @see Exclui o usuário
	 * @param Credito(credito)
	 */
	@Transactional
	public void remover(Credito credito) throws PersistenceException {
		credito = porId(credito.getId());
		manager.remove(credito);
		manager.flush();
	}
	
	/**
	 * @see Retorna o credito pesquisado pelo id
	 * @param Long (id)
	 * 
	 * @return Credito
	 */
	public Credito porId(Long id) {
		return this.manager.find(Credito.class, id);
	}

	/**
	 * @see Retorna a lista de todos os creditos ordenados por Id
	 * 
	 * @return List<Credito>
	 */
	public List<Credito> findAllOrderById() {
		return this.manager.createQuery("FROM Credito ORDER BY id ASC",
				Credito.class).getResultList();
	}
}

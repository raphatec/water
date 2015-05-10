package br.gov.ce.secult.water.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.gov.ce.secult.water.entity.Entrega;
import br.gov.ce.secult.water.util.jpa.Transactional;

public class EntregaDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private EntityManager manager;

	/**
	 * @see Salvar ou Atualizar
	 * @param Entrega(entrega)
	 * 
	 * @return Entrega
	 */
	@Transactional
	public Entrega saveOrUpdate(Entrega entrega) {
		return manager.merge(entrega);
	}

	/**
	 * @see Exclui o usuário
	 * @param Entrega(entrega)
	 */
	@Transactional
	public void remover(Entrega entrega) throws PersistenceException {
		entrega = porId(entrega.getId());
		manager.remove(entrega);
		manager.flush();
	}
	
	/**
	 * @see Retorna o entrega pesquisado pelo id
	 * @param Long (id)
	 * 
	 * @return Entrega
	 */
	public Entrega porId(Long id) {
		return this.manager.find(Entrega.class, id);
	}

	/**
	 * @see Retorna a lista de todos os creditos ordenados por Id
	 * 
	 * @return List<Entrega>
	 */
	public List<Entrega> findAllOrderById() {
		return this.manager.createQuery("FROM Entrega ORDER BY dataEntrega DESC",
				Entrega.class).getResultList();
	}

}

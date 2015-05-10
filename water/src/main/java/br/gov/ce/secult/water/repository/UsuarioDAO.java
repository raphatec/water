package br.gov.ce.secult.water.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.gov.ce.secult.water.entity.Usuario;
import br.gov.ce.secult.water.util.jpa.Transactional;
import br.gov.ce.secult.water.util.jsf.FacesUtil;

/**
 * @author nalomy.souza
 * @version 1.0
 * @see Repositório para CRUD de Usuario(s)
 */
public class UsuarioDAO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	/**
	 * @see Salvar ou Atualizar o usuário
	 * @param Usuario (usuario)
	 */
	@Transactional
	public Usuario saveOrUpdate(Usuario usuario) {
		return manager.merge(usuario);
	}

	/**
	 * @see Exclui o usuário
	 * @param Usuario (usuario)
	 */
	@Transactional
	public void remover(Usuario usuario) throws PersistenceException {
		usuario = porId(usuario.getId());
		manager.remove(usuario);
		manager.flush();
	}

	/**
	 * @see Retorna a lista de todos os usuários ordenador pelo nome
	 * @return List<Usuario>
	 */
	public List<Usuario> findAllOrderByName() {
		return this.manager.createQuery("FROM Usuario ORDER BY nome ASC",
				Usuario.class).getResultList();
	}

	/**
	 * @see Retorna o usuario pesquisado pelo id
	 * @param Long (id)
	 * 
	 * @return Usuario
	 */
	public Usuario porId(Long id) {
		return this.manager.find(Usuario.class, id);
	}

	/**
	 * @see Retorna a lista de usuarios pesquisados pelo nome
	 * @param String (nome)
	 * 
	 * @return List<Usuario>
	 */
	public List<Usuario> porNome(String nome) {
		return this.manager
				.createQuery("from Usuario " + "where upper(nome) like :nome",
						Usuario.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}

	/**
	 * @see Retorna o Usuario pesquisado pelo E-mail
	 * @param String (email)
	 * 
	 * @return Usuario
	 */
	public Usuario porLogin(String login) {
		Usuario usuario = null;
		try {
			usuario = this.manager
					.createQuery("from Usuario where lower(login) = :login",
							Usuario.class)
					.setParameter("login", login.toLowerCase())
					.getSingleResult();
		} catch (NoResultException e) {
			FacesUtil
					.addInfoMessage("Nenhum usuário encontrado com o login informado");
		}
		return usuario;
	}

}
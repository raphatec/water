package br.gov.ce.secult.water.entity;

/**
 * @author nalomy.souza
 * @version 1.0
 * @see Enumeration com as Permissoes possiveis de cada Usuario
 */
public enum Permissao {
	ADMINISTRADOR("Administrador"), GERENTE("Gerente"), SUPERVISOR("Supervisor"), USUARIO(
			"Usuário");

	private String descricao;

	Permissao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}

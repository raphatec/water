package br.gov.ce.secult.water.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import br.gov.ce.secult.water.entity.Entrega;
import br.gov.ce.secult.water.entity.Usuario;

public class PagamentoService implements Serializable {

//	private static final long serialVersionUID = 1L;
//	@Inject
//	private UsuarioService usuarioService;
//	@Inject
//	private EntregaService entregaService;
//	
//	public void realizarPagamento(List<Usuario> listaPagantes, Entrega entrega) {
//				
//		BigDecimal numPagantes = new BigDecimal(listaPagantes.size());
//		
//		BigDecimal valorCota = entrega.getValor().divide(numPagantes);
//
//		for (Usuario pagante : listaPagantes) {
//			usuarioService.atualizarSaldo(pagante, valorCota);
//		}
//		entrega.setPagantes(listaPagantes);
//		entregaService.salvar(entrega);
//	}
}

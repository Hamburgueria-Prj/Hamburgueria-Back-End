package br.com.hamburgueria_local.exceptions;

public class PedidoSemItensException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PedidoSemItensException(String message) {
		super(message);
	}
}

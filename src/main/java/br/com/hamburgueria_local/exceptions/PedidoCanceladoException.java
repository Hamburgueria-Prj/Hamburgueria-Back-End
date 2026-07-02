package br.com.hamburgueria_local.exceptions;

public class PedidoCanceladoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PedidoCanceladoException(String message) {
		super(message);
	}
}

package br.com.hamburgueria_local.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RecursoNaoEncontradoException(String message) {
		super(message);
	}
}

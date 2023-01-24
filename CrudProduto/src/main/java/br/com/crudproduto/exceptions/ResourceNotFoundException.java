package br.com.crudproduto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/*
a anotação @ResponseStatus faz com que uma exceccão do java seja disparada caso um
determinado código de status ocorra na execução do software, a exceccão abaixo é executada
caso o código 404(NOT FOUND) sejá encontrado em tempo de execução de um determinado método.
*/

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String ex) {
		super(ex);
	}

}
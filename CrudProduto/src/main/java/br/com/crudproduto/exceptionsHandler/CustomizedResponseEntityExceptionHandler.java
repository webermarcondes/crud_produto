package br.com.crudproduto.exceptionsHandler;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.crudproduto.exceptions.ExceptionResponse;
import br.com.crudproduto.exceptions.NotNumericException;
import br.com.crudproduto.exceptions.ResourceNotFoundException;

/*
CustomizedResponseEntityExceptionHandler cuida de todas as exceções geradas em tempo de execução, a anotação
@ControllerAdvice faz com que apenas esta classe desempenhe este papel.
 */


@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	/*
	 Quando a exceção do java é disparada a anotação @ExceptionHandler pega classe referente a exceccão
	 e faz com que o método abaixo dela gere um retorno apropriado a esta exceccão.
	 */
	@ExceptionHandler(NotNumericException.class)
	public final ResponseEntity<ExceptionResponse> 
		handleNotNumericExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),
				ex.getMessage(),
				request.getDescription(false)
				);
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST); // a ResponseEntity sempre deve conter o conteúdo de resposta e o código para o qual a resposta deve ser dada.
	}
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> 
		handleResourceNotFoundExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),
				ex.getMessage(),
				request.getDescription(false)
				);
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	

}

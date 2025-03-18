package com.rauxasoft.liga_futbol.presentation.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GestorCentralizadoExcepciones extends ResponseEntityExceptionHandler{


	// **********************************************************************************

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		CustomErrorResponse customErrorResponse = new CustomErrorResponse("No se puede parsear el objeto JSON");
		return ResponseEntity.badRequest().body(customErrorResponse);
	}

	// **********************************************************************************
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		CustomErrorResponse customErrorResponse = new CustomErrorResponse("El método [" + ex.getMethod() + "] no se permite.");
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(customErrorResponse);
	}

	// **********************************************************************************
	
	@Override
	protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		CustomErrorResponse customErrorResponse = new CustomErrorResponse("Recurso no encontrado.");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customErrorResponse);
	}
	
	// **********************************************************************************
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
	
		String tipoRequerido = ex.getRequiredType().getSimpleName();
		String tipoEntrante = ex.getValue().getClass().getSimpleName();
		
		CustomErrorResponse customErrorResponse = new CustomErrorResponse("El valor [" + ex.getValue() + "] es de tipo [" + tipoEntrante+ "]. Se requiere un tipo [" + tipoRequerido + "]");
		
		return ResponseEntity.badRequest().body(customErrorResponse);
	}
	
	// **********************************************************************************
	
	@ExceptionHandler(PresentationException.class)
	public ResponseEntity<Object> handlePresentationException(PresentationException ex){
	
		CustomErrorResponse customErrorResponse = new CustomErrorResponse(ex.getMessage());
		
		return new ResponseEntity<>(customErrorResponse, ex.getHttpStatus());
	}
	
	// **********************************************************************************
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex){
	
		CustomErrorResponse customErrorResponse = new CustomErrorResponse("Se ha producido un error en el servidor.");
		
		return ResponseEntity.internalServerError().body(customErrorResponse);
	}

	// **********************************************************************************
	
}

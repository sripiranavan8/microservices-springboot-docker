package com.sripiranavan.microservice.learning;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		ExceptionMessage exceptionMessage = new ExceptionMessage(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CurrencyExchangeNotFoundException.class)
	public final ResponseEntity<Object> handleCurrencyExchangeNotFoundException(Exception ex, WebRequest request)
			throws Exception {
		ExceptionMessage exceptionMessage = new ExceptionMessage(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionMessage, HttpStatus.NOT_FOUND);
	}
}

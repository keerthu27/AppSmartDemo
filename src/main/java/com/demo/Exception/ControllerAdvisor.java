package com.demo.Exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	/**
	 * To handle customer or product not found exception
	 * @param response
	 * @throws IOException
	 */
	@ExceptionHandler({ CustomerException.class, ProductException.class })
	public void exceptionHandleNotFound(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.NOT_FOUND.value());
	}

	/**
	 * To handle parameter validation exception
	 * @param response
	 * @throws IOException
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public void handleConstraintViolationError(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}

}

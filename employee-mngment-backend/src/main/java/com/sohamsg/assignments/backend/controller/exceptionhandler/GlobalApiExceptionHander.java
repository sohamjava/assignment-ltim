package com.sohamsg.assignments.backend.controller.exceptionhandler;

import java.util.NoSuchElementException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sohamsg.assignments.backend.model.ApiErrorResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalApiExceptionHander {

	@ExceptionHandler(exception = ConstraintViolationException.class)
	public ResponseEntity<ApiErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
		return ResponseEntity.badRequest()
				.body(ApiErrorResponse.builder().httpCode(HttpStatus.BAD_REQUEST.name()).message(ex.getMessage())
						.applicationErrorCode("APP-400").detailedMessage("Some field validation failed").build()

				);
	}

	@ExceptionHandler(exception = NoSuchElementException.class)
	public ResponseEntity<ApiErrorResponse> handleNoSuchElementException(NoSuchElementException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ApiErrorResponse.builder().httpCode(HttpStatus.NOT_FOUND.name()).message(ex.getMessage())
						.applicationErrorCode("APP-404").detailedMessage("ID not found for employee OR department")
						.build()

				);
	}

	@ExceptionHandler(exception = DataAccessException.class)
	public ResponseEntity<ApiErrorResponse> handleDataAccessException(DataAccessException ex) {
		return ResponseEntity.internalServerError()
				.body(ApiErrorResponse.builder().httpCode(HttpStatus.INTERNAL_SERVER_ERROR.name())
						.message(ex.getMessage()).applicationErrorCode("APP-500").detailedMessage("Database error")
						.build()

				);
	}

	@ExceptionHandler(exception = DataIntegrityViolationException.class)
	public ResponseEntity<ApiErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(ApiErrorResponse.builder().httpCode(HttpStatus.CONFLICT.name()).message(ex.getMessage())
						.applicationErrorCode("APP-409").detailedMessage("Database integiry violated").build()

				);
	}

	@ExceptionHandler(exception = Exception.class)
	public ResponseEntity<ApiErrorResponse> handleException(Exception ex) {
		return ResponseEntity.badRequest()
				.body(ApiErrorResponse.builder().httpCode(HttpStatus.INTERNAL_SERVER_ERROR.name())
						.message(ex.getMessage()).applicationErrorCode("APP-GENERIC-500")
						.detailedMessage("Generic Error").build()

				);
	}
}

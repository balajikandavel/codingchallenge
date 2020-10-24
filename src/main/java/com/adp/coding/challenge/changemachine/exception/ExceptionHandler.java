package com.adp.coding.challenge.changemachine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.adp.coding.challenge.changemachine.model.ErrorTO;

@ControllerAdvice
public class ExceptionHandler {
	@org.springframework.web.bind.annotation.ExceptionHandler(ChangeMachineException.class)
	public ResponseEntity<ErrorTO> generateNotFoundException(ChangeMachineException ex) {
		ErrorTO error = ex.getError();

		return new ResponseEntity<ErrorTO>(error, HttpStatus.BAD_REQUEST);
	}
}
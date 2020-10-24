package com.adp.coding.challenge.changemachine.exception;

import org.springframework.beans.factory.annotation.Autowired;

import com.adp.coding.challenge.changemachine.model.ErrorTO;

import lombok.Getter;

@Getter
public class ChangeMachineException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ErrorTO error;

	public ChangeMachineException(ErrorTO error) {
		super(error.getMessage());
		this.error = error;

	}
}
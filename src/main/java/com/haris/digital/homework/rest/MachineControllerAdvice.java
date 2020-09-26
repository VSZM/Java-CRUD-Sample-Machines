package com.haris.digital.homework.rest;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.haris.digital.homework.exceptions.MissingMachineException;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error")
public class MachineControllerAdvice {


	@ResponseBody
	@ExceptionHandler(MissingMachineException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String missingMachineHandler(MissingMachineException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String badArgumentHandler(IllegalArgumentException ex) {
		return ex.getMessage();
	}
}

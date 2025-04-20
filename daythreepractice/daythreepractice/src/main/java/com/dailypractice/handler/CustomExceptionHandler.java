package com.dailypractice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dailypractice.exception.UserNotFoundException;
import com.dailypractice.response.SuccessResponse;

@RestControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<SuccessResponse> userNotFoundException(UserNotFoundException exception) {
		return new ResponseEntity<>(new SuccessResponse(true, exception.getMessage(), null), HttpStatus.BAD_REQUEST);
	}

}

package com.example.VaccinationControl.exceptions;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDTO> handlerMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        Set<ErrorDTO> errors = exception.getBindingResult().getFieldErrors().stream().map(error -> buildError(error.getCode(), error.getDefaultMessage())).collect(Collectors.toSet());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseErrorBuilder(HttpStatus.BAD_REQUEST, errors));
    }

	@ExceptionHandler(Throwable.class) 
	public ResponseEntity<ApiErrorDTO> handlerMethodThrowable(Throwable exception) { 
		Set<ErrorDTO> errors = Set.of(buildError("InvalidDate" , "The date format must be dd/MM/yyyy")); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseErrorBuilder(HttpStatus.BAD_REQUEST, errors)); 
	}
	
	@ExceptionHandler(RepeatedEmail.class)
	public ResponseEntity<ApiErrorDTO> repeatedEmail(RepeatedEmail email) {
		Set<ErrorDTO> errors = Set.of(buildError("E-mail", email.getLocalizedMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseErrorBuilder(HttpStatus.BAD_REQUEST, errors));
	}
	
	@ExceptionHandler(RepeatedCPF.class)
	public ResponseEntity<ApiErrorDTO> repeatedCpf(RepeatedCPF cpf) {
		Set<ErrorDTO> errors = Set.of(buildError("CPF", cpf.getLocalizedMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseErrorBuilder(HttpStatus.BAD_REQUEST, errors));
	}
	
	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<ApiErrorDTO> userNotFound(UserNotFound user) {
		Set<ErrorDTO> errors = Set.of(buildError("User not found", user.getLocalizedMessage()));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(baseErrorBuilder(HttpStatus.NOT_FOUND, errors));
	}
	  
    private ErrorDTO buildError(String code, String message) {
        return new ErrorDTO(code, message);
    }

    private ApiErrorDTO baseErrorBuilder(HttpStatus httpStatus, Set<ErrorDTO> errorList) {
        return new ApiErrorDTO(new Date(), httpStatus.value(), httpStatus.name(), errorList);
    }
}
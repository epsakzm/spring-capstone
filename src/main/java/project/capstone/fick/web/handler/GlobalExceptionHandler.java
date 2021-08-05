package project.capstone.fick.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;


@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> illegalArgumentExceptionHandler(IllegalArgumentException exception) {
		Throwable cause = exception.getCause();
		return ResponseEntity.badRequest().body(cause.toString());
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> noSuchElementExceptionHandler(NoSuchElementException exception) {
		Throwable cause = exception.getCause();
		return ResponseEntity.badRequest().body(cause.toString());
	}
}

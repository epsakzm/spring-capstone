package project.capstone.fick.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;


//@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<?> illegalArgumentExceptionHandler(IllegalArgumentException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());
	}

	@ExceptionHandler
	public ResponseEntity<?> noSuchElementExceptionHandler(NoSuchElementException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());
	}
}

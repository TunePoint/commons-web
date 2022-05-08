package ua.tunepoint.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.tunepoint.web.model.StatusResponse;

@Slf4j
@ControllerAdvice
public class WebExceptionHandler {

    private static final String INTERNAL_ERROR_MESSAGE = "Oops. Server error occurred :(";

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StatusResponse> handleBadRequest(BadRequestException ex) {
        return ResponseEntity.badRequest()
                .body(StatusResponse.builder().message(ex.getMessage()).status(HttpStatus.BAD_REQUEST.value()).build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StatusResponse> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(StatusResponse.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND.value()).build());
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<StatusResponse> handleForbidden(ForbiddenException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(StatusResponse.builder().message(ex.getMessage()).status(HttpStatus.FORBIDDEN.value()).build());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<StatusResponse> handleAccessDenied(AccessDeniedException ex) {
        log.warn("Unauthorized exception occurred", ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
             .body(StatusResponse.builder().message("Unauthorized").status(HttpStatus.UNAUTHORIZED.value()).build());
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<StatusResponse> handleServer(ServerException ex) {
        log.error("Oops, service arose internal error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(StatusResponse.builder().message(ex.getMessage()).status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StatusResponse> handleValidation(MethodArgumentNotValidException ex) {
        log.warn("Validation error occurred", ex);
        return ResponseEntity.badRequest()
                .body(StatusResponse.builder().message(ex.getMessage()).status(HttpStatus.BAD_REQUEST.value()).build());
    }
}

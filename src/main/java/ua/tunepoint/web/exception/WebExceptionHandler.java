package ua.tunepoint.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.tunepoint.web.model.StatusResponse;

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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StatusResponse> handleOther(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(StatusResponse.builder().message(INTERNAL_ERROR_MESSAGE).status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build());
    }
}

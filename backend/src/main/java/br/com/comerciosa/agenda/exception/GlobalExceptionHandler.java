package br.com.comerciosa.agenda.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
        @ExceptionHandler(CpfDuplicadoException.class)
        public ResponseEntity<ApiError> handleCpfDuplicado (CpfDuplicadoException e){
            ApiError erro = new ApiError(
                    HttpStatus.CONFLICT,
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
        }

        @ExceptionHandler(RegistroNaoEncontradoException.class)
        public ResponseEntity<ApiError> handleSemRegistro (RegistroNaoEncontradoException e){
            ApiError erro = new ApiError(
                    HttpStatus.NOT_FOUND,
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
        }

        @ExceptionHandler(ConstraintViolationException.class)
        public ResponseEntity<ApiError> handleCampoInvalido (ConstraintViolationException e){
            ApiError erro = new ApiError(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }
}

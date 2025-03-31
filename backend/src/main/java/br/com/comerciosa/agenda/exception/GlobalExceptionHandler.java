package br.com.comerciosa.agenda.exception;

import br.com.comerciosa.agenda.dto.response.ApiResponseDTO;
import jakarta.validation.ConstraintViolationException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
        @ExceptionHandler(CpfDuplicadoException.class)
        public ResponseEntity<ApiResponseDTO<?>> handleCpfDuplicado (CpfDuplicadoException e){

            return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResponseDTO.error(
                    e.getMessage(),
                    HttpStatus.CONFLICT
            ));
        }
        @ExceptionHandler(SemIdException.class)
        public ResponseEntity<ApiResponseDTO<?>> HandleSemId (SemIdException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponseDTO.error(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            ));
        }


        @ExceptionHandler(RegistroNaoEncontradoException.class)
        public ResponseEntity<ApiResponseDTO<?>> handleSemRegistro (RegistroNaoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseDTO.error(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            ));
        }

        @ExceptionHandler(ConstraintViolationException.class)
        public ResponseEntity<ApiResponseDTO<?>> handleCampoInvalido (ConstraintViolationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponseDTO.error(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            ));
        }
}

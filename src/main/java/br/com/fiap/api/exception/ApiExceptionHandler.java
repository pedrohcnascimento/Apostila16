package br.com.fiap.api.exception;

import br.com.fiap.api.dto.ErroResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ErroResponseDto> naoEncontrado(EntidadeNaoEncontradaException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErroResponseDto("Erro 404", e.getMessage()));
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErroResponseDto> erro500(SQLException e){
        return ResponseEntity.internalServerError().body(
                new ErroResponseDto("Procure o administrador do sistema", e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validacoes(MethodArgumentNotValidException e){
        Map<String, String> erros = new HashMap<>();
        //Percorrer todos os erros de validação
        for (FieldError field : e.getFieldErrors()){
            erros.put(field.getField(), field.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(erros);
    }

}

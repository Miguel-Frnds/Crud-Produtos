package br.com.miguel.produtos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProdutoNotFoundException.class)
    public ResponseEntity<ErroResponse> produtoNotFoundHandler(ProdutoNotFoundException exception){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroResponse erro = new ErroResponse(exception.getMessage(), status.value());
        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(ProdutoSemNomeException.class)
    public ResponseEntity<ErroResponse> produtoSemNomeHandler(ProdutoSemNomeException exception){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroResponse erro = new ErroResponse(exception.getMessage(), status.value());
        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(ValorInvalidoException.class)
    public ResponseEntity<ErroResponse> valorInvalidoHandler(ValorInvalidoException exception){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroResponse erro = new ErroResponse(exception.getMessage(), status.value());
        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> ExceptionGenericaHandler(Exception exception){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErroResponse erro = new ErroResponse(exception.getMessage(), status.value());
        return ResponseEntity.status(status).body(erro);
    }
}

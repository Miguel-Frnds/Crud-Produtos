package br.com.miguel.produtos.exception;

public class ValorInvalidoException extends RuntimeException {
    public ValorInvalidoException(String parametro) {
        super("Valor de " + parametro + " não pode ser negativo ou nulo.");
    }
}

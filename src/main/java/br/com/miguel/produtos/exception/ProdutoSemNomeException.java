package br.com.miguel.produtos.exception;

public class ProdutoSemNomeException extends RuntimeException{
    public ProdutoSemNomeException(){
        super("Nome do produto é obrigatório.");
    }
}

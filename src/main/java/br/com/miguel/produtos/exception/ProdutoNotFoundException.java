package br.com.miguel.produtos.exception;

public class ProdutoNotFoundException extends RuntimeException {
    public ProdutoNotFoundException(String nome){
        super("Produto não encontrado com nome: " + nome);
    }

    public ProdutoNotFoundException(Long id){
        super("Produto não encontrado com id: " + id);
    }
}

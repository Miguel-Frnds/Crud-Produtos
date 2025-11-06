package br.com.miguel.produtos.exception;

import java.util.UUID;

public class ProdutoNotFoundException extends RuntimeException {
    public ProdutoNotFoundException(String nome){
        super("Produto não encontrado com nome: " + nome);
    }

    public ProdutoNotFoundException(UUID id){
        super("Produto não encontrado com id: " + id);
    }
}

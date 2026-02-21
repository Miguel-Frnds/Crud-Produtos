package br.com.miguel.produtos.exception;

// Extende RuntimeException para nao exigir a quem chama o metodo utilizar try/catch
public class ProdutoNotFoundException extends RuntimeException {
    public ProdutoNotFoundException(String nome){
        super("Produto não encontrado com nome: " + nome);
    }

    public ProdutoNotFoundException(Long id){
        super("Produto não encontrado com id: " + id);
    }
}

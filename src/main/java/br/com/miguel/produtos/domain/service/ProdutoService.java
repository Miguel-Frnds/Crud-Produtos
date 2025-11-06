package br.com.miguel.produtos.domain.service;

import br.com.miguel.produtos.domain.entity.Produto;
import br.com.miguel.produtos.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    public Produto findById(UUID id){
        return getById(id);
    }

    public Produto save(Produto produto){
        return produtoRepository.save(produto);
    }

    public Produto update(UUID id, Produto produto){
        Produto produtoEncontrado = getById(id);
        produtoEncontrado.setNome(produto.getNome());
        produtoEncontrado.setDescricao(produto.getDescricao());
        produtoEncontrado.setPreco(produto.getPreco());
        produtoEncontrado.setQuantidade(produto.getQuantidade());
        return produtoRepository.save(produtoEncontrado);
    }

    public void delete(UUID id){
        Produto produtoEncontrado = getById(id);
        produtoRepository.delete(produtoEncontrado);
    }

    public Produto getById(UUID id){
        return produtoRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
}

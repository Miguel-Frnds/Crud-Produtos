package br.com.miguel.produtos.api.controller;

import br.com.miguel.produtos.domain.entity.Produto;
import br.com.miguel.produtos.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll(){
        List<Produto> produtos = produtoService.findAll();
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable UUID id){
        Produto produtoEncontrado = produtoService.findById(id);
        return ResponseEntity.ok().body(produtoEncontrado);
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody Produto produto){
        Produto produtoSalvo = produtoService.save(produto);
        return ResponseEntity.ok().body(produtoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable UUID id, @RequestBody Produto produto){
        Produto produtoAtualizado = produtoService.update(id, produto);
        return ResponseEntity.ok().body(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

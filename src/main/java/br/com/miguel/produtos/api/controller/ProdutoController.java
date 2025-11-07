package br.com.miguel.produtos.api.controller;

import br.com.miguel.produtos.domain.entity.Produto;
import br.com.miguel.produtos.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll(){
        List<Produto> produtos = produtoService.findAll();
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id){
        Produto produtoEncontrado = produtoService.findById(id);
        return ResponseEntity.ok().body(produtoEncontrado);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Produto>> findByNome(@RequestParam String nome){
        List<Produto> produtosEncontrados = produtoService.findByNome(nome);
        return ResponseEntity.ok().body(produtosEncontrados);
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody Produto produto){
        Produto produtoSalvo = produtoService.save(produto);
        return ResponseEntity.status(201).body(produtoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto produto){
        Produto produtoAtualizado = produtoService.update(id, produto);
        return ResponseEntity.ok().body(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

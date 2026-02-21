package br.com.miguel.produtos.domain.repository;

import br.com.miguel.produtos.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Camada responsável por acessar o banco de dados usando Spring Data JPA.

// JpaRepository: interface do Spring Data que fornece operações prontas de CRUD,
// paginação e consultas, sem necessidade de escrever SQL ou implementar o repository.
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Consulta derivada: o Spring interpreta o nome do método e gera automaticamente
    // uma query equivalente a "WHERE LOWER(nome) LIKE LOWER('%texto%')".
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}


// Por que Repository é uma interface?
// Porque o Spring Data gera a implementação automaticamente em tempo de execução.
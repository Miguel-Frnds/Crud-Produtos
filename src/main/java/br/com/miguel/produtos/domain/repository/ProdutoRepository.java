package br.com.miguel.produtos.domain.repository;

import br.com.miguel.produtos.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}

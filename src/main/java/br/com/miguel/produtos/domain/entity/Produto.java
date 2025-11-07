package br.com.miguel.produtos.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "produto")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @Column(name = "valor_unitario", nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Integer quantidade;

    public Produto(String nome, String descricao, Double preco, Integer quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }
}

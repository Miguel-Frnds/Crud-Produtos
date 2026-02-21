package br.com.miguel.produtos.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "O nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    private String descricao;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "Valor de preço tem que ser positivo. Não pode ser negativo ou nulo.")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
    @Column(name = "valor_unitario", nullable = false)
    private Double preco;

    @NotNull(message = "A quantidade é obrigatória")
    @Positive(message = "A quantidade deve ser positiva")
    @Min(value = 0, message = "A quantidade não pode ser negativa")
    @Column(nullable = false)
    private Integer quantidade;

    public Produto(String nome, String descricao, Double preco, Integer quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }
}

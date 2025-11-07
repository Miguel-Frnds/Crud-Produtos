package br.com.miguel.produtos.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErroResponse {
    private String mensagem;
    private int status;
    private LocalDateTime registroTempo;

    public ErroResponse(String mensagem) {
        this.mensagem = mensagem;
        this.registroTempo = LocalDateTime.now();
    }

    public ErroResponse(String mensagem, int status) {
        this.mensagem = mensagem;
        this.status = status;
        this.registroTempo = LocalDateTime.now();
    }
}

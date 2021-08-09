package br.com.veloe.mecanicafinanceira.concilialancamento.core.domain;

import br.com.veloe.mecanicafinanceira.concilialancamento.core.enums.Origem;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LancamentoLogMovDTO {

    private Long id;
    private Long idCyber;
    private String idConta;
    private Double valor;
    private Long idRazaoTransacao;
    private String idTransacao;
    private int fatorDebCred;
    private LocalDateTime dataCriacao;
    private Origem origem;
}

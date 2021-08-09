package br.com.veloe.mecanicafinanceira.concilialancamento.core.domain;

import br.com.veloe.mecanicafinanceira.concilialancamento.core.enums.Origem;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.strategy.StrategyConcila;
import lombok.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Builder
public class Lancamento {

    private Long id;
    private Long idCyber;
    private ZonedDateTime dataEnvio;
    private String idConta;
    private String idIdentificador;
    private String idAutorizador;
    private Integer estorno;
    private Integer reversao;
    private String status;
    private Double valor;
    private ZonedDateTime dataRepasse;
    private Long idRazaoTransacao;
    private Long idGrupoTransacao;
    private String idTransacao;
    private String atividade;
    private LocalDateTime dataConciliacao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private int fatorDebCred;
    private Origem origem;
    private StrategyConcila strategyConcila;
}
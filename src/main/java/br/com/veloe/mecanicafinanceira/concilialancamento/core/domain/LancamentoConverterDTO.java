package br.com.veloe.mecanicafinanceira.concilialancamento.core.domain;

import br.com.veloe.mecanicafinanceira.concilialancamento.core.enums.Origem;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.exception.LancamentoNaoEncontradoException;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.strategy.StrategyConcila;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.strategy.StrategyConciliaLancamentoAprovisionado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LancamentoConverterDTO {
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
    private int fatorDebCred;
    private Origem origem;
    private StrategyConcila strategyConcila;
    private Lancamento lancamento;

    public LancamentoConverterDTO(int id, Long idCyber, String idConta, String idTransacao, double valor, String idRazaoTransacao,
                                  long stampDateTime, int fatorDebCred, StrategyConcila strategyConcila) {
    }

    public LancamentoConverterDTO(Long idCoreBank, ZonedDateTime dataEnvio, String toString, String toString1, String toString2,
                                  Integer estorno, Integer reversao, String toString3, Double valor, ZonedDateTime dataRepasse,
                                  Long idRazaoTransacao, Long idGrupoTransacao, String toString4, String toString5, Integer fatorDebCred,
                                  Origem lancamentoAprovisionado, StrategyConcila strategyConciliaLancamentoAprovisionado) {
    }

    public void buscarLancamentoAConciliar() throws LancamentoNaoEncontradoException {
        this.lancamento = this.strategyConcila.buscarLancamentoConciliar(this);
    }
}

package br.com.veloe.mecanicafinanceira.concilialancamento.core.converter;

import br.com.veloe.mecanicafinanceira.LancamentoAprovisionado;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.LancamentoConverterDTO;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.enums.Origem;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.strategy.StrategyConciliaLancamentoAprovisionado;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.strategy.StrategyConciliaLogMov;

import java.time.ZonedDateTime;

public class LancamentoAprovisionadoConverter {

    public static LancamentoConverterDTO converterLancamentoAprovisionadoLancamentoDTO(LancamentoAprovisionado lancamentoAprovisionado) {
        LancamentoConverterDTO lancamentoConverterDTO = LancamentoConverterDTO.builder().idCyber(lancamentoAprovisionado.getIdCoreBank())
                .dataEnvio(ZonedDateTime.parse(lancamentoAprovisionado.getDataEnvio()))
                .idConta(lancamentoAprovisionado.getIdConta().toString())
                .idIdentificador(lancamentoAprovisionado.getIdIdentificador().toString())
                .idAutorizador(lancamentoAprovisionado.getIdAutorizador().toString())
                .estorno(lancamentoAprovisionado.getEstorno())
                .reversao(lancamentoAprovisionado.getReversao())
                .status(lancamentoAprovisionado.getStatus().toString())
                .valor(lancamentoAprovisionado.getValor())
                .dataRepasse(ZonedDateTime.parse(lancamentoAprovisionado.getDataRepasse()))
                .idRazaoTransacao(lancamentoAprovisionado.getIdRazaoTransacao())
                .idGrupoTransacao(lancamentoAprovisionado.getIdGrupoTransacao())
                .idTransacao(lancamentoAprovisionado.getIdTransacao().toString())
                .atividade(lancamentoAprovisionado.getAtividade().toString())
                .fatorDebCred(lancamentoAprovisionado.getFatorDebCred())
                .origem(Origem.LANCAMENTO_APROVISIONADO)
                .strategyConcila(new StrategyConciliaLogMov()).build();

                return lancamentoConverterDTO;

    }
}

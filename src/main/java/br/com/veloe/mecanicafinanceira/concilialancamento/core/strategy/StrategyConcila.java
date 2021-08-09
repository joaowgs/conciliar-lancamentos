package br.com.veloe.mecanicafinanceira.concilialancamento.core.strategy;

import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.Lancamento;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.LancamentoConverterDTO;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.exception.LancamentoNaoEncontradoException;

public interface StrategyConcila {

    public Lancamento buscarLancamentoConciliar(LancamentoConverterDTO lancamentoConverterDTO) throws LancamentoNaoEncontradoException;
}

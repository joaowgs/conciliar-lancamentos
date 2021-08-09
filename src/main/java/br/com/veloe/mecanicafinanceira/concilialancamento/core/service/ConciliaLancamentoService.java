package br.com.veloe.mecanicafinanceira.concilialancamento.core.service;

import br.com.veloe.mecanicafinanceira.LancamentoAprovisionado;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.LancamentoConverterDTO;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.MfLogmov;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.exception.LancamentoDadosDivergentesException;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.exception.LancamentoNaoEncontradoException;

public interface ConciliaLancamentoService {

    void concilia(LancamentoConverterDTO lancamento) throws LancamentoNaoEncontradoException, LancamentoDadosDivergentesException;

}

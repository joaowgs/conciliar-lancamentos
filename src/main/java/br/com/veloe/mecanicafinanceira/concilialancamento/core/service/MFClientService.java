package br.com.veloe.mecanicafinanceira.concilialancamento.core.service;

import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.Lancamento;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.LancamentoConciliadoDTO;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.exception.LancamentoNaoEncontradoException;

public interface MFClientService {
    boolean atualizaLancamento(String idConta, String idCyber, String idTransacao, LancamentoConciliadoDTO lancamentoConciliadoDTO);

    Lancamento buscarLancamentoAprovisionado(String idConta, String idCyber, String idTransacao) throws LancamentoNaoEncontradoException;
}

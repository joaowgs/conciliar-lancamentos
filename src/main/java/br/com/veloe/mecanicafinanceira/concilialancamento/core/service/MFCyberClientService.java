package br.com.veloe.mecanicafinanceira.concilialancamento.core.service;

import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.Lancamento;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.exception.LancamentoNaoEncontradoException;

public interface MFCyberClientService {

    Lancamento buscarLancamentoLogMov(String idCyber, String idConta, String idTransacao) throws LancamentoNaoEncontradoException;
}

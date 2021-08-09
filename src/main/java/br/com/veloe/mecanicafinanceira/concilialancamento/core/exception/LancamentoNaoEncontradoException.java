package br.com.veloe.mecanicafinanceira.concilialancamento.core.exception;

import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.MfLogmov;

public class LancamentoNaoEncontradoException extends Throwable {

    private final String idConta;
    private final String idCyber;
    private final String idTransacao;

    public LancamentoNaoEncontradoException(String idConta, String idCyber, String idTransacao) {
        this.idConta = idConta;
        this.idCyber = idCyber;
        this.idTransacao = idTransacao;
    }

    @Override
    public String getMessage() {
        StringBuilder message = new StringBuilder();
        message.append("Lançamento não encontrado -> ")
            .append("idConta="+idConta+", ")
            .append("idCyber="+idCyber+", ")
            .append("idTransacao="+idTransacao+", ");
        return message.toString();
    }
}

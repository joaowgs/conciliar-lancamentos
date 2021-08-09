package br.com.veloe.mecanicafinanceira.concilialancamento.core.exception;

import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.Lancamento;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.LancamentoConverterDTO;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.LancamentoDiff;

import java.util.ArrayList;
import java.util.List;

public class LancamentoDadosDivergentesException extends Throwable {

    private final String message;

    public LancamentoDadosDivergentesException(LancamentoConverterDTO lancamentoConverterDTO, Lancamento lancamento) {
        this.message = "Lançamento com dados divergentes -> " + comparaDiferencaEntreLancamentos(lancamentoConverterDTO, lancamento);
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    private List<LancamentoDiff> comparaDiferencaEntreLancamentos(LancamentoConverterDTO lancamentoConverterDTO, Lancamento lancamento) {
        List<LancamentoDiff> lancamentoLogmovDiffs = new ArrayList<>();
        if (!lancamento.getIdTransacao().equals(lancamentoConverterDTO.getIdTransacao())) {
            lancamentoLogmovDiffs.add(new LancamentoDiff("IdTransacao", lancamentoConverterDTO.getOrigem(), lancamentoConverterDTO.getIdTransacao(), lancamento.getOrigem(), lancamento.getIdTransacao()));
        }
        if (!lancamento.getIdCyber().toString().equals(lancamentoConverterDTO.getIdCyber())) {
            lancamentoLogmovDiffs.add(new LancamentoDiff("IdCyber", lancamentoConverterDTO.getOrigem(), lancamentoConverterDTO.getIdCyber().toString(), lancamento.getOrigem(), lancamento.getIdCyber().toString()));
        }
        if (!lancamento.getIdRazaoTransacao().toString().equals(lancamentoConverterDTO.getIdRazaoTransacao())) {
            lancamentoLogmovDiffs.add(new LancamentoDiff("IdRazaoTransacao", lancamentoConverterDTO.getOrigem(), lancamentoConverterDTO.getIdRazaoTransacao().toString(), lancamento.getOrigem(), lancamento.getIdRazaoTransacao().toString()));
        }
        if (!lancamento.getValor().toString().equals(Double.toString(lancamentoConverterDTO.getValor()))) {
            lancamentoLogmovDiffs.add(new LancamentoDiff("Valor", lancamentoConverterDTO.getOrigem(), Double.toString(lancamentoConverterDTO.getValor()), lancamento.getOrigem(), lancamento.getValor().toString()));
        }
        if (lancamento.getFatorDebCred() != lancamentoConverterDTO.getFatorDebCred() ) {
            lancamentoLogmovDiffs.add(new LancamentoDiff("FatorDebCred", lancamentoConverterDTO.getOrigem(), Integer.toString(lancamentoConverterDTO.getFatorDebCred()), lancamento.getOrigem(), Integer.toString(lancamento.getFatorDebCred())));
        }
        return lancamentoLogmovDiffs;
    }

}

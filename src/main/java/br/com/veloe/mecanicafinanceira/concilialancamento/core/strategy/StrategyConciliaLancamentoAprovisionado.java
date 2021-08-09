package br.com.veloe.mecanicafinanceira.concilialancamento.core.strategy;

import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.Lancamento;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.LancamentoConverterDTO;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.exception.LancamentoNaoEncontradoException;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.service.MFClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class StrategyConciliaLancamentoAprovisionado implements StrategyConcila {

    @Autowired
    private MFClientService mfClientService;

    @Override
    public Lancamento buscarLancamentoConciliar(LancamentoConverterDTO lancamentoConverterDTO) throws LancamentoNaoEncontradoException {
        return mfClientService.buscarLancamentoAprovisionado(lancamentoConverterDTO.getIdConta(),
                lancamentoConverterDTO.getIdCyber().toString(), lancamentoConverterDTO.getIdTransacao());
    }
}

package br.com.veloe.mecanicafinanceira.concilialancamento.core.strategy;

import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.Lancamento;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.LancamentoConverterDTO;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.exception.LancamentoNaoEncontradoException;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.service.MFCyberClientService;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.service.impl.MFClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StrategyConciliaLogMov implements StrategyConcila {

    @Autowired
    private MFCyberClientService mfCyberClientService;

    @Override
    public Lancamento buscarLancamentoConciliar(LancamentoConverterDTO lancamentoConverterDTO) throws LancamentoNaoEncontradoException {
        return mfCyberClientService.buscarLancamentoLogMov(lancamentoConverterDTO.getIdCyber().toString(),
                lancamentoConverterDTO.getIdConta(), lancamentoConverterDTO.getIdTransacao());
    }
}

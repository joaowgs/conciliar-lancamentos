package br.com.veloe.mecanicafinanceira.concilialancamento.core.service.impl;

import static br.com.veloe.mecanicafinanceira.concilialancamento.core.enums.Origem.LANCAMENTO_APROVISIONADO;
import static br.com.veloe.mecanicafinanceira.concilialancamento.core.enums.Origem.LOGMOV;

import java.time.Clock;
import java.time.LocalDateTime;

import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.*;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.enums.Origem;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import br.com.veloe.mecanicafinanceira.LancamentoAprovisionado;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.exception.LancamentoDadosDivergentesException;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.exception.LancamentoNaoEncontradoException;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.service.ConciliaLancamentoService;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.service.MFClientService;
import lombok.extern.slf4j.Slf4j;

@Service
@Configurable
@Slf4j
public class ConciliaLancamentoServiceImpl implements ConciliaLancamentoService {

    private final MFClientService mfClientService;

    private Clock clock;

    public ConciliaLancamentoServiceImpl(MFClientService mfClientService) {
        this.mfClientService = mfClientService;
        this.clock = Clock.systemDefaultZone();
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void concilia(LancamentoConverterDTO lancamentoConverterDTO) throws LancamentoNaoEncontradoException, LancamentoDadosDivergentesException {
        lancamentoConverterDTO.buscarLancamentoAConciliar();
        Lancamento lancamento = lancamentoConverterDTO.getLancamento();

        if (lancamento != null) {
            if (estaConciliado(lancamentoConverterDTO, lancamentoConverterDTO.getLancamento())) {
                mfClientService.atualizaLancamento(lancamentoConverterDTO.getIdConta(), Long.toString(lancamentoConverterDTO.getIdCyber()),
                        lancamentoConverterDTO.getIdTransacao(), new LancamentoConciliadoDTO(LocalDateTime.now(clock)));
                return;
            }
            throw new LancamentoDadosDivergentesException(lancamentoConverterDTO, lancamento);
        }
    }

    public boolean estaConciliado(LancamentoConverterDTO lancamentoDTO, Lancamento lancamento) {
        return lancamento.getIdTransacao().equals(lancamentoDTO.getIdTransacao()) &&
                lancamento.getIdCyber().toString().equals(lancamentoDTO.getIdCyber()) &&
                lancamento.getIdRazaoTransacao().toString().equals(lancamentoDTO.getIdRazaoTransacao()) &&
                lancamento.getValor().toString().equals(Double.toString(lancamentoDTO.getValor()))&&
                lancamento.getFatorDebCred() == lancamentoDTO.getFatorDebCred();
    }

}

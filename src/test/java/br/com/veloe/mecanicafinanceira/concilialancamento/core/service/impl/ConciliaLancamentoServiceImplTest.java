package br.com.veloe.mecanicafinanceira.concilialancamento.core.service.impl;

import br.com.veloe.mecanicafinanceira.LancamentoAprovisionado;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.Lancamento;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.LancamentoConciliadoDTO;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.MfLogmov;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.exception.LancamentoDadosDivergentesException;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.exception.LancamentoNaoEncontradoException;
import br.com.veloe.mecanicafinanceira.concilialancamento.creator.LancamentoCreator;
import br.com.veloe.mecanicafinanceira.concilialancamento.creator.MfLogmovCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConciliaLancamentoServiceImplTest {

    @Mock
    private MFClientServiceImpl mfClientService;

    @Mock
    private MFCyberClientServiceImpl mfCyberClientService;

    @InjectMocks
    private ConciliaLancamentoServiceImpl conciliaLancamentoService;

    private Lancamento lancamento;
    private Lancamento lancamentoDadosDivergentes;
    private MfLogmov mfLogmov;
    //private LancamentoDTO lancamentoDTO;
    private LancamentoAprovisionado lancamentoAprovisionado;
    private LancamentoAprovisionado lancamentoAprovisionadoDivergente;

    private Clock clock;

//    @Test
//    @DisplayName("Quando lançamento encontrado, deve atualizar lançamento")
//    void quandoLancamentoEncontrado_deveChamarAtualizaLancamento() throws LancamentoNaoEncontradoException, LancamentoDadosDivergentesException {
//        String idConta = Integer.toString(mfLogmov.getAccountId());
//        String idCyber = Integer.toString(mfLogmov.getCyberId());
//        String idTransacao = mfLogmov.getFastId();
//        conciliaLancamentoService.setClock(clock);
//        when(mfClientService.atualizaLancamento(any(), any(), any(), any())).thenReturn(true);
//        when(mfClientService.buscarLancamentoAprovisionado(any(), any(), any())).thenReturn(lancamento);
//        conciliaLancamentoService.concilia(mfLogmov);
//        verify(mfClientService).atualizaLancamento(eq(idConta), eq(idCyber), eq(idTransacao), any(LancamentoConciliadoDTO.class));
//    }
//
//    @Test
//    @DisplayName("Quando lançamento com dados divergentes, deve lançar LancamentoDadosDivergentesException")
//    void quandoLancamentoComDadosDivergentes_deveLancarLancamentoDadosDivergentesException() throws LancamentoNaoEncontradoException {
//        when(mfClientService.buscarLancamentoAprovisionado(any(), any(), any())).thenReturn(lancamento);
//
//        assertThrows(LancamentoDadosDivergentesException.class, () -> conciliaLancamentoService.concilia(new MfLogmov()));
//    }
//
//    @Test
//    @DisplayName("Quando lançamento encontrado, deve atualizar lançamento via mecanica")
//    void quandoLancamentoEncontrado_deveChamarAtualizaLancamentoOrigemMecanicaFinanceira() throws LancamentoNaoEncontradoException, LancamentoDadosDivergentesException {
//        String idConta = lancamentoAprovisionado.getIdConta().toString();
//        String idCyber = lancamentoAprovisionado.getIdCoreBank().toString();
//        String idTransacao = lancamentoAprovisionado.getIdTransacao().toString();
//        conciliaLancamentoService.setClock(clock);
//        when(mfClientService.atualizaLancamento(any(), any(), any(), any())).thenReturn(true);
//        when(mfCyberClientService.buscarLancamentoLogMov(any(), any(), any())).thenReturn(lancamento);
//        conciliaLancamentoService.concilia(lancamentoAprovisionado);
//        verify(mfClientService).atualizaLancamento(eq(idConta), eq(idCyber), eq(idTransacao), any(LancamentoConciliadoDTO.class));
//    }
//
//    @Test
//    @DisplayName("Quando lançamento com dados divergentes via mecanica financeira, deve lançar LancamentoDadosDivergentesException")
//    void quandoLancamentoComDadosDivergentes_deveLancarLancamentoDadosDivergentesExceptionOrigemMecanicaFinanceira() throws LancamentoNaoEncontradoException {
//        when(mfCyberClientService.buscarLancamentoLogMov(any(), any(), any())).thenReturn(lancamento);
//
//        assertThrows(LancamentoDadosDivergentesException.class, () -> conciliaLancamentoService.concilia(lancamentoAprovisionadoDivergente));
//    }
//
//    @Test
//    @DisplayName("Quando dados iguais, deve retornar true")
//    void quandoDadosIguais_deveRetornarTrue() {
//        boolean conciliado = conciliaLancamentoService.estaConciliado(lancamentoDTO, lancamento);
//
//        assertTrue(conciliado);
//    }
//
//    @Test
//    @DisplayName("Quando dados divergentes, deve retornar false")
//    void quandoDadosDivergentes_deveRetornarFalse() {
//        boolean conciliado = conciliaLancamentoService.estaConciliado(lancamentoDTO, lancamentoDadosDivergentes);
//
//        assertFalse(conciliado);
//    }
//
//    @BeforeEach
//    public void setup() {
//        Instant instant = Instant.parse("2018-08-19T16:02:42.00Z");
//        ZoneId zoneId = ZoneId.of("Asia/Calcutta");
//
//        this.clock = Clock.fixed(instant, zoneId);
//        this.mfLogmov = MfLogmovCreator.createValid();
//        this.lancamento = LancamentoCreator.createValid();
//        this.lancamentoDTO = LancamentoCreator.lancamentoDTOobjetoPersistencia();
//
//        this.lancamentoDadosDivergentes = LancamentoCreator.lancamentoTodosDadosDivergentes();
//        this.lancamentoAprovisionado = LancamentoCreator.lancamentoAprovisionado();
//        this.lancamentoAprovisionadoDivergente = LancamentoCreator.lancamentoAprovisionadoDivergente();
//    }


}
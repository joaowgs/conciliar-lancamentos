package br.com.veloe.mecanicafinanceira.concilialancamento.core.service.impl;

import br.com.veloe.mecanicafinanceira.LancamentoAprovisionado;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.Lancamento;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.LancamentoConciliadoDTO;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.exception.LancamentoNaoEncontradoException;
import br.com.veloe.mecanicafinanceira.concilialancamento.creator.LancamentoCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Teste de MFClientServiceImpl")
class MFCyberClientServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private MFCyberClientServiceImpl mfCyberClientService;

    private LancamentoAprovisionado lancamentoAprovisionado;
    private LancamentoConciliadoDTO lancamentoConciliadoDTO;
    private Lancamento lancamento;

    @Value("${mf-api-cyber.url-base}")
    private String MF_API_URL_BASE;

    private final String MF_API_LANCAMENTO = "/api/v1/lancamento-aprovisionado";
    private final String MF_API_LANCAMENTO_GET_PARAMS = "?idCyber={idCyber}&idConta={idConta}&idTransacao={idTransacao}";

    @Test
    @DisplayName("Quando buscar com sucesso, deve retornar lançamento da LogMov")
    void quandoBuscarComSucesso_deveRetornarLancamentoLogMov() throws LancamentoNaoEncontradoException {
        String idConta = lancamentoAprovisionado.getIdConta().toString();
        String idCyber = lancamentoAprovisionado.getIdCoreBank().toString();
        String idTransacao = lancamentoAprovisionado.getIdTransacao().toString();

        ResponseEntity<Lancamento> responseEntity = ResponseEntity.ok(lancamento);

        when(restTemplate.getForEntity(
                MF_API_URL_BASE + MF_API_LANCAMENTO + MF_API_LANCAMENTO_GET_PARAMS,
                Lancamento.class,
                idCyber, idConta, idTransacao))
                .thenReturn(responseEntity);

        Lancamento lancamento = mfCyberClientService.buscarLancamentoLogMov(idCyber, idConta, idTransacao);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(lancamento, responseEntity.getBody());
    }

    @Test
    @DisplayName("Quando não encontrar, deve retornar LancamentoNaoEncontradoException")
    void quandoNaoEncontrar_deveLancarLancamentoNaoEncontradoException() throws LancamentoNaoEncontradoException {
        String idConta = lancamentoAprovisionado.getIdConta().toString();
        String idCyber = lancamentoAprovisionado.getIdCoreBank().toString();
        String idTransacao = lancamentoAprovisionado.getIdTransacao().toString();

        RestClientResponseException restClientResponseException = new RestClientResponseException("", 404, "", null, null, null);

        doThrow(restClientResponseException).when(restTemplate).getForEntity(MF_API_URL_BASE + MF_API_LANCAMENTO + MF_API_LANCAMENTO_GET_PARAMS,
                Lancamento.class,
                idCyber, idConta, idTransacao);

        Assertions.assertThatThrownBy(() -> mfCyberClientService.buscarLancamentoLogMov(idCyber, idConta, idTransacao)).isInstanceOf(LancamentoNaoEncontradoException.class);
    }

    @BeforeEach
    public void setup() {
        Instant instant = Instant.parse("2018-08-19T16:02:42.00Z");
        ZoneId zoneId = ZoneId.of("Asia/Calcutta");

        this.lancamentoAprovisionado = LancamentoCreator.lancamentoAprovisionado();
        this.lancamento = LancamentoCreator.createValid();
        this.lancamentoConciliadoDTO = LancamentoCreator.createLancamentoConciliadoDTOValid(Clock.fixed(instant, zoneId));

    }

}
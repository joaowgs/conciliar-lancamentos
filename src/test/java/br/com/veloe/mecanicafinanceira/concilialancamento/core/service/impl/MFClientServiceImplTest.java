package br.com.veloe.mecanicafinanceira.concilialancamento.core.service.impl;

import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.Lancamento;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.LancamentoConciliadoDTO;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.MfLogmov;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.exception.LancamentoNaoEncontradoException;
import br.com.veloe.mecanicafinanceira.concilialancamento.creator.LancamentoCreator;
import br.com.veloe.mecanicafinanceira.concilialancamento.creator.MfLogmovCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Teste de MFClientServiceImpl")
class MFClientServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private MFClientServiceImpl mfClientService;

    private MfLogmov mfLogmov;
    private LancamentoConciliadoDTO lancamentoConciliadoDTO;
    private Lancamento lancamento;

    @Value("${mf-api.url-base}")
    private String MF_API_URL_BASE;

    private final String MF_API_LANCAMENTO = "/api/v1/lancamento";
    private final String MF_API_LANCAMENTO_GET_PARAMS = "?idConta={idConta}&idCyber={idCyber}&idTransacao={idTransacao}";

    @Test
    @DisplayName("Quando atualizar lançamento conciliado, deve retornar status 200 e conciliação true")
    void quandoAtualizarLancamentoConciliadoSucesso_deveRetornarTrue() {
        String idConta = Integer.toString(mfLogmov.getAccountId());
        String idCyber = Integer.toString(mfLogmov.getCyberId());
        String idTransacao = mfLogmov.getFastId();

        HttpEntity<LancamentoConciliadoDTO> requestEntity = new HttpEntity<>(lancamentoConciliadoDTO, null);
        ResponseEntity<Void> responseEntity = ResponseEntity.ok().build();

        when(restTemplate.exchange(MF_API_URL_BASE + MF_API_LANCAMENTO + MF_API_LANCAMENTO_GET_PARAMS,
                HttpMethod.PUT,
                requestEntity,
                Void.class,
                idConta, idCyber, idTransacao))
                .thenReturn(responseEntity);

        boolean conciliado = mfClientService.atualizaLancamento(idConta, idCyber, idTransacao, lancamentoConciliadoDTO);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertTrue(conciliado);
    }

    @Test
    @DisplayName("Quando buscar com sucesso, deve retornar lançamento aprovisionado")
    void quandoBuscarComSucesso_deveRetornarLancamentoAprovisionado() throws LancamentoNaoEncontradoException {
        String idConta = Integer.toString(mfLogmov.getAccountId());
        String idCyber = Integer.toString(mfLogmov.getCyberId());
        String idTransacao = mfLogmov.getFastId();

        ResponseEntity<Lancamento> responseEntity = ResponseEntity.ok(lancamento);

        when(restTemplate.getForEntity(
                MF_API_URL_BASE + MF_API_LANCAMENTO + MF_API_LANCAMENTO_GET_PARAMS,
                Lancamento.class,
                idConta, idCyber, idTransacao))
                .thenReturn(responseEntity);

        Lancamento lancamento = mfClientService.buscarLancamentoAprovisionado(idConta, idCyber, idTransacao);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(lancamento, responseEntity.getBody());
    }

    @Test
    @DisplayName("Quando não encontrar, deve retornar LancamentoNaoEncontradoException")
    void quandoNaoEncontrar_deveLancarLancamentoNaoEncontradoException() throws LancamentoNaoEncontradoException {
        String idConta = Integer.toString(mfLogmov.getAccountId());
        String idCyber = Integer.toString(mfLogmov.getCyberId());
        String idTransacao = mfLogmov.getFastId();

        RestClientResponseException restClientResponseException = new RestClientResponseException("", 404, "", null, null, null);

        doThrow(restClientResponseException).when(restTemplate).getForEntity(MF_API_URL_BASE + MF_API_LANCAMENTO + MF_API_LANCAMENTO_GET_PARAMS,
                Lancamento.class,
                idConta, idCyber, idTransacao);

        Assertions.assertThatThrownBy(() -> mfClientService.buscarLancamentoAprovisionado(idConta, idCyber, idTransacao)).isInstanceOf(LancamentoNaoEncontradoException.class);
    }

    @BeforeEach
    public void setup() {
        Instant instant = Instant.parse("2018-08-19T16:02:42.00Z");
        ZoneId zoneId = ZoneId.of("Asia/Calcutta");

        this.mfLogmov = MfLogmovCreator.createValid();
        this.lancamento = LancamentoCreator.createValid();
        this.lancamentoConciliadoDTO = LancamentoCreator.createLancamentoConciliadoDTOValid(Clock.fixed(instant, zoneId));

    }

}
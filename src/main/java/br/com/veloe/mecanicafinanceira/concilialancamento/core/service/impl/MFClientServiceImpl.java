package br.com.veloe.mecanicafinanceira.concilialancamento.core.service.impl;

import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.Lancamento;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.LancamentoConciliadoDTO;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.exception.LancamentoNaoEncontradoException;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.service.MFClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class MFClientServiceImpl implements MFClientService {

    @Value("${mf-api.url-base}")
    private String MF_API_URL_BASE;

    private final String MF_API_LANCAMENTO = "/api/v1/lancamento";
    private final String MF_API_LANCAMENTO_GET_PARAMS = "?idConta={idConta}&idCyber={idCyber}&idTransacao={idTransacao}";

    private final RestTemplate restTemplate;

    public MFClientServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean atualizaLancamento(String idConta, String idCyber, String idTransacao, LancamentoConciliadoDTO lancamentoConciliadoDTO) {
        log.info("Conciliando lançamento.");
        HttpEntity<LancamentoConciliadoDTO> requestEntity = new HttpEntity<>(lancamentoConciliadoDTO, null);
        try {
            restTemplate.exchange(MF_API_URL_BASE + MF_API_LANCAMENTO + MF_API_LANCAMENTO_GET_PARAMS,
                    HttpMethod.PUT,
                    requestEntity,
                    Void.class,
                    idConta, idCyber, idTransacao);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Lancamento buscarLancamentoAprovisionado(String idConta, String idCyber, String idTransacao) throws LancamentoNaoEncontradoException {
        try {
            log.info("Buscando por lançamento" );
            ResponseEntity<Lancamento> lancamentoResponseEntity = restTemplate.getForEntity(
                    MF_API_URL_BASE + MF_API_LANCAMENTO + MF_API_LANCAMENTO_GET_PARAMS,
                    Lancamento.class,
                    idConta, idCyber, idTransacao);
            return lancamentoResponseEntity.getBody();
        } catch (RestClientResponseException e) {
            if (e.getRawStatusCode() == HttpStatus.NOT_FOUND.value()) {
                throw new LancamentoNaoEncontradoException(idConta, idCyber, idTransacao);
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

}

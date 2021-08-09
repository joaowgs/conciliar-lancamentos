package br.com.veloe.mecanicafinanceira.concilialancamento.core.service.impl;

import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.Lancamento;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.exception.LancamentoNaoEncontradoException;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.service.MFCyberClientService;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class MFCyberClientServiceImpl implements MFCyberClientService {

    @Value("${mf-api.url-base}")
    private String MF_API_URL_BASE;

    private final String MF_API_LANCAMENTO_LOG_MOV = "/api/v1/lancamento-aprovisionado";
    private final String MF_API_LANCAMENTO_GET_PARAMS = "?idCyber={idCyber}&idConta={idConta}&idTransacao={idTransacao}";

    private final RestTemplate restTemplate;

    public MFCyberClientServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Lancamento buscarLancamentoLogMov(String idCyber, String idConta, String idTransacao) throws LancamentoNaoEncontradoException {
        try {
            log.info("Buscando por lançamento" );
            ResponseEntity<Lancamento> lancamentoResponseEntity = restTemplate.getForEntity(
                    MF_API_URL_BASE + MF_API_LANCAMENTO_LOG_MOV + MF_API_LANCAMENTO_GET_PARAMS,
                    Lancamento.class,
                    idCyber, idConta, idTransacao);
            return lancamentoResponseEntity.getBody();
        } catch (RestClientResponseException e) {
            if (e.getRawStatusCode() == NOT_FOUND.value()) {
                throw new LancamentoNaoEncontradoException(idConta, idCyber, idTransacao);
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }
}

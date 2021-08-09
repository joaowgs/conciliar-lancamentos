package br.com.veloe.mecanicafinanceira.concilialancamento.consumer;

import br.com.veloe.mecanicafinanceira.LancamentoAprovisionado;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.converter.LancamentoAprovisionadoConverter;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.LancamentoConverterDTO;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.exception.LancamentoDadosDivergentesException;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.exception.LancamentoNaoEncontradoException;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.service.ConciliaLancamentoService;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.SchemaCompatibility;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Configuration
@EnableKafka
@Slf4j
public class LancamentoConsumer {

    private final ConciliaLancamentoService conciliaLancamentoService;

    public LancamentoConsumer(ConciliaLancamentoService conciliaLancamentoService) {
        this.conciliaLancamentoService = conciliaLancamentoService;
    }
    @Bean
    public Consumer<KStream<String, LancamentoAprovisionado>> consumidorLancamentoAprovisionado() {
        return lancamentoAprovisionadoStream -> lancamentoAprovisionadoStream
                .peek((k, v) -> log.info("Processando lançamento - idCyber={}, idTransacao={}, idConta={}", v.getIdCoreBank(), v.getIdTransacao(), v.getIdConta()))
                .foreach((k, v) -> {
                    try {
                        LancamentoConverterDTO lancamentoConverterDTO = LancamentoAprovisionadoConverter.converterLancamentoAprovisionadoLancamentoDTO(v);
                        conciliaLancamentoService.concilia(lancamentoConverterDTO);
                    } catch (LancamentoNaoEncontradoException e) {
                        log.error(e.getMessage());
                    } catch (LancamentoDadosDivergentesException e) {
                        log.error(e.getMessage());
                    }
                    catch (Exception e) {
                        log.error(e.getMessage());
                    }
                });
    }

}

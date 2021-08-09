package br.com.veloe.mecanicafinanceira.concilialancamento.consumer;

import br.com.veloe.mecanicafinanceira.concilialancamento.core.converter.MfLogMovConverter;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.LancamentoConverterDTO;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.MfLogmov;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.exception.LancamentoDadosDivergentesException;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.exception.LancamentoNaoEncontradoException;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.service.ConciliaLancamentoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.function.Consumer;

@Configuration
@EnableKafka
@Slf4j
public class MFLogMovConsumer {

    private final ConciliaLancamentoService conciliaLancamentoService;

    public MFLogMovConsumer(ConciliaLancamentoService conciliaLancamentoService) {
        this.conciliaLancamentoService = conciliaLancamentoService;
    }

    @Bean
    public Consumer<KStream<String, MfLogmov>> consumidorMfLogmov() {
        return mfLogmovStream -> mfLogmovStream
                .peek((k, v) -> log.info("Consumindo mensagem -> {}", v))
                .foreach((k, v) -> {
                    try {
                        LancamentoConverterDTO lancamentoConverterDTO = MfLogMovConverter.converterLancamentoMfLogMovLancamentoDTO(v);
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
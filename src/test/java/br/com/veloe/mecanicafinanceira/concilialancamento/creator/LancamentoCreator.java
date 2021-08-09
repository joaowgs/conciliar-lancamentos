package br.com.veloe.mecanicafinanceira.concilialancamento.creator;

import br.com.veloe.mecanicafinanceira.LancamentoAprovisionado;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.Lancamento;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.LancamentoConciliadoDTO;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.enums.Origem;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class LancamentoCreator {

    private Clock clock;

    public static LancamentoConciliadoDTO createLancamentoConciliadoDTOValid(Clock clock) {
        return LancamentoConciliadoDTO.builder().dataConciliacao(LocalDateTime.now(clock)).build();
    }

    public static Lancamento createValid() {
        return Lancamento.builder()
                .id(1L)
                .idCyber(2L)
                .dataEnvio(ZonedDateTime.parse("2021-03-10T21:03:33.361Z"))
                .idConta("123456")
                .idIdentificador("5bf6b93a-7648-4545-9689-1ba32fc04bfd")
                .idAutorizador("097b62f0-09cf-4cbe-a224-eef388ff8242")
                .estorno(0)
                .reversao(0)
                .status("COMPENSADO")
                .valor(10.0)
                .dataRepasse(ZonedDateTime.parse("2021-03-10T21:03:33.361Z"))
                .idRazaoTransacao(3L)
                .idGrupoTransacao(4L)
                .idTransacao("5bf6b93a-7648-4545-9689-1ba32fc04bfd")
                .atividade("PASSAGEM_DENTRO_SLA")
                .dataConciliacao(LocalDateTime.parse("2021-07-21T20:48:37.431850"))
                .fatorDebCred(1)
                .build();
    }

    public static Lancamento lancamentoTodosDadosDivergentes() {
        return Lancamento.builder()
                .idCyber(999L)
                .dataEnvio(ZonedDateTime.parse("2021-03-10T21:03:33.361Z"))
                .idConta("98765")
                .idIdentificador("123456-7648-4545-9689-1ba32fc04bfd")
                .idAutorizador("123456-09cf-4cbe-a224-eef388ff8242")
                .estorno(1)
                .reversao(1)
                .status("COMPENSADO")
                .valor(15.0)
                .dataRepasse(ZonedDateTime.parse("2021-03-10T21:03:33.361Z"))
                .idRazaoTransacao(7L)
                .idGrupoTransacao(4L)
                .idTransacao("123456-7648-4545-9689-1ba32fc04bfd")
                .atividade("PASSAGEM_DENTRO_SLA")
                .build();
    }

    public static LancamentoAprovisionado lancamentoAprovisionado() {
        return LancamentoAprovisionado.newBuilder()
                .setAtividade("PASSAGEM_DENTRO_SLA")
                .setDataEnvio("2021-03-10T21:03:33.361Z")
                .setDataRepasse("2021-03-10T21:03:33.361Z")
                .setEstorno(0)
                .setIdAutorizador("097b62f0-09cf-4cbe-a224-eef388ff8242")
                .setIdConta("123456")
                .setIdCoreBank(2L)
                .setIdGrupoTransacao(4L)
                .setIdIdentificador("5bf6b93a-7648-4545-9689-1ba32fc04bfd")
                .setIdRazaoTransacao(3L)
                .setIdTransacao("5bf6b93a-7648-4545-9689-1ba32fc04bfd")
                .setReversao(0)
                .setStatus("COMPENSADO")
                .setValor(10.0)
                .setTipoConta("EC")
                .setFatorDebCred(1)
                .build();
    }

    public static LancamentoAprovisionado lancamentoAprovisionadoDivergente() {
        return LancamentoAprovisionado.newBuilder()
                .setAtividade("PASSAGEM_DENTRO_SLA")
                .setDataEnvio("2021-03-10T21:03:33.361Z")
                .setDataRepasse("2021-03-10T21:03:33.361Z")
                .setEstorno(0)
                .setIdAutorizador("097b62f0-09cf-4cbe-a224-eef388ff8242")
                .setIdConta("123456")
                .setIdCoreBank(1L)
                .setIdGrupoTransacao(4L)
                .setIdIdentificador("5bf6b93a-7648-4545-9689-1ba32fc04bfd")
                .setIdRazaoTransacao(1L)
                .setIdTransacao("5bf6b93a-7648-4545-9689-1ba32fc04bfd")
                .setReversao(0)
                .setStatus("COMPENSADO")
                .setValor(16.0)
                .setTipoConta("EC")
                .setFatorDebCred(1)
                .build();
    }

   /* public static LancamentoDTO lancamentoDTOobjetoPersistencia() {
        return new LancamentoDTO("5bf6b93a-7648-4545-9689-1ba32fc04bfd",
                "2", "3", 10.0, 1, Origem.LOGMOV);
    }*/

}

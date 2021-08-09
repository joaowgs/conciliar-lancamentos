package br.com.veloe.mecanicafinanceira.concilialancamento.creator;

import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.MfLogmov;

import java.util.Date;

public class MfLogmovCreator {

    public static MfLogmov createValid() {
        return MfLogmov.builder()
                .id(1)
                .accountId(123456)
                .amount(10)
                .cyberId(2)
                .debcred(1)
                .fastId("5bf6b93a-7648-4545-9689-1ba32fc04bfd")
                .stampDateTime(new Date().getTime())
                .transactionReason(3)
                .build();
    }


}

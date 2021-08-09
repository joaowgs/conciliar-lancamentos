package br.com.veloe.mecanicafinanceira.concilialancamento.core.converter;

import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.LancamentoConverterDTO;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.domain.MfLogmov;
import br.com.veloe.mecanicafinanceira.concilialancamento.core.strategy.StrategyConciliaLogMov;

public class MfLogMovConverter {
    public static LancamentoConverterDTO converterLancamentoMfLogMovLancamentoDTO(MfLogmov mfLogmov) {
        return new LancamentoConverterDTO(mfLogmov.getId(), Long.valueOf(mfLogmov.getCyberId()), String.valueOf(mfLogmov.getAccountId()),
                mfLogmov.getFastId(),mfLogmov.getAmount(), String.valueOf(mfLogmov.getTransactionReason()), mfLogmov.getStampDateTime(),
                mfLogmov.getDebcred(), new StrategyConciliaLogMov());
    }
}

package br.com.veloe.mecanicafinanceira.concilialancamento.core.domain;

import br.com.veloe.mecanicafinanceira.concilialancamento.core.enums.Origem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LancamentoDiff {

    private String campo;
    private Origem lancamentoOrigemConsumer;
    private String valorConsumer;
    private Origem lancamentoOrigemApi;
    private String valorApi;

}

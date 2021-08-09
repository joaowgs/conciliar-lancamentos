package br.com.veloe.mecanicafinanceira.concilialancamento.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MfLogmov {

    @JsonProperty("ID")
    private int id;
    @JsonProperty("ACCOUNT_ID")
    private int accountId;
    @JsonProperty("STAMP_DATE_TIME")
    private long stampDateTime;
    @JsonProperty("CYBER_ID")
    private int cyberId;
    @JsonProperty("FAST_ID")
    private String fastId;
    @JsonProperty("TRANSACTION_REASON")
    private int transactionReason;
    @JsonProperty("DEBCRED")
    private int debcred;
    @JsonProperty("AMOUNT")
    private double amount;

}

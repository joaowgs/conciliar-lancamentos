package br.com.veloe.mecanicafinanceira.concilialancamento.core.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LancamentoConciliadoDTO {

    @NotNull(message = "O campo DataConciliacao não pode ser nulo")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dataConciliacao;
}

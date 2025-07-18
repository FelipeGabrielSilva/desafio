package com.agt.desafio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record CriarRegistroViagemDTO(
        @NotBlank(message = "A placa do veículo deve ser informada.")
        @Size(max = 7, message = "A placa do veículo deve ter 7 caracteres.")
        String placa_veiculo,

        @NotNull(message = "O motorista deve ser informado")
        Long id_motorista,

        @NotNull(message = "A data de saída deve ser informada.")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        Date saida,

        @NotBlank(message = "O destino deve ser informado.")
        @Size(max = 150, message = "O destino pode ter no máximo 150 caracteres.")
        String destino,

        String passageiros
) {
}

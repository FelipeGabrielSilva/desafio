package com.agt.desafio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CriarRegistroViagemRetornoDTO(
        @NotBlank(message = "A placa do veículo deve ser informada.")
        @Size(max = 7, message = "A placa deve conter os 7 caracteres.")
        String placa
) {
}

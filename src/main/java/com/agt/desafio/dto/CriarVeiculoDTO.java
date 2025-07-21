package com.agt.desafio.dto;

import com.agt.desafio.enumtype.Localizacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record CriarVeiculoDTO(
        @NotBlank(message = "A placa deve ser informada.") @Size(min = 7, max = 7, message = "A placa deve ter entre 7 caracteres.") String placa,

        @NotBlank(message = "A marca deve ser informada.") @Size(min = 2, max = 20, message = "A marca deve ter entre 2 e 20 caracteres.") String marca,

        @NotBlank(message = "O modelo deve ser informado.") @Size(min = 2, max = 20, message = "O modelo deve ter entre 2 e 20 caracteres.") String modelo
) {
}

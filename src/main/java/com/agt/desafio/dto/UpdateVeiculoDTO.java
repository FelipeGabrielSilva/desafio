package com.agt.desafio.dto;

import com.agt.desafio.enumtype.Localizacao;

public record UpdateVeiculoDTO(
        String placa,
        String marca,
        String modelo,
        Localizacao status
        ) {}

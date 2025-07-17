package com.agt.desafio.dto;

import com.agt.desafio.enumtype.Categoria;

public record UpdateFuncionarioDTO(
        String nome,
        String email,
        String cnh,
        Categoria categoria
        ) {}

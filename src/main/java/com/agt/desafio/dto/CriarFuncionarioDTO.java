package com.agt.desafio.dto;

import com.agt.desafio.enumtype.Categoria;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CriarFuncionarioDTO
        (
        @NotBlank(message = "O nome deve ser informado.")
        String nome,

        @NotBlank(message = "O e-mail deve ser informado.")
        @Email(message = "O formato do e-mail é inválido.")
        String email,

        @NotBlank(message = "A CNH deve ser informada.")
        @Size(max = 11, message = "A CNH deve ter no máximo 11 caracteres.")
        String cnh,

        @NotNull(message = "A categoria deve ser informada.")
        Categoria categoria
        ) {}

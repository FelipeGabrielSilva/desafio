package com.agt.desafio.config;

public record ApiErrorMessage(
        int status,
        String message,
        String path
) {
}

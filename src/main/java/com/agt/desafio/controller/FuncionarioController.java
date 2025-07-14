package com.agt.desafio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class FuncionarioController {
    @GetMapping
    public String teste() {
        return "TALSKFHASLÇFHASFAS";
    }
}

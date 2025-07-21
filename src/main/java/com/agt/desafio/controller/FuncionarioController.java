package com.agt.desafio.controller;

import com.agt.desafio.dto.CriarFuncionarioDTO;
import com.agt.desafio.dto.UpdateFuncionarioDTO;
import com.agt.desafio.entity.Funcionario;
import com.agt.desafio.errors.ResourceBadRequestException;
import com.agt.desafio.errors.ResourceNotFoundException;
import com.agt.desafio.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody CriarFuncionarioDTO dto) throws ResourceBadRequestException {
        funcionarioService.Criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> getAll() throws ResourceNotFoundException {
        return ResponseEntity.ok(funcionarioService.ListarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getOne(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(funcionarioService.ListarUm(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Funcionario> patch(@PathVariable("id") Long id, @RequestBody UpdateFuncionarioDTO dto) throws ResourceNotFoundException {
        Funcionario f = funcionarioService.Atualizar(id, dto);
        return ResponseEntity.ok(f);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(funcionarioService.Deletar(id));
    }
}

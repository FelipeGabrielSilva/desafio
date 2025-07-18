package com.agt.desafio.controller;

import com.agt.desafio.dto.CriarFuncionarioDTO;
import com.agt.desafio.dto.UpdateFuncionarioDTO;
import com.agt.desafio.entity.Funcionario;
import com.agt.desafio.service.FuncionarioService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    FuncionarioController(FuncionarioService f) {
        this.funcionarioService = f;
    }

    @PostMapping
    public ResponseEntity<Funcionario> create(@Valid @RequestBody CriarFuncionarioDTO dto) throws BadRequestException {
        Funcionario func = funcionarioService.Criar(dto);
        return ResponseEntity.ok(func);
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> getAll() throws ObjectNotFoundException {
        return ResponseEntity.ok(funcionarioService.ListarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getOne(@PathVariable("id") Long id) throws ObjectNotFoundException {
        return ResponseEntity.ok(funcionarioService.ListarUm(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Funcionario> patch(@PathVariable("id") Long id, @RequestBody UpdateFuncionarioDTO dto) throws ObjectNotFoundException {
        Funcionario f = funcionarioService.Atualizar(id, dto);
        return ResponseEntity.ok(f);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) throws ObjectNotFoundException {
        return ResponseEntity.ok(funcionarioService.Deletar(id));
    }
}

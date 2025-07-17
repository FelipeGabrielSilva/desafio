package com.agt.desafio.controller;

import com.agt.desafio.dto.CriarVeiculoDTO;
import com.agt.desafio.entity.Veiculo;
import com.agt.desafio.service.VeiculoService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {
    private VeiculoService veiculoService;

    VeiculoController(VeiculoService v) {
        this.veiculoService = v;
    }

    @PostMapping
    public ResponseEntity<Veiculo> create(@Valid @RequestBody CriarVeiculoDTO dto) throws BadRequestException {
        return ResponseEntity.ok(veiculoService.Criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> getAll() {
        return ResponseEntity.ok(veiculoService.ListarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(veiculoService.ListarUm(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patch(@PathVariable("id") Long id, @RequestBody  CriarVeiculoDTO dto) {
        return ResponseEntity.ok(veiculoService.Atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(veiculoService.Deletar(id));
    }
}

package com.agt.desafio.controller;

import com.agt.desafio.dto.CriarVeiculoDTO;
import com.agt.desafio.dto.UpdateVeiculoDTO;
import com.agt.desafio.entity.Veiculo;
import com.agt.desafio.enumtype.Localizacao;
import com.agt.desafio.errors.ResourceBadRequestException;
import com.agt.desafio.errors.ResourceNotFoundException;
import com.agt.desafio.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    @Autowired
    private VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<Veiculo> create(@Valid @RequestBody CriarVeiculoDTO dto) throws ResourceBadRequestException {
        return ResponseEntity.ok(veiculoService.Criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> getAllOrByStatus(@RequestParam(value = "status", required = false) Localizacao status) {
        if (status != null) {
            return ResponseEntity.ok(veiculoService.ListarPorStatus(status));
        }
        return ResponseEntity.ok(veiculoService.ListarTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Veiculo>> getOne(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(veiculoService.ListarUm(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patch(@PathVariable("id") Long id, @RequestBody UpdateVeiculoDTO dto) throws ResourceNotFoundException {
        return ResponseEntity.ok(veiculoService.Atualizar(id, dto));
    }

    @GetMapping("/placa")
    public ResponseEntity<Veiculo> getPlaca(@RequestParam("placa") String placa) throws ResourceBadRequestException {
        return ResponseEntity.ok(veiculoService.BuscarPorPlaca(placa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(veiculoService.Deletar(id));
    }
}

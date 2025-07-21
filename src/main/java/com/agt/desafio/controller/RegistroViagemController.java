package com.agt.desafio.controller;

import com.agt.desafio.dto.CriarRegistroViagemDTO;
import com.agt.desafio.dto.CriarRegistroViagemRetornoDTO;
import com.agt.desafio.entity.RegistroViagem;
import com.agt.desafio.errors.ResourceConflictException;
import com.agt.desafio.errors.ResourceNotFoundException;
import com.agt.desafio.service.RegistroViagemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viagens")
public class RegistroViagemController {
    @Autowired
    private RegistroViagemService registroViagemService;

    @PostMapping("/saida")
    public ResponseEntity<HttpStatus> createSaida(@Valid @RequestBody CriarRegistroViagemDTO dto) throws ResourceConflictException {
        registroViagemService.CriarSaida(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpStatus.CREATED);
    }

    @GetMapping("/registros")
    public ResponseEntity<List<RegistroViagem>> getAll() throws ResourceNotFoundException {
        return ResponseEntity.ok(registroViagemService.ListarTodos());
    }

    @PostMapping("/retorno")
    public ResponseEntity<RegistroViagem> createRetorno(@Valid @RequestBody CriarRegistroViagemRetornoDTO dto) throws ResourceConflictException {
        return ResponseEntity.ok(registroViagemService.CriarRetorno(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(registroViagemService.DeletarRegistro(id));
    }
}

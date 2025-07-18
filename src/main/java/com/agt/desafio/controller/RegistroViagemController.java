package com.agt.desafio.controller;

import com.agt.desafio.dto.CriarRegistroViagemDTO;
import com.agt.desafio.dto.CriarRegistroViagemRetornoDTO;
import com.agt.desafio.entity.RegistroViagem;
import com.agt.desafio.entity.Veiculo;
import com.agt.desafio.service.RegistroViagemService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/viagens")
public class RegistroViagemController {
    private RegistroViagemService registroViagemService;

    RegistroViagemController(RegistroViagemService r) {
        this.registroViagemService = r;
    }

    @PostMapping("/saida")
    public ResponseEntity<RegistroViagem> createSaida(@Valid @RequestBody CriarRegistroViagemDTO dto) throws BadRequestException {
        return ResponseEntity.ok(registroViagemService.CriarSaida(dto));
    }

    @GetMapping
    public ResponseEntity<List<RegistroViagem>> getAll() throws ObjectNotFoundException {
        return ResponseEntity.ok(registroViagemService.ListarTodos());
    }

    @PostMapping("/retorno")
    public ResponseEntity<RegistroViagem> createRetorno(@Valid @RequestBody CriarRegistroViagemRetornoDTO dto) throws BadRequestException {
        return ResponseEntity.ok(registroViagemService.CriarRetorno(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) throws ObjectNotFoundException {
        return ResponseEntity.ok(registroViagemService.DeletarRegistro(id));
    }
}

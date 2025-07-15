package com.agt.desafio.service;

import com.agt.desafio.dto.CriarFuncionarioDTO;
import com.agt.desafio.entity.Funcionario;
import com.agt.desafio.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    private FuncionarioRepository funcionarioRepository;
    private final Validacao validacao;

    FuncionarioService(FuncionarioRepository f, Validacao v) {
        this.funcionarioRepository = f;
        this.validacao = v;
    }

    public Funcionario Criar(CriarFuncionarioDTO dto) {
        Funcionario f = new Funcionario(
                dto.nome(),
                dto.email(),
                dto.cnh(),
                dto.categoria()
        );

        try {
            Boolean res = validacao.VFuncioario(f);

            if (res) {
                funcionarioRepository.save(f);
            } else {

            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return f;
    }

    public List<Funcionario> ListarTodos() {
        List<Funcionario> f = new ArrayList<Funcionario>();

        try {
            f = funcionarioRepository.findAll()
            ;
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return f;
    }

    public Funcionario ListarUm(Long id) {
        Funcionario f = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com ID: " + id));

        return f;
    }

    public Funcionario Atualizar(Long id, CriarFuncionarioDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com ID: " + id));

        if (dto.nome() != null) {
            funcionario.setNome(dto.nome());
        }

        if (dto.email() != null) {
            funcionario.setEmail(dto.email());
        }

        if (dto.cnh() != null) {
            funcionario.setCnh(dto.cnh());
        }

        if (dto.categoria() != null) {
            funcionario.setCategoria(dto.categoria());
        }

        return funcionarioRepository.save(funcionario);
    }

    public ResponseEntity<String> Deletar(Long id) {
        boolean func = funcionarioRepository.existsById(id);

        if (func) {
            funcionarioRepository.deleteById(id);
        }

        return ResponseEntity.ok("Funcionário deletado com sucesso.");
    }
}

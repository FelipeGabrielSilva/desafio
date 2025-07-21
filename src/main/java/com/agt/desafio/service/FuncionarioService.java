package com.agt.desafio.service;

import com.agt.desafio.dto.CriarFuncionarioDTO;
import com.agt.desafio.dto.UpdateFuncionarioDTO;
import com.agt.desafio.entity.Funcionario;
import com.agt.desafio.errors.ResourceBadRequestException;
import com.agt.desafio.errors.ResourceNotFoundException;
import com.agt.desafio.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional
    public Funcionario Criar(CriarFuncionarioDTO dto) throws ResourceBadRequestException {
        boolean existeEmail = funcionarioRepository.existsByEmail(dto.email());

        if (existeEmail) {
            throw new ResourceBadRequestException("Funcionário com esse e-mail já cadastrado.");
        }

        boolean existeCnh = funcionarioRepository.existsByCnh(dto.cnh());

        if (existeCnh) {
            throw new ResourceBadRequestException("Funcionário com essa CNH já cadastrado.");
        }

        Funcionario f = new Funcionario(dto.nome(), dto.email(), dto.cnh(), dto.categoria());

        funcionarioRepository.save(f);

        return f;
    }

    public List<Funcionario> ListarTodos() {
        List<Funcionario> f = funcionarioRepository.findAll();

        if (f.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possível recuperar os registro de funcionários.");
        }

        return f;
    }

    public Funcionario ListarUm(Long id) throws ResourceNotFoundException {
        return funcionarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi possível recuperar o funcionário."));
    }

    public Funcionario Atualizar(Long id, UpdateFuncionarioDTO dto) throws ObjectNotFoundException {
        Funcionario f = funcionarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado."));

        if (dto.nome() != null && !dto.nome().isBlank()) {
            f.setNome(dto.nome());
        }

        if (dto.email() != null && !dto.email().isBlank()) {
            f.setEmail(dto.email());
        }

        if (dto.cnh() != null && !dto.cnh().isBlank()) {
            f.setCnh(dto.cnh());
        }

        if (dto.categoria() != null) {
            f.setCategoria(dto.categoria());
        }

        return funcionarioRepository.save(f);
    }

    public String Deletar(Long id) throws ResourceNotFoundException {
        boolean res = funcionarioRepository.existsById(id);

        if (!res) {
            throw new ResourceNotFoundException("Funcionário não encontrado");
        }

        funcionarioRepository.deleteById(id);

        return "Funcionário deletado com sucesso.";
    }
}

package com.agt.desafio.service;

import com.agt.desafio.dto.CriarFuncionarioDTO;
import com.agt.desafio.dto.UpdateFuncionarioDTO;
import com.agt.desafio.entity.Funcionario;
import com.agt.desafio.enumtype.Categoria;
import com.agt.desafio.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
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

    @Transactional
    public Funcionario Criar(CriarFuncionarioDTO dto) throws BadRequestException {
        boolean existeEmail = funcionarioRepository.existsByEmail(dto.email());

        if (existeEmail) {
            throw new BadRequestException("Funcionário com esse e-mail já cadastrado.");
        }

        boolean existeCnh = funcionarioRepository.existsByCnh(dto.cnh());

        if (existeCnh) {
            throw new BadRequestException("Funcionário com essa CNH já cadastrado.");
        }

        Funcionario f = new Funcionario(
                dto.nome(),
                dto.email(),
                dto.cnh(),
                dto.categoria()
        );

        funcionarioRepository.save(f);

        return f;
    }

    public List<Funcionario> ListarTodos() {
        List<Funcionario> f = funcionarioRepository.findAll();

        if (f.isEmpty()) {
            throw new ObjectNotFoundException(f, "Não foi possível recuperar os registro de funcionários.");
        }

        return f;
    }

    public Optional<Funcionario> ListarUm(Long id) throws ObjectNotFoundException {
        Optional<Funcionario> f = funcionarioRepository.findById(id);

        if (f.isEmpty()) {
            throw new ObjectNotFoundException(f, "Não foi possível recuperar o funcionário.");
        }

        return f;
    }

    public Funcionario Atualizar(Long id, UpdateFuncionarioDTO dto) throws ObjectNotFoundException {
        Funcionario f = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, "Funcionário não encontrado."));

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

    public String Deletar(Long id) throws ObjectNotFoundException {
        boolean res = funcionarioRepository.existsById(id);

        if (!res) {
            throw new ObjectNotFoundException(id, "Veícuilo não encontrado");
        }

        funcionarioRepository.deleteById(id);

        return "Funcionário deletado com sucesso.";
    }
}

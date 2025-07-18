package com.agt.desafio.service;

import com.agt.desafio.dto.CriarVeiculoDTO;
import com.agt.desafio.dto.UpdateVeiculoDTO;
import com.agt.desafio.entity.Veiculo;
import com.agt.desafio.enumtype.Localizacao;
import com.agt.desafio.repository.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {
    private final VeiculoRepository veiculoRepository;

    VeiculoService(VeiculoRepository v, Validacao va) {
        this.veiculoRepository = v;
    }

    public Veiculo Criar(CriarVeiculoDTO dto) throws BadRequestException {
        boolean existePlaca = veiculoRepository.existsByPlaca(dto.placa());

        if (existePlaca) {
            throw new BadRequestException("Veículo com essa placa já cadastrado.");
        }

        Veiculo v = new Veiculo(
                dto.placa(),
                dto.marca(),
                dto.modelo(),
                Localizacao.NO_PATIO
        );

        veiculoRepository.save(v);

        return v;
    }

    public List<Veiculo> ListarTodos() throws ObjectNotFoundException {
        List<Veiculo> v = veiculoRepository.findAll();

        if (v.isEmpty()) {
            throw new ObjectNotFoundException(v, "Não foi possível recuperar os registros de veículos.");
        }

        return v;
    }

    public Optional<Veiculo> ListarUm(Long id) throws ObjectNotFoundException {
        Optional<Veiculo> v = veiculoRepository.findById(id);

        if (v.isEmpty()) {
            throw new ObjectNotFoundException(v, "Não foi possível recuperar o veículo.");
        }

        return v;
    }

    public String Atualizar(Long id, UpdateVeiculoDTO dto) throws ObjectNotFoundException {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, "Veículo não encontrado com o ID: " + id));

        if (dto.placa() != null && !dto.placa().isBlank()) {
            veiculo.setPlaca(dto.placa());
        }

        if (dto.marca() != null && !dto.marca().isBlank()) {
            veiculo.setMarca(dto.marca());
        }

        if (dto.modelo() != null && !dto.modelo().isBlank()) {
            veiculo.setModelo(dto.modelo());
        }

        if (dto.status() != null) {
            veiculo.setStatus(dto.status());
        }

        veiculoRepository.save(veiculo);

        return "Veículo atualizado com sucesso.";
    }

    public String Deletar(Long id) throws ObjectNotFoundException {
        boolean res = veiculoRepository.existsById(id);


        if (!res) {
            throw new ObjectNotFoundException(id, "Veícuilo não encontrado");
        }

        veiculoRepository.deleteById(id);

        return "Veículo deletado com sucesso.";
    }

    public Veiculo BuscarPorPlaca(String placa) throws ObjectNotFoundException {
        return veiculoRepository.findByPlaca(placa).orElseThrow(() -> new ObjectNotFoundException("Veículo com placa não encontrado.", (Object) placa));
    }

    public List<Veiculo> ListarPorStatus(Localizacao status) throws ObjectNotFoundException {
        return veiculoRepository.findByStatus(status).orElseThrow(() -> new ObjectNotFoundException("Não há veículos com o status selecionado.", (Object) status));
    }
}

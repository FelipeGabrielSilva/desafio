package com.agt.desafio.service;

import com.agt.desafio.dto.CriarVeiculoDTO;
import com.agt.desafio.entity.Veiculo;
import com.agt.desafio.enumtype.Localizacao;
import com.agt.desafio.repository.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VeiculoService {
    private VeiculoRepository veiculoRepository;
    private Validacao validacao;

    VeiculoService(VeiculoRepository v, Validacao va) {
        this.veiculoRepository = v;
        this.validacao = va;
    }

    public Veiculo Criar(CriarVeiculoDTO dto) throws BadRequestException {
        Veiculo v = new Veiculo(
                dto.placa(),
                dto.marca(),
                dto.modelo(),
                Localizacao.NO_PATIO
        );

        boolean res = validacao.VVeiculo(v);

        if (!res) {
            throw new BadRequestException("Informações para o cadastro de veículo está em falta.");
        }

        try {
            veiculoRepository.save(v);

        } catch (Exception e) {
            return v;
        }

        return v;
    }

    public List<Veiculo> ListarTodos() {
        List<Veiculo> v = new ArrayList<Veiculo>();

        try {
            v = veiculoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return v;
    }

    public Veiculo ListarUm(Long id) {
        Veiculo v = veiculoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Veículo não encontrado com o ID: " + id));

        return v;
    }

    public String Atualizar(Long id, CriarVeiculoDTO dto) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Veículo não encontrado com o ID: " + id));

        if (dto.placa() != null) {
            veiculo.setPlaca(dto.placa());
        }

        if (dto.marca() != null) {
            veiculo.setMarca(dto.marca());
        }

        if (dto.modelo() != null) {
            veiculo.setModelo(dto.modelo());
        }

        if (dto.status() != null) {
            veiculo.setStatus(dto.status());
        }

        veiculoRepository.save(veiculo);

        return "Veículo atualizado com sucesso.";
    }

    public String Deletar(Long id) {
        boolean res = veiculoRepository.existsById(id);

        if (res) {
            veiculoRepository.deleteById(id);
        }

        return "Veículo deletado com sucesso.";
    }
}

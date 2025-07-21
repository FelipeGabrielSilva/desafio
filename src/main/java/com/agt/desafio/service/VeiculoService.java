package com.agt.desafio.service;

import com.agt.desafio.dto.CriarVeiculoDTO;
import com.agt.desafio.dto.UpdateVeiculoDTO;
import com.agt.desafio.entity.Veiculo;
import com.agt.desafio.enumtype.Localizacao;
import com.agt.desafio.errors.ResourceBadRequestException;
import com.agt.desafio.errors.ResourceNotFoundException;
import com.agt.desafio.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository veiculoRepository;

    public Veiculo Criar(CriarVeiculoDTO dto) throws ResourceBadRequestException {
        boolean existePlaca = veiculoRepository.existsByPlaca(dto.placa());

        if (existePlaca) {
            throw new ResourceBadRequestException("Veículo com essa placa já cadastrado.");
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

    public List<Veiculo> ListarTodos() throws ResourceNotFoundException {
        String ord = "id";
        List<Veiculo> v = veiculoRepository.findAll(Sort.by(ord));

        if (v.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possível recuperar os registros de veículos.");
        }

        return v;
    }

    public Optional<Veiculo> ListarUm(Long id) throws ResourceNotFoundException {
        Optional<Veiculo> v = veiculoRepository.findById(id);

        if (v.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possível recuperar o veículo.");
        }

        return v;
    }

    public String Atualizar(Long id, UpdateVeiculoDTO dto) throws ResourceNotFoundException {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado com o ID: " + id));

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

    public String Deletar(Long id) throws ResourceNotFoundException {
        boolean res = veiculoRepository.existsById(id);


        if (!res) {
            throw new ResourceNotFoundException("Veículo não encontrado");
        }

        veiculoRepository.deleteById(id);

        return "Veículo deletado com sucesso.";
    }

    public Veiculo BuscarPorPlaca(String placa) throws ResourceNotFoundException {
        return veiculoRepository.findByPlaca(placa).orElseThrow(() -> new ResourceNotFoundException("Veículo com placa não encontrado."));
    }

    public List<Veiculo> ListarPorStatus(Localizacao status) throws ResourceNotFoundException {
        return veiculoRepository.findByStatus(status).orElseThrow(() -> new ResourceNotFoundException("Não há veículos com o status selecionado."));
    }
}

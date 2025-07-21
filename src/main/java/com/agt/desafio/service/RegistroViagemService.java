package com.agt.desafio.service;

import com.agt.desafio.dto.CriarRegistroViagemDTO;
import com.agt.desafio.dto.CriarRegistroViagemRetornoDTO;
import com.agt.desafio.dto.UpdateVeiculoDTO;
import com.agt.desafio.entity.Funcionario;
import com.agt.desafio.entity.RegistroViagem;
import com.agt.desafio.entity.Veiculo;
import com.agt.desafio.enumtype.Localizacao;
import com.agt.desafio.errors.ResourceBadRequestException;
import com.agt.desafio.errors.ResourceConflictException;
import com.agt.desafio.errors.ResourceNotFoundException;
import com.agt.desafio.repository.RegistroViagemRepository;
import jakarta.validation.constraints.Null;
import org.apache.coyote.BadRequestException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroViagemService {
    private final RegistroViagemRepository registroViagemRepository;
    private final VeiculoService veiculoService;
    private final FuncionarioService funcionarioService;

    RegistroViagemService(RegistroViagemRepository reg, VeiculoService veic, FuncionarioService func) {
        this.registroViagemRepository = reg;
        this.veiculoService = veic;
        this.funcionarioService = func;
    }

    public RegistroViagem CriarSaida(CriarRegistroViagemDTO dto) throws ResourceConflictException {
        Veiculo v = veiculoService.BuscarPorPlaca(dto.placa_veiculo());
        Funcionario f = funcionarioService.ListarUm(dto.id_motorista());

        if (v.getStatus() != Localizacao.NO_PATIO) {
            throw new ResourceConflictException("O veículo informado não se encontra no pátio.");
        }

        UpdateVeiculoDTO upv = new UpdateVeiculoDTO(
                v.getPlaca(),
                v.getMarca(),
                v.getModelo(),
                Localizacao.EM_VIAGEM
        );

        veiculoService.Atualizar(v.getId(), upv);

        RegistroViagem r = new RegistroViagem(
                v,
                f,
                dto.saida(),
                dto.destino(),
                dto.passageiros()
        );

        registroViagemRepository.save(r);

        return r;
    }

    public List<RegistroViagem> ListarTodos() throws ResourceNotFoundException {
        List<RegistroViagem> r = registroViagemRepository.findAll();

        if (r.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possível recuperar os registros de viagens.");
        }

        return r;
    }

    public RegistroViagem CriarRetorno(CriarRegistroViagemRetornoDTO dto) throws ResourceConflictException {
        Veiculo v = veiculoService.BuscarPorPlaca(dto.placa());

        if (v.getStatus() != Localizacao.EM_VIAGEM) {
            throw new ResourceConflictException("O veículo informado não se encontra em viagem.");
        }

        RegistroViagem resv = registroViagemRepository.findFirstByVeiculoAndRetornoIsNullOrderBySaidaDesc(v)
                .orElseThrow(() -> new ResourceConflictException("Não há registro de viagem em aberto para o veículo informado.")
                );

        UpdateVeiculoDTO upv = new UpdateVeiculoDTO(
                v.getPlaca(),
                v.getMarca(),
                v.getModelo(),
                Localizacao.NO_PATIO
        );

        veiculoService.Atualizar(v.getId(), upv);

        Date dataRetorno = new Date();
        resv.setRetorno(dataRetorno);

        registroViagemRepository.save(resv);

        return resv;
    }

    public String DeletarRegistro(Long id) throws ResourceNotFoundException {
        boolean existeRegistro = registroViagemRepository.existsById(id);

        if (!existeRegistro) {
            throw new ResourceNotFoundException("O registro de viagem não existe.");
        }

        registroViagemRepository.deleteById(id);

        return "O registro de viagem foi deletado com sucesso.";
    }
}

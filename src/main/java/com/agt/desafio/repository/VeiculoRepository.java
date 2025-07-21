package com.agt.desafio.repository;

import com.agt.desafio.entity.Veiculo;
import com.agt.desafio.enumtype.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    boolean existsByPlaca(String placa);

    Optional<Veiculo> findByPlaca(String placa);

    Optional<List<Veiculo>> findByStatus(Localizacao status);
}

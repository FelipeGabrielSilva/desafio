package com.agt.desafio.repository;

import com.agt.desafio.entity.RegistroViagem;
import com.agt.desafio.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistroViagemRepository extends JpaRepository<RegistroViagem, Long> {
    Optional<RegistroViagem> findFirstByVeiculoAndRetornoIsNullOrderBySaidaDesc(Veiculo veiculo);
}

package com.agt.desafio.service;

import com.agt.desafio.entity.Funcionario;
import com.agt.desafio.entity.Veiculo;
import com.agt.desafio.enumtype.Localizacao;
import org.springframework.stereotype.Service;

@Service
public class Validacao {

    public Boolean VFuncioario(Funcionario f) {
        if (f.getNome() == null) {
            return false;
        }

        if (f.getEmail() == null) {
            return false;
        }

        if (f.getCnh() == null) {
            return false;
        }

        if (f.getCategoria() == null) {
            return false;
        }

        return true;
    }

    public Boolean VVeiculo(Veiculo v) {
        if (v.getPlaca() == null) {
            return false;
        }

        if (v.getMarca() == null) {
            return false;
        }

        if (v.getModelo() == null) {
            return false;
        }

        if (v.getStatus() != Localizacao.NO_PATIO) {
            return false;
        }

        return true;
    }
}

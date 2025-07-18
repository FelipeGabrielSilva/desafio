package com.agt.desafio.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "registro_viagem")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class RegistroViagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "id_veiculo", nullable = false)
    @ManyToOne
    private Veiculo veiculo;

    @JoinColumn(name = "id_funcionario_motorista", nullable = false)
    @ManyToOne
    private Funcionario motorista;

    @Column(name = "data_saida", nullable = false)
    private Date saida;

    @Column(name = "data_retorno")
    private Date retorno;

    @Column(name = "destino", nullable = false, length = 150)
    private String destino;

    @Column(name = "passageiros")
    private String passageiros;

    @Column(name = "criado_em", nullable = false)
    @CreationTimestamp
    private Instant criadoEm;

    @Column(name = "atualizado_em")
    @UpdateTimestamp
    private Instant atualizadoEm;


    public RegistroViagem(Veiculo veiculo, Funcionario funcionario, Date saida, String destino, String passageiros) {
        this.veiculo = veiculo;
        this.motorista = funcionario;
        this.saida = saida;
        this.destino = destino;
        this.passageiros = passageiros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Funcionario getMotorista() {
        return motorista;
    }

    public void setMotorista(Funcionario motorista) {
        this.motorista = motorista;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public Date getRetorno() {
        return retorno;
    }

    public void setRetorno(Date retorno) {
        this.retorno = retorno;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getPassageiros() {
        return passageiros;
    }

    public void setPassageiros(String passageiros) {
        this.passageiros = passageiros;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Instant criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Instant getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(Instant atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }
}

package com.agt.desafio.entity;

import com.agt.desafio.enumtype.Localizacao;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "veiculo")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "placa", nullable = false, unique = true, length = 8)
    private String placa;

    @Column(name = "marca", nullable = false, length = 30)
    private String marca;

    @Column(name = "modelo", nullable = false, length = 30)
    private String modelo;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Localizacao status;

    @Column(name = "criado_em", nullable = false)
    @CreationTimestamp
    private Instant criadoEm;

    @Column(name = "atualizado_em")
    @UpdateTimestamp
    private Instant atualizadoEm;

    public Veiculo(String placa, String marca, String modelo, Localizacao status) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Localizacao getStatus() {
        return status;
    }

    public void setStatus(Localizacao status) {
        this.status = status;
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

package com.agt.desafio.entity;

import ch.qos.logback.core.status.InfoStatus;
import com.agt.desafio.enumtype.Categoria;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "funcionario")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "cnh", nullable = false, unique = true, length = 11)
    private String cnh;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false, length = 3)
    private Categoria categoria;

    @Column(name = "criado_em", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant criadoEm;

    @Column(name = "atualizado_em")
    @UpdateTimestamp
    private Instant atualizadoEm;

    public Funcionario(String nome, String email, String cnh, Categoria categoria) {
        this.nome = nome;
        this.email = email;
        this.cnh = cnh;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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

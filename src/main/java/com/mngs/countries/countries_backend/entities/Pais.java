package com.mngs.countries.countries_backend.entities;

import jakarta.persistence.*;

@Entity
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true, nullable=false)
    private String nome;

    @Column(unique=true, nullable=false)
    private String sigla;

    @Column(unique=true, nullable=false)
    private String gentilico;

    public Pais() {}

    public Pais(int id, String nome, String sigla, String gentilico) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.gentilico = gentilico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getGentilico() {
        return gentilico;
    }

    public void setGentilico(String gentilico) {
        this.gentilico = gentilico;
    }
}

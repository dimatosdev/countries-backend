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
}

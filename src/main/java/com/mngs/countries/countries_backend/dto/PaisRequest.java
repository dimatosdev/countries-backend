package com.mngs.countries.countries_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de requisição para criação ou edição de um país")
public class PaisRequest {

    @Schema(description = "ID do país (para edição)", example = "1")
    private Long id;

    @Schema(description = "Nome do país", example = "Brasil", required = true)
    private String nome;

    @Schema(description = "Sigla do país", example = "BR", required = true)
    private String sigla;

    @Schema(description = "Gentílico do país", example = "brasileiro", required = true)
    private String gentilico;

    public PaisRequest(Long id, String nome, String sigla, String gentilico) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.gentilico = gentilico;
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

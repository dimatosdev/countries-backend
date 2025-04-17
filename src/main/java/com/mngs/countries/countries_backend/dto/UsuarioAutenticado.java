package com.mngs.countries.countries_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto que representa um usuário autenticado.")
public class UsuarioAutenticado {

    @Schema(description = "Login do usuário", example = "admin")
    private String login;

    @Schema(description = "Nome completo do usuário", example = "Administrador Geral")
    private String nome;

    @Schema(description = "Token de autenticação", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6...")
    private String token;

    @Schema(description = "Indica se o usuário é administrador", example = "true")
    private boolean administrador;

    @Schema(description = "Indica se a autenticação foi bem-sucedida", example = "true")
    private boolean autenticado;

    public UsuarioAutenticado(String login, String nome, String token, boolean administrador, boolean autenticado) {
        this.login = login;
        this.nome = nome;
        this.token = token;
        this.administrador = administrador;
        this.autenticado = autenticado;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }
}

package com.mngs.countries.countries_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto que representa a requisição de login.")
public class LoginRequest {

    @Schema(description = "Login do usuário", example = "admin")
    private String login;

    @Schema(description = "Senha do usuário", example = "123456")
    private String senha;

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
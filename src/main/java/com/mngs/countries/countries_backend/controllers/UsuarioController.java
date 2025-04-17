package com.mngs.countries.countries_backend.controllers;

import com.mngs.countries.countries_backend.dto.LoginRequest;
import com.mngs.countries.countries_backend.dto.UsuarioAutenticado;
import com.mngs.countries.countries_backend.service.AutenticacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuários", description = "Operações relacionadas a usuários")
public class UsuarioController {

    @Autowired
    private AutenticacaoService authService;

    @PostMapping("/autenticar")
    @Operation(
            summary = "Autenticar usuário",
            description = "Realiza a autenticação do usuário com login e senha, retornando os dados do usuário autenticado com token."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticação realizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioAutenticado.class))),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "400", description = "Requisição mal formatada")
    })
    public UsuarioAutenticado autenticar(@RequestBody LoginRequest request) {
        return authService.autenticar(request.getLogin(), request.getSenha());
    }

    @GetMapping("/renovar-ticket")
    @Operation(
            summary = "Renovar token de autenticação",
            description = "Renova o token JWT de um usuário autenticado se o token atual ainda for válido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token renovado com sucesso (true/false)"),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido")
    })
    public boolean renovar(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.replace("Bearer ", "");
            return authService.renovarToken(token);
        }
        return false;
    }

}

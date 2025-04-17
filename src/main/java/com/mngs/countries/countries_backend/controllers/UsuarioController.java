package com.mngs.countries.countries_backend.controllers;

import com.mngs.countries.countries_backend.dto.LoginRequest;
import com.mngs.countries.countries_backend.dto.UsuarioAutenticado;
import com.mngs.countries.countries_backend.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private AutenticacaoService authService;

    @PostMapping("/autenticar")
    public UsuarioAutenticado autenticar(@RequestBody LoginRequest request) {
        return authService.autenticar(request.getLogin(), request.getSenha());
    }

    @GetMapping("/renovar-ticket")
    public boolean renovar(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.replace("Bearer ", "");
            return authService.renovarToken(token);
        }
        return false;
    }

}

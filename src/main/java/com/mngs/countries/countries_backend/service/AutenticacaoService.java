package com.mngs.countries.countries_backend.service;

import com.mngs.countries.countries_backend.dto.UsuarioAutenticado;
import com.mngs.countries.countries_backend.entities.Token;
import com.mngs.countries.countries_backend.entities.Usuario;
import com.mngs.countries.countries_backend.repositories.TokenRepository;
import com.mngs.countries.countries_backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private TokenRepository tokenRepo;

    public UsuarioAutenticado autenticar(String login, String senha) {
        Optional<Usuario> userOpt = usuarioRepo.findByLogin(login);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (userOpt.isPresent() && encoder.matches(senha, userOpt.get().getSenha())) {
            String novoToken = UUID.randomUUID().toString();
            LocalDateTime expiracao = LocalDateTime.now().plusMinutes(1);

            Token token = new Token(null, novoToken, login, expiracao, userOpt.get().isAdministrador());
            tokenRepo.save(token);

            return new UsuarioAutenticado(login, userOpt.get().getNome(), novoToken,
                    userOpt.get().isAdministrador(), true);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login ou senha inválidos");
    }

    public boolean renovarToken(String token) {
        Optional<Token> tokenOpt = tokenRepo.findByToken(token);
        if (tokenOpt.isPresent()) {
            Token t = tokenOpt.get();
            t.setExpiracao(LocalDateTime.now().plusMinutes(5));
            tokenRepo.save(t);
            return true;
        }
        return false;
    }

    public boolean tokenValido(String token) {
        return tokenRepo.findByToken(token)
                .filter(t -> t.getExpiracao().isAfter(LocalDateTime.now()))
                .isPresent();
    }

    public boolean isAdmin(String token) {
        return tokenRepo.findByToken(token)
                .map(Token::isAdministrador)
                .orElse(false);
    }

}

package com.mngs.countries.countries_backend.service;

import com.mngs.countries.countries_backend.entities.Usuario;
import com.mngs.countries.countries_backend.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public DataLoader(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario usuario : usuarios) {
            if (!usuario.getSenha().startsWith("$2a$")) {
                String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
                usuario.setSenha(senhaCriptografada);
                usuarioRepository.save(usuario);
            }
        }

        System.out.println("✅ Senhas criptografadas com sucesso!");
    }

}

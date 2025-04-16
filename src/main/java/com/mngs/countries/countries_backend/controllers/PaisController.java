package com.mngs.countries.countries_backend.controllers;

import com.mngs.countries.countries_backend.entities.Pais;
import com.mngs.countries.countries_backend.repositories.PaisRepository;
import com.mngs.countries.countries_backend.service.AutenticacaoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pais")
public class PaisController {

    @Autowired
    private PaisRepository paisRepo;

    @Autowired
    private AutenticacaoService authService;

    @GetMapping("/listar")
    public ResponseEntity<List<Pais>> listar(@RequestParam String token) {
        if(!authService.tokenValido(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(paisRepo.findAll());
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestParam String token, @RequestBody Pais pais) {
        if (!authService.tokenValido(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (!authService.isAdmin(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        Pais salvo = paisRepo.save(pais);
        return ResponseEntity.ok(salvo);
    }
    @GetMapping("/pesquisar")
    public ResponseEntity<List<Pais>> pesquisar(@RequestParam String token, @RequestParam String filtro) {
        if (!authService.tokenValido(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(paisRepo.findByNomeContainingIgnoreCase(filtro));
    }

    @GetMapping("/excluir")
    public ResponseEntity<Boolean> excluir(@RequestParam String token, @RequestParam Long id) {
        if (!authService.tokenValido(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (!authService.isAdmin(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (paisRepo.existsById(id)) {
            paisRepo.deleteById(id);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }


}

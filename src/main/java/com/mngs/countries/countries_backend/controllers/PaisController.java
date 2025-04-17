package com.mngs.countries.countries_backend.controllers;

import com.mngs.countries.countries_backend.dto.PaisRequest;
import com.mngs.countries.countries_backend.entities.Pais;
import com.mngs.countries.countries_backend.repositories.PaisRepository;
import com.mngs.countries.countries_backend.service.AutenticacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pais")
@Tag(name = "Países", description = "Operações relacionadas ao gerenciamento de países")
public class PaisController {

    @Autowired
    private PaisRepository paisRepo;

    @Autowired
    private AutenticacaoService authService;

    @GetMapping("/listar")
    @Operation(
            summary = "Listar países",
            description = "Lista todos os países cadastrados no sistema. Requer autenticação."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de países retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pais.class))),
            @ApiResponse(responseCode = "401",
                    description = "Token inválido ou não autorizado")
    })
    public ResponseEntity<List<Pais>> listar(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        if(!authService.tokenValido(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(paisRepo.findAll());
    }

    @PostMapping("/salvar")
    @Operation(
            summary = "Salvar ou atualizar país",
            description = "Salva ou atualiza um país com base no ID. Requer autenticação e privilégio de administrador."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "País salvo ou atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pais.class))),
            @ApiResponse(responseCode = "401",
                    description = "Token inválido ou não autorizado"),
            @ApiResponse(responseCode = "403",
                    description = "Usuário não é administrador")
    })
    public ResponseEntity<?> salvar(@RequestParam String token, @RequestBody PaisRequest paisRequest) {
        if (!authService.tokenValido(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (!authService.isAdmin(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        Pais pais = new Pais();
        pais.setId(paisRequest.getId());
        pais.setNome(paisRequest.getNome());
        pais.setSigla(paisRequest.getSigla());
        pais.setGentilico(paisRequest.getGentilico());

        Pais salvo = paisRepo.save(pais);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/pesquisar")
    @Operation(
            summary = "Pesquisar países",
            description = "Busca países pelo nome utilizando um filtro."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de países encontrados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pais.class))),
            @ApiResponse(responseCode = "401",
                    description = "Token inválido ou não autorizado"),
            @ApiResponse(responseCode = "400",
                    description = "Parâmetro de filtro inválido")
    })
    public ResponseEntity<List<Pais>> pesquisar(@RequestHeader("Authorization") String token, @RequestParam String filtro) {
        token = token.replace("Bearer ", "");
        if (!authService.tokenValido(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(paisRepo.findByNomeContainingIgnoreCase(filtro));
    }


    @GetMapping("/excluir")
    @Operation(
            summary = "Excluir país",
            description = "Exclui um país baseado no ID fornecido. Requer privilégios de administrador."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação de exclusão concluída com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "boolean", example = "true"))),
            @ApiResponse(responseCode = "401",
                    description = "Token inválido ou não autorizado"),
            @ApiResponse(responseCode = "403",
                    description = "Usuário não é administrador"),
            @ApiResponse(responseCode = "404",
                    description = "País não encontrado")
    })
    public ResponseEntity<Boolean> excluir(@RequestHeader("Authorization") String token, @RequestParam Long id) {
        token = token.replace("Bearer ", "");
        if (!authService.tokenValido(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (!authService.isAdmin(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (paisRepo.existsById(id)) {
            paisRepo.deleteById(id);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

}

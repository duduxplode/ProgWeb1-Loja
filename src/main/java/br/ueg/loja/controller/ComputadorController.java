package br.ueg.loja.controller;

import br.ueg.api.exception.MessageResponse;
import br.ueg.loja.dto.ComputadorDTO;
import br.ueg.loja.mapper.ComputadorMapper;
import br.ueg.loja.model.Computador;
import br.ueg.loja.service.ComputadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "${app.api.base}/computador")
public class ComputadorController {
    @Autowired
    ComputadorMapper computadorMapper;
    @Autowired
    ComputadorService computadorService;

    @GetMapping()
    @Operation(description = "Listagem Geral de computadores")
    public List<ComputadorDTO> listAll(){
        List<Computador> computadores = computadorService.listarTodos();
        return computadorMapper.toDTO(computadores);
    }

    @PostMapping
    @Operation(description = "Método utilizado para realizar a inclusão de um computador", responses = {
            @ApiResponse(responseCode = "200", description = "Computador Incluído",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ComputadorDTO.class) )),
            @ApiResponse(responseCode = "400", description = "Campos Obrigatórios não informados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageResponse.class)
                    )
            )
    })
    public ResponseEntity<ComputadorDTO> incluir(@Valid @RequestBody ComputadorDTO computadorDTO){
        //prepração para entrada.
        Computador computadorIncluir = this.computadorMapper.toComputador(computadorDTO);

        //chamada do serviço
        System.out.println(computadorIncluir);
        computadorIncluir = this.computadorService.incluir(computadorIncluir);

        //preparação para o retorno
        ComputadorDTO retorno = this.computadorMapper.toDTO(computadorIncluir);
        return ResponseEntity.ok(retorno);
    }

    @PutMapping(path = "/{id}")
    @Operation(description = "Método utilizado para altlerar os dados de um computador", responses = {
            @ApiResponse(responseCode = "200", description = "Computador Alterado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ComputadorDTO.class))),
            @ApiResponse(responseCode = "404", description = "Computador Não encontrado", content = @Content(mediaType = "application/json"))})
    public ResponseEntity<ComputadorDTO> alterar(@RequestBody() ComputadorDTO computadorDTO, @PathVariable(name = "id") Long id ){
        Computador computador = computadorMapper.toComputador(computadorDTO);
        Computador alterar = computadorService.alterar(computador,id);
        return ResponseEntity.ok(this.computadorMapper.toDTO(alterar));
    }

    @GetMapping(path = "/{id}")
    @Operation(description = "Obter os dados completos de um computador pelo id informado!", responses = {
            @ApiResponse(responseCode = "200", description = "Computador informado no ID", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ComputadorDTO.class))),
            @ApiResponse(responseCode = "404", description = "Computador Não encontrado", content = @Content(mediaType = "application/json"))})
    public ResponseEntity<ComputadorDTO> ObterPorId(@PathVariable(name = "id") Long id){
        Computador computador = this.computadorService.obterPeloId(id);
        return ResponseEntity.ok(this.computadorMapper.toDTO(computador));
    }

    @DeleteMapping(path ="/{id}")
    @Operation(description = "Método utililzado para remover um computador pelo Id informado")
    public ResponseEntity<ComputadorDTO> remover(@PathVariable(name = "id") Long id){
        Computador computadorExcluido = this.computadorService.excluir(id);
        return ResponseEntity.ok(this.computadorMapper.toDTO(computadorExcluido));
    }
}

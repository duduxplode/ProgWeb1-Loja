package br.ueg.loja.controller;

import br.ueg.loja.dto.ComputadorDTO;
import br.ueg.loja.dto.TipoComputadorDTO;
import br.ueg.loja.mapper.ComputadorMapper;
import br.ueg.loja.mapper.TipoComputadorMapper;
import br.ueg.loja.model.Computador;
import br.ueg.loja.model.TipoComputador;
import br.ueg.loja.service.ComputadorService;
import br.ueg.loja.service.TipoComputadorService;
import br.ueg.loja.service.VendaService;
import br.ueg.loja.storage.StorageService;
import br.ueg.loja.utils.Base64Converter;
import br.ueg.prog.webi.api.exception.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "${app.api.base}/tipo_computador")
public class TipoComputadorController {
    @Autowired
    TipoComputadorMapper tipoComputadorMapper;
    @Autowired
    TipoComputadorService tipoComputadorService;

    private final StorageService storageService;

    public TipoComputadorController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping()
    @Operation(description = "Listagem Geral de tipos de computadores" , responses = {
            @ApiResponse(responseCode = "200", description = "Listagem geral",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = TipoComputadorDTO.class))))})
    public List<TipoComputadorDTO> listAll(){
        List<TipoComputadorDTO> tipoComputadorDTOS = tipoComputadorMapper.toDTO(tipoComputadorService.listarTodos());
        return tipoComputadorDTOS;
    }

    @PostMapping
    @Operation(description = "Método utilizado para realizar a inclusão de um tipo de computador", responses = {
            @ApiResponse(responseCode = "200", description = "Tipo de computador Incluído",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoComputadorDTO.class) )),
            @ApiResponse(responseCode = "400", description = "Campos Obrigatórios não informados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageResponse.class)
                    )
            )
    })
    public ResponseEntity<TipoComputadorDTO> incluir(@Valid @RequestBody TipoComputadorDTO tipoComputadorDTO){
        //prepração para entrada.
        TipoComputador tipoComputadorIncluir = this.tipoComputadorMapper.toTipoComputador(tipoComputadorDTO);

        //chamada do serviço
        System.out.println(tipoComputadorIncluir);
        tipoComputadorIncluir = this.tipoComputadorService.incluir(tipoComputadorIncluir);

        //preparação para o retorno
        TipoComputadorDTO retorno = this.tipoComputadorMapper.toDTO(tipoComputadorIncluir);
        return ResponseEntity.ok(retorno);
    }

    @PutMapping(path = "/{id}")
    @Operation(description = "Método utilizado para altlerar os dados de um tipo de computador", responses = {
            @ApiResponse(responseCode = "200", description = "Tipo de computador Alterado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoComputadorDTO.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de computador Não encontrado", content = @Content(mediaType = "application/json"))})
    public ResponseEntity<TipoComputadorDTO> alterar(@RequestBody() TipoComputadorDTO tipoComputadorDTO, @PathVariable(name = "id") Long id ){
        TipoComputador tipoComputador = tipoComputadorMapper.toTipoComputador(tipoComputadorDTO);
        TipoComputador alterar = tipoComputadorService.alterar(tipoComputador,id);
        return ResponseEntity.ok(this.tipoComputadorMapper.toDTO(alterar));
    }

    @GetMapping(path = "/{id}")
    @Operation(description = "Obter os dados completos de um tipo de computador pelo id informado!", responses = {
            @ApiResponse(responseCode = "200", description = "Tipo de computador informado no ID", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoComputadorDTO.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de computador Não encontrado", content = @Content(mediaType = "application/json"))})
    public ResponseEntity<TipoComputadorDTO> ObterPorId(@PathVariable(name = "id") Long id){
        TipoComputador tipoComputador = this.tipoComputadorService.obterPeloId(id);
        return ResponseEntity.ok(this.tipoComputadorMapper.toDTO(tipoComputador));
    }

    @DeleteMapping(path ="/{id}")
    @Operation(description = "Método utililzado para remover um tipo de computador pelo Id informado", responses = {
            @ApiResponse(responseCode = "200", description = "Tipo de computador deletado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ComputadorDTO.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de computador Não encontrado", content = @Content(mediaType = "application/json"))})
    public ResponseEntity<TipoComputadorDTO> remover(@PathVariable(name = "id") Long id){
        TipoComputador tipoComputadorExcluido = this.tipoComputadorService.excluir(id);
        return ResponseEntity.ok(this.tipoComputadorMapper.toDTO(tipoComputadorExcluido));
    }
}

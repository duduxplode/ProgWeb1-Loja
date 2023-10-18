package br.ueg.loja.controller;

import br.ueg.loja.dto.ComputadorDTO;
import br.ueg.loja.mapper.ComputadorMapper;
import br.ueg.loja.model.Computador;
import br.ueg.loja.service.ComputadorService;
import br.ueg.loja.service.VendaService;
import br.ueg.loja.storage.StorageService;
import br.ueg.loja.utils.Base64Converter;
import br.ueg.prog.webi.api.controller.CrudController;
import br.ueg.prog.webi.api.exception.MessageResponse;
import br.ueg.prog.webi.api.model.IEntidade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "${app.api.base}/computador")
public class ComputadorController extends CrudController
    <Computador, ComputadorDTO, Long, ComputadorMapper, ComputadorService>
{
    @Autowired
    ComputadorMapper computadorMapper;
    @Autowired
    ComputadorService computadorService;
    @Autowired
    VendaService vendaService;

    private final StorageService storageService;

    public ComputadorController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping()
    @Operation(description = "Listagem Geral de computadores" , responses = {
            @ApiResponse(responseCode = "200", description = "Listagem geral",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = ComputadorDTO.class))))})
    public ResponseEntity<List<ComputadorDTO>> listAll(){
        List<ComputadorDTO> computadores = computadorMapper.toDTO(computadorService.listarTodos());
        geraComplemento(computadores);
        return ResponseEntity.ok(computadores);
    }

    private void geraComplemento(List<ComputadorDTO> computadores) {
        for (ComputadorDTO computadorDto: computadores) {
            computadorDto.setContVendas(vendaService.contarVendas(computadorDto.getId()));
            if (!computadorDto.getImagem().isEmpty()) {
                File arquivo = new File(storageService.load(computadorDto.getImagem()).toString());
                try {
                    computadorDto.setImagemBase64(Base64Converter.toBase64(arquivo));
                } catch (IOException e) {

                }
            }
        }
    }

    @PostMapping
    @Operation(
            description = "Método utilizado para realizar a inclusão de um computador",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Entidade Incluida",
                    content = {@Content(
                            mediaType = "application/json"
                    )}
            ), @ApiResponse(
                    responseCode = "403",
                    description = "Acesso negado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = MessageResponse.class
                            )
                    )}
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Erro de Negócio",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = MessageResponse.class
                            )
                    )}
            )}
    )
    public ResponseEntity<ComputadorDTO> incluir(@RequestBody ComputadorDTO modeloDTO) {
        Computador modeloIncluir = this.mapper.toModelo(modeloDTO);
        System.out.println(modeloIncluir);
        modeloIncluir = this.service.incluir(modeloIncluir);
        modeloIncluir.setImagem(modeloIncluir.getId()+ "." +FilenameUtils.getExtension(modeloIncluir.getImagem()));
        modeloIncluir = this.service.alterar(modeloIncluir, modeloIncluir.getId());
        return ResponseEntity.ok(this.mapper.toDTO(modeloIncluir));
    }
}

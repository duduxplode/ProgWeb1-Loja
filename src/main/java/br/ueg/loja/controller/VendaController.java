package br.ueg.loja.controller;

import br.ueg.loja.dto.VendaDTO;
import br.ueg.loja.mapper.VendaMapper;
import br.ueg.loja.model.Venda;
import br.ueg.loja.service.VendaService;
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

import java.util.List;

@RestController
@RequestMapping(path = "${app.api.base}/venda")
public class VendaController {
    @Autowired
    VendaMapper vendaMapper;
    @Autowired
    VendaService vendaService;

    @GetMapping()
    @Operation(description = "Listagem Geral de vendas" , responses = {
            @ApiResponse(responseCode = "200", description = "Listagem geral",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = VendaDTO.class))))})
    public List<VendaDTO> listAll(){
        List<Venda> vendas = vendaService.listarTodos();
        return vendaMapper.toDTO(vendas);
    }

    @PostMapping
    @Operation(description = "Método utilizado para realizar a inclusão de uma venda", responses = {
            @ApiResponse(responseCode = "200", description = "Venda Incluída",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VendaDTO.class) )),
            @ApiResponse(responseCode = "400", description = "Campos Obrigatórios não informados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageResponse.class)
                    )
            )
    })
    public ResponseEntity<VendaDTO> incluir(@Valid @RequestBody VendaDTO vendaDTO){
        //prepração para entrada.
        Venda vendaIncluir = this.vendaMapper.toVenda(vendaDTO);

        //chamada do serviço
        System.out.println(vendaIncluir);
        vendaIncluir = this.vendaService.incluir(vendaIncluir);

        //preparação para o retorno
        VendaDTO retorno = this.vendaMapper.toDTO(vendaIncluir);
        return ResponseEntity.ok(retorno);
    }

    @PutMapping(path = "/{id}")
    @Operation(description = "Método utilizado para altlerar os dados de uma venda", responses = {
            @ApiResponse(responseCode = "200", description = "Venda Alterada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VendaDTO.class))),
            @ApiResponse(responseCode = "404", description = "Venda Não encontrada", content = @Content(mediaType = "application/json"))})
    public ResponseEntity<VendaDTO> alterar(@RequestBody() VendaDTO vendaDTO, @PathVariable(name = "id") Long id ){
        Venda venda = vendaMapper.toVenda(vendaDTO);
        Venda alterar = vendaService.alterar(venda,id);
        return ResponseEntity.ok(this.vendaMapper.toDTO(alterar));
    }

    @GetMapping(path = "/{id}")
    @Operation(description = "Obter os dados completos de uma venda pelo id informado!", responses = {
            @ApiResponse(responseCode = "200", description = "Venda informada no ID", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VendaDTO.class))),
            @ApiResponse(responseCode = "404", description = "Venda Não encontrada", content = @Content(mediaType = "application/json"))})
    public ResponseEntity<VendaDTO> ObterPorId(@PathVariable(name = "id") Long id){
        Venda venda = this.vendaService.obterPeloId(id);
        return ResponseEntity.ok(this.vendaMapper.toDTO(venda));
    }

    @DeleteMapping(path ="/{id}")
    @Operation(description = "Método utililzado para remover uma venda pelo Id informado")
    public ResponseEntity<VendaDTO> remover(@PathVariable(name = "id") Long id){
        Venda vendaExcluida = this.vendaService.excluir(id);
        return ResponseEntity.ok(this.vendaMapper.toDTO(vendaExcluida));
    }
}

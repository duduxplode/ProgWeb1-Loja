package br.ueg.loja.controller;

import br.ueg.loja.dto.ComputadorDTO;
import br.ueg.loja.mapper.ComputadorMapper;
import br.ueg.loja.model.Computador;
import br.ueg.loja.service.ComputadorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Operation(description = "Método utilizado para realizar a inclusão de um computador")
    public ComputadorDTO incluir(@RequestBody ComputadorDTO computadorDTO){
        //prepração para entrada.
        Computador computadorIncluir = this.computadorMapper.toComputador(computadorDTO);

        //chamada do serviço
        System.out.println(computadorIncluir);
        computadorIncluir = this.computadorService.incluir(computadorIncluir);

        //preparação para o retorno
        ComputadorDTO retorno = this.computadorMapper.toDTO(computadorIncluir);
        return retorno;
    }

    @PutMapping(path = "/{id}")
    @Operation(description = "Método utilizado para altlerar os dados de um computador")
    public ComputadorDTO alterar(@RequestBody() ComputadorDTO computadorDTO, @PathVariable(name = "id") Long id ){
        Computador computador = computadorMapper.toComputador(computadorDTO);
        computador.setId(id);
        Computador alterar = computadorService.alterar(computador);
        return computadorMapper.toDTO(alterar);
    }

    @DeleteMapping(path ="/{id}")
    @Operation(description = "Método utililzado para remover um computador pelo Id informado")
    public ComputadorDTO remover(@PathVariable(name = "id") Long id){
        Computador computadorExcluido = this.computadorService.excluir(id);
        return computadorMapper.toDTO(computadorExcluido);
    }
}

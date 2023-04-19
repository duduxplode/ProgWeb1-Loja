package br.ueg.loja.mapper;

import br.ueg.loja.dto.ComputadorDTO;
import br.ueg.loja.model.Computador;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComputadorMapper {
    /**
     * Converte a Entidade {@link Computador} em {@link br.ueg.loja.dto.ComputadorDTO}
     * @param computador
     * @return
     */
    public ComputadorDTO toDTO(Computador computador);
    public List<ComputadorDTO> toDTO(List<Computador> computadores);
    public Computador toComputador(ComputadorDTO computadorDTO);
}

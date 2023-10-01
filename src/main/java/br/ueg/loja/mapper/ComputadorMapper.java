package br.ueg.loja.mapper;

import br.ueg.loja.dto.ComputadorDTO;
import br.ueg.loja.model.Computador;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComputadorMapper extends BaseMapper<Computador, ComputadorDTO> {
    /**
     * Converte a Entidade {@link Computador} em {@link ComputadorDTO}
     * @param computador
     * @return
     */

    @Override
    @Mapping(source = "fkTipoComputador.id" , target = "tipo_id")
    @Mapping(source = "fkTipoComputador.nome" , target = "tipo_nome")
    public ComputadorDTO toDTO(Computador computador);

    @Override
    @Mapping(source = "fkTipoComputador.id" , target = "tipo_id")
    @Mapping(source = "fkTipoComputador.nome" , target = "tipo_nome")
    public List<ComputadorDTO> toDTO(List<Computador> computadores);

    @Override
    @Mapping(source = "tipo_id" , target = "fkTipoComputador.id")
    public Computador toModelo(ComputadorDTO computadorDTO);
}

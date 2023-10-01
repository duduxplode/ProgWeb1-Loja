package br.ueg.loja.mapper;

import br.ueg.loja.dto.ComputadorDTO;
import br.ueg.loja.dto.TipoComputadorDTO;
import br.ueg.loja.model.Computador;
import br.ueg.loja.model.TipoComputador;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoComputadorMapper extends BaseMapper<TipoComputador, TipoComputadorDTO> {
    /**
     * Converte a Entidade {@link TipoComputador} em {@link TipoComputadorDTO}
     * @param tipoComputador
     * @return
     */
    public TipoComputadorDTO toDTO(TipoComputador tipoComputador);
    public List<TipoComputadorDTO> toDTO(List<TipoComputador> tipoComputadores);
    public TipoComputador toTipoComputador(TipoComputadorDTO tipoComputadorDTO);
}

package br.ueg.loja.mapper;

import br.ueg.loja.dto.ComputadorDTO;
import br.ueg.loja.dto.ItemVendaDTO;
import br.ueg.loja.dto.VendaDTO;
import br.ueg.loja.model.Computador;
import br.ueg.loja.model.ItemVenda;
import br.ueg.loja.model.Venda;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemVendaMapper extends BaseMapper<ItemVenda, ItemVendaDTO> {
    /**
     * Converte a Entidade {@link ItemVenda} em {@link ItemVendaDTO}
     * @param itemVenda
     * @return
     */

    @Override
    @Mapping(source = "fkComputador.id" , target = "computador_id")
    @Mapping(source = "fkVenda.id" , target = "venda_id")
        public ItemVendaDTO toDTO(ItemVenda itemVenda);

    @Override
    @Mapping(source = "fkComputador.id" , target = "computador_id")
    @Mapping(source = "fkVenda.id" , target = "venda_id")
    public List<ItemVendaDTO> toDTO(List<ItemVenda> items);

    @Override
    @Mapping(source = "computador_id" , target = "fkComputador.id")
    @Mapping(source = "venda_id" , target = "fkVenda.id")
    public ItemVenda toModelo(ItemVendaDTO itemVendaDTO);
}

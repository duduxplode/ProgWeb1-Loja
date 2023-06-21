package br.ueg.loja.mapper;

import br.ueg.loja.dto.VendaDTO;
import br.ueg.loja.model.Venda;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VendaMapper {
    /**
     * Converte a Entidade {@link Venda} em {@link VendaDTO}
     * @param venda
     * @return
     */
    public VendaDTO toDTO(Venda venda);
    public List<VendaDTO> toDTO(List<Venda> vendas);
    public Venda toVenda(VendaDTO vendaDTO);
}

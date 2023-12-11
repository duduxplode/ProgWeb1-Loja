package br.ueg.loja.service;

import br.ueg.loja.model.ItemVenda;
import br.ueg.loja.model.Venda;
import br.ueg.prog.webi.api.service.CrudService;

public interface ItemVendaService extends CrudService<ItemVenda, Long> {
    Integer contarItens(Long idComputador);
}

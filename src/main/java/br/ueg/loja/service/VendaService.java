package br.ueg.loja.service;

import br.ueg.loja.model.Computador;
import br.ueg.loja.model.Venda;
import br.ueg.prog.webi.api.service.CrudService;

import java.util.List;

public interface VendaService extends CrudService<Venda, Long> {
    Integer contarVendas(Long idComputador);
}

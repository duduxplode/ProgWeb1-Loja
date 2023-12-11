package br.ueg.loja.controller;

import br.ueg.loja.dto.ItemVendaDTO;
import br.ueg.loja.mapper.ItemVendaMapper;
import br.ueg.loja.model.ItemVenda;
import br.ueg.loja.service.ItemVendaService;
import br.ueg.prog.webi.api.controller.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${app.api.base}/item_venda")
public class ItemVendaController extends CrudController
        <ItemVenda, ItemVendaDTO, Long, ItemVendaMapper, ItemVendaService> {
}

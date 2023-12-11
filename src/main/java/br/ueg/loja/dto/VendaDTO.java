package br.ueg.loja.dto;

import br.ueg.loja.model.Computador;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public @Data class VendaDTO {
    private Long id;
    private Integer quantidade;
    private LocalDate dataVenda;
    private BigDecimal valorTotal;
    private String cliente;
    private List<ItemVendaDTO> listItemVendaDTO;
}

package br.ueg.loja.dto;

import lombok.Data;

import java.math.BigDecimal;

public @Data class ItemVendaDTO {
    private Long id;
    private ComputadorDTO fkComputadorDTO;
    private Long computador_id;
    private Long venda_id;
    private VendaDTO fkVendaDTO;
    private Integer quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
}

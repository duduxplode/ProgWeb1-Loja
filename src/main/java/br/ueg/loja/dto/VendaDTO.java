package br.ueg.loja.dto;

import br.ueg.loja.model.Computador;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

public @Data class VendaDTO {
    private Long id;
    private ComputadorDTO fkComputador;
    private Integer quantidade;
    private LocalDate dataVenda;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
    private String cliente;
}

package br.ueg.loja.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

public @Data class TipoComputadorDTO {
    private Long id;
    private String nome;
}

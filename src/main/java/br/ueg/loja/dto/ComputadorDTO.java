package br.ueg.loja.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

public @Data class ComputadorDTO {
    private Long id;
    private String descricao;
    private String tipo;
    private Integer tamanhoRam;
    private String unidadeRam;
    private String processador;
    private Integer tamanhoHd;
    private String unidadeHd;
    private LocalDate dataLancamento;
}

package br.ueg.loja.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
    private BigDecimal valorCompra;
    private BigDecimal valorVenda;
    private Integer quantidade;
    private String imagem;
    private String imagemBase64;
    private List<VendaDTO> listVendaDto;
    private Integer contVendas;
}

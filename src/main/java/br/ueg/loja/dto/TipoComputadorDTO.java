package br.ueg.loja.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public @Data class TipoComputadorDTO {
    @Schema(description = "Identificador do objeto em Hash")
    private String idHash;

    @Schema(description = "Número identificador do tipo")
    @NotNull
    private Long id;
    @Size(max = 20)
    @Schema(description = "Nome do tipo")
    @NotNull
    private String nome;
}

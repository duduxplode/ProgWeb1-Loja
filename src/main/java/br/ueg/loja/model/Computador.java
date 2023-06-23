package br.ueg.loja.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = "TBL_COMPUTADOR",
        uniqueConstraints = {
                @UniqueConstraint(name= Computador.UK_DESCRICAO, columnNames = "descricao" )
        }
)
public class Computador {
    public static final String UK_DESCRICAO = "uk_descricao";
    @SequenceGenerator(
            name="a_gerador_sequence",
            sequenceName = "computador_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "a_gerador_sequence"
    )
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "descricao", length = 200, nullable = false)
    private String descricao;
    @Column(name = "tipo", length = 200, nullable = false)
    private String tipo;
    @Column(name = "tamanhoRam", nullable = false)
    private Integer tamanhoRam;
    @Column(name = "unidade_ram", length = 2, nullable = false)
    private String unidadeRam;
    @Column(name = "processador", length = 200, nullable = false)
    private String processador;
    @Column(name = "tamanho_hd", nullable = false)
    private Integer tamanhoHd;
    @Column(name = "unidade_hd", length = 2, nullable = false)
    private String unidadeHd;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "data_lancamento", nullable = false)
    private LocalDate dataLancamento;
    @Column(name = "valor_compra", nullable = false)
    private BigDecimal valorCompra;
    @Column(name = "valor_venda", nullable = false)
    private BigDecimal valorVenda;
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    @Column(name = "imagem", nullable = true)
    private String imagem;

}
